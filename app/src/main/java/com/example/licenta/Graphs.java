package com.example.licenta;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class Graphs extends AppCompatActivity {

    ArrayList<GraphListData> data;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    Intent intent;
    int adapterIndex;

    String[] labels;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        ArrayList<GraphListData> graphListData = new ArrayList<>();
        List<GraphListData> g = GraphListDataDatabase.getInstance(getApplicationContext()).graphdao().getAllGraphListData();
        graphListData.addAll(g);

        intent = new Intent(this, SpecificChart.class);
        // Populates the chart with the last elements from database
        GraphListData listData = GraphListDataDatabase.getInstance(getApplicationContext()).graphdao().getLastGraphListData();
        barChart = findViewById(R.id.BarChart);

        getEntries();
        updateGraph(listData.getTrafficSigns());

        ListView listView = (ListView) findViewById(R.id.datesList);
        data = graphListData;
        adapter = new CustomAdapter(data, getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GraphListData d = data.get(position);
                setNewBarEntries(d.getTimeBegin(), d.getTimeEnd(), d.getDate());
                updateGraph(d.getTrafficSigns());
                adapterIndex = position;
            }
        });
    }

    private void getEntries() {
        barEntries = new ArrayList<>();
        GraphListData graphListData =
                GraphListDataDatabase.getInstance(getApplicationContext()).graphdao()
                        .getLastGraphListData();

        labels = new String[graphListData.getTrafficSigns().size()];
        int index = 0;
        for (TrafficSign t : graphListData.getTrafficSigns()) {
            labels[index] = t.getName();
            barEntries.add(new BarEntry(index++, (float) t.getAccuracy()));
        }
    }

    private void setNewBarEntries(String timeBegin, String timeEnd, String date) {
        barEntries = new ArrayList<>();
        GraphListData graphListData =
                GraphListDataDatabase.getInstance(getApplicationContext()).graphdao()
                        .getThisGraphListData(timeBegin, timeEnd, date);

        labels = new String[graphListData.getTrafficSigns().size()];
        int index = 0;
        for (TrafficSign t : graphListData.getTrafficSigns()) {
            labels[index] = t.getName();
            barEntries.add(new BarEntry(index++, (float) t.getAccuracy()));
        }
    }


    private void updateGraph(ArrayList<TrafficSign> ts) {
        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorByName.getColorList(ts));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int position = Math.round(h.getX());
                GraphListData g = data.get(adapterIndex);
                intent.putParcelableArrayListExtra("candidates", g.getTrafficSigns().get(position).getCandidates());
                startActivityForResult(intent, 113);
            }

            @Override
            public void onNothingSelected() {

            }
        });

        BarChart barView = (BarChart) findViewById(R.id.BarChart);
        barView.invalidate();
    }
}