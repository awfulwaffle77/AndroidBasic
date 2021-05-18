package com.example.licenta;

import android.util.Pair;

import androidx.room.TypeConverter;

import java.util.ArrayList;

// https://stackoverflow.com/questions/46914736/how-canvas-drawrect-draws-a-rectangle
public class TrafficSign {
    private Point rectangle;
    private String name;
    private double accuracy;
    private ArrayList<Candidate> candidates;

//    public TrafficSign(Point rectangle, String name, double accuracy) {
//        this.rectangle = rectangle;
//        this.name = name;
//        this.accuracy = accuracy;
//        this.candidates = null;
//    }

    public TrafficSign(Point rectangle, String name, double accuracy, ArrayList<Candidate> candidates) {
        this.rectangle = rectangle;
        this.name = name;
        this.accuracy = accuracy;
        this.candidates = candidates;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public Point getRectangle() {
        return rectangle;
    }

    public String getName() {
        return name;
    }

    public double getAccuracy() {
        return accuracy;
    }
}
