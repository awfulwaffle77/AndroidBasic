package com.example.licenta;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

@Entity(tableName = "GraphListData")
public class GraphListData {
    @PrimaryKey(autoGenerate = true)
    public int db_id;

    private String date;
    private String timeBegin;
    private String timeEnd;


    @TypeConverters(Converters.class)
    private ArrayList<TrafficSign> trafficSigns;
//    private TrafficSigns trafficSigns;

    public GraphListData(String date, String timeBegin, String timeEnd, ArrayList<TrafficSign> trafficSigns) {
        this.date = date;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
//        this.trafficSigns = new TrafficSigns(trafficSigns);
        this.trafficSigns = trafficSigns;
    }

    @Ignore
    public GraphListData(String date, String timeBegin, String timeEnd){
        this.date = date;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    @Ignore
    public GraphListData(){
        this.date = "";
        this.timeBegin = "";
        this.timeEnd = "";
//        this.trafficSigns = new TrafficSigns();
        this.trafficSigns = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public ArrayList<TrafficSign> getTrafficSigns() {
//        return trafficSigns.getTrafficSigns();
        return trafficSigns;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setTrafficSigns(ArrayList<TrafficSign> trafficSigns) {
//        this.trafficSigns.setTrafficSigns(trafficSigns);
        this.trafficSigns = trafficSigns;
    }
}
