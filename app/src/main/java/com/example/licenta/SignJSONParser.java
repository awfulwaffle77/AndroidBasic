package com.example.licenta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignJSONParser {
    private String content;
    public SignJSONParser(String filename, int differntiateFromContent) throws IOException {
        File file = new File(filename);
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(
                file));
        byte[] buffer = new byte[(int) file.length()];
        bin.read(buffer);

        this.content = new String(buffer);
    }

    public SignJSONParser(String content){
        this.content = content;
    }

    // parses the JSON file and returns a DetailedOverlay
    public DetailedOverlay getParsedFile() throws JSONException {
        ArrayList<TrafficSign> trafficSignList = new ArrayList<>();

        JSONObject json = new JSONObject(this.content);
        JSONArray signs = json.getJSONArray("signs");
        String modelName = json.getString("modelName");
        int currentSpeed = json.getInt("currentSpeed");

        for(int i = 0; i < signs.length(); i++){
            JSONObject obj = signs.getJSONObject(i);
            String name = obj.getString("name");
            double accuracy = obj.getDouble("accuracy");
            int top = obj.getInt("top");
            int left = obj.getInt("left");
            int right = obj.getInt("right");
            int bottom = obj.getInt("bottom");

            ArrayList<Candidate> candidates = new ArrayList<>();
            JSONArray cand = obj.getJSONArray("candidates");
            for(int j = 0; j < cand.length(); j++){
                JSONObject candOBJ = cand.getJSONObject(j);
                String candName = candOBJ.getString("name");
                double candAcc = candOBJ.getDouble("accuracy");

                candidates.add(new Candidate(candName, candAcc));
            }
            Point p = new Point(left, top, right, bottom);
            TrafficSign t = new TrafficSign(p, name, accuracy, candidates);

            trafficSignList.add(t);
        }

        DetailedOverlay d = new DetailedOverlay(trafficSignList, modelName, currentSpeed);
        return d;
    }
}
