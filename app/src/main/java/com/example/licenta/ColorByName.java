package com.example.licenta;

import android.graphics.Color;

import java.util.ArrayList;

public class ColorByName {
    static int getColor(String name) {
        if (name.equals("Stop")) {
            return Color.rgb(255, 0, 0);
        } else if (name.equals("TurnLeft")) {
            return Color.rgb(0, 0, 255);
        } else if(name.equals("TurnRight")){
            return Color.rgb(0, 255, 0);
        }
        else if(name.equals("TurnAround")){
            return Color.rgb(255,255,0);
        }
        else if(name.equals("OnlyForward")){
            return Color.rgb(0,255,255);
        }
        else {
            return Color.rgb(0, 0, 0);
        }
    }

    static int[] getColorList(ArrayList<TrafficSign> trafficSigns) {
        int[] ret = new int[trafficSigns.size()];
        int idx = 0;

        for (TrafficSign t : trafficSigns) {
            ret[idx++] = getColor(t.getName());
        }
        return ret;
    }

    static int[]getCandColorList(ArrayList<Candidate> candidates){
        int[] ret = new int[candidates.size()];
        int idx = 0;

        for (Candidate t : candidates) {
            ret[idx++] = getColor(t.getName());
        }
        return ret;
    }
}
