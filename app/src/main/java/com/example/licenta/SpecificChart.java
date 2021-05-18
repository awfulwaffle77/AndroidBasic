package com.example.licenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class SpecificChart extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    String[] labels;
    ArrayList<Candidate> candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chart);

        Intent intent = getIntent();
        candidates = intent.getParcelableArrayListExtra("candidates");

        barChart = (BarChart)findViewById(R.id.SpecificBarChart);
        getEntries();
        updateGraph();
    }

    private void updateGraph() {
        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorByName.getCandColorList(candidates));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
//
//        BarChart barView = (BarChart) findViewById(R.id.SpecificBarChart);
//        barView.invalidate();
    }

    private void getEntries() {
        barEntries = new ArrayList<>();

        labels = new String[candidates.size()];
        int index = 0;
        for (Candidate t : candidates) {
            labels[index] = t.getName();
            barEntries.add(new BarEntry(index++, (float) t.getAccuracy()));
        }
    }
}