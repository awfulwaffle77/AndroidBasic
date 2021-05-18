package com.example.licenta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class VideoActivity extends AppCompatActivity {

    DetailedOverlay d;
    GraphListData listData;
    private VideoView videoView;
    private TrafficSignCanvas hud;
    private Boolean videoIsStarted = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        GraphListDataDatabase.getInstance(getApplicationContext()).graphdao().nukeTable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button b = (Button) findViewById(R.id.button);
        b.setText("PLAY");

//        String content = "{  \"modelName\": \"cnn_124\",  \"currentSpeed\": 23,  \"signs\": [    {      \"left\": 40,      \"top\": 40,      \"right\": 90,      \"bottom\": 160,      \"name\": \"Stop\",      \"accuracy\": 77.5,      \"candidates\": [        {          \"name\": \"Stop\",          \"accuracy\": 77.5        },        {          \"name\": \"TurnLeft\",          \"accuracy\": 15.6        },        {          \"name\": \"Stop\",          \"accuracy\": 9.65        }      ]    },    {      \"left\": 12,      \"top\": 252,      \"right\": 325,      \"bottom\": 412,      \"name\": \"TurnLeft\",      \"accuracy\": 27.9,      \"candidates\": [        {          \"name\": \"TurnLeft\",          \"accuracy\": 27.9        },        {          \"name\": \"TurnRight\",          \"accuracy\": 15.6        },        {          \"name\": \"OnlyForward\",          \"accuracy\": 9.65        }      ]    }  ]}";
        String content = "{  \"modelName\": \"cnn_124\",  \"currentSpeed\": 23,  \"signs\": [    {      \"left\": 40,      \"top\": 40,      \"right\": 90,      \"bottom\": 160,      \"name\": \"TurnAround\",      \"accuracy\": 33.5,      \"candidates\": [        {          \"name\": \"Stop\",          \"accuracy\": 33.5        },        {          \"name\": \"TurnRight\",          \"accuracy\": 12.6        },        {          \"name\": \"TurnLeft\",          \"accuracy\": 9.65        }      ]    },    {      \"left\": 12,      \"top\": 252,      \"right\": 325,      \"bottom\": 412,      \"name\": \"TurnRight\",      \"accuracy\": 27.9,      \"candidates\": [        {          \"name\": \"TurnRight\",          \"accuracy\": 27.9        },        {          \"name\": \"TurnLeft\",          \"accuracy\": 15.6        },        {          \"name\": \"OnlyForward\",          \"accuracy\": 9.65        }      ]    }  ]}";
//        String content = null;
//        try {
//            content = getContent("https://pastebin.com/raw/uwUm7tji");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            SignJSONParser parser = new SignJSONParser(content);
            d = parser.getParsedFile();
            hud = (TrafficSignCanvas) findViewById(R.id.customHud);
            hud.setOverlay(d);

            OverlayDetailsHUD detailsHud = (OverlayDetailsHUD) findViewById(R.id.overlayDetailsHUD);
            detailsHud.setOverlay(d);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("https://v.ftcdn.net/04/30/29/16/700_F_430291647_tZMwG90Y8qDxqkjRYI53T7JzEztkyepi_ST.mp4"));
        videoView.start();
        int[] a = new int[2];
        videoView.getLocationOnScreen(a);
        videoView.pause();
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    private String getContent(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
//        conn.connect();
//        int response = conn.getResponseCode();
        InputStream is = conn.getInputStream();

        // Convert the InputStream into a string
        String contentAsString = convertStreamToString(is);
        return contentAsString;
    }

    private void simulateMovement() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ArrayList<TrafficSign> list = new ArrayList<>();

                // Generate 2 signs with random positions and size
                for (int i = 0; i < 2; i++) {
                    Random generator = new Random();
                    int p1 = generator.nextInt(400) + 40;
                    int p2 = generator.nextInt(400) + 40;
                    int p3 = generator.nextInt(400) + 40;
                    int p4 = generator.nextInt(400) + 40;
                    Point p = new Point(p1, p2, p3, p4);
                    TrafficSign t = new TrafficSign(p, "Max99", 75.3, null);
                    list.add(t);
                }

                DetailedOverlay d = new DetailedOverlay(list);
                hud.setOverlay(d);
                hud.invalidate();
            }
        };
        handler.postDelayed(r, 1000);
    }

    public void set(View view) {
//        hud = (CustomHUD) findViewById(R.id.customHud);
//        hud.setRadius(hud.getRadius()+15);
        Calendar rightNow = Calendar.getInstance();
        String currentHour = String.valueOf(rightNow.get(Calendar.HOUR_OF_DAY));
        int zz = rightNow.get(Calendar.MINUTE);
        String currentMinutes = String.valueOf(zz);
        if (zz < 10) {
            currentMinutes = "0" + currentMinutes;
        }
        String day = String.valueOf(rightNow.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(rightNow.get(Calendar.MONTH));
        String year = String.valueOf(rightNow.get(Calendar.YEAR));

        if (listData == null) {
            listData = new GraphListData();
        }

        if (videoIsStarted) {
            // If video is started, we stop it, set the end time and the traffic signs list
            videoIsStarted = Boolean.FALSE;
            videoView.pause();  // we simulated the stop of the video
            Button b = (Button) findViewById(R.id.button);
            b.setText("PLAY");

            listData.setTimeEnd(currentHour + ":" + currentMinutes);
            listData.setTrafficSigns(d.signList);
            // Insert into database after recording is done
            GraphListDataDatabase.getInstance(getApplicationContext()).graphdao().insertGraphListData(listData);

        } else {
            // If video is not started we start it, set the date and start time
            videoIsStarted = Boolean.TRUE;
            Button b = (Button) findViewById(R.id.button);
            b.setText("PAUSE");
            videoView.start();

            listData.setDate(day + "/" + month + "/" + year);
            listData.setTimeBegin(currentHour + ":" + currentMinutes);

            simulateMovement();
        }

//        hud.invalidate();  // to refresh canvas
    }

    public void goToGraphs(View view){
        Intent i = new Intent(this, Graphs.class);
        startActivityForResult(i, 1);
    }

    public void goToModels(View view){
        Intent i = new Intent(this, UploadModel.class);
        startActivityForResult(i, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){  // if started activity for Graphs
            if(resultCode == RESULT_OK)
            {
                return;
            }
        }
        if(requestCode == 2) {  // if started for UploadModel
            if(resultCode == RESULT_OK){
                return;
            }
        }
    }
}