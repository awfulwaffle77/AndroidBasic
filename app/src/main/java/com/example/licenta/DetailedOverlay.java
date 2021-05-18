package com.example.licenta;

import android.util.Pair;

import java.util.ArrayList;

public class DetailedOverlay {
    // the current traffic signs detected by the camera
    ArrayList<TrafficSign> signList;  // this will refresh every second or as soon as it gets and updated version
    ArrayList<Pair<String, Float>> last5Signs;
    String modelName;  // current model used
    int speed;

    public DetailedOverlay(ArrayList<TrafficSign> signList, String modelName, int speed) {
        this.signList = signList;
        this.modelName = modelName;
        this.speed = speed;
    }

    public DetailedOverlay(ArrayList<TrafficSign> signList) {
        this.signList = signList;
    }

    public ArrayList<TrafficSign> getSignList() {
        return signList;
    }

    public void setSignList(ArrayList<TrafficSign> signList) {
        this.signList = signList;
    }

    public String getModelName() {
        return modelName;
    }

    public int getSpeed() {
        return speed;
    }
}
