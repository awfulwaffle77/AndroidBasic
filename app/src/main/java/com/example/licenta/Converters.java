package com.example.licenta;

import android.util.Pair;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// Converts to json when putting the item into the database and then converts it back
public class Converters {

    static Gson gson = new Gson();

    @TypeConverter
    public static String trafficArrayToString(ArrayList<TrafficSign> t){
        return gson.toJson(t);
    }

    @TypeConverter
    public static ArrayList<TrafficSign> stringToTSign(String s){
        return gson.fromJson(s, new TypeToken<ArrayList<TrafficSign>>(){}.getType());
    }

}
