package com.example.licenta;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<GraphListData> {

    Context context;
    ViewHolder viewHolder;
    private ArrayList<GraphListData> data;
    int r = 0;

    public CustomAdapter(ArrayList<GraphListData> data, Context context) {
        super(context, R.layout.activity_graph_layout, data);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        super.getView(position, convertView, parent);
        final View result;

        int r = Color.rgb(254,0,0);
        int g = Color.rgb(0,255,0);

        GraphListData graphListData = getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_graph_layout, parent, false);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.interval = (TextView) convertView.findViewById(R.id.interval);
//            if(position % 2 == )
//            convertView.setBackgroundColor(r);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.date.setText(graphListData.getDate());
        viewHolder.interval.setText((graphListData.getTimeBegin() + " - " + graphListData.getTimeEnd()));

        return convertView;
    }

    private static class ViewHolder {
        TextView date;
        TextView interval;
    }
}
