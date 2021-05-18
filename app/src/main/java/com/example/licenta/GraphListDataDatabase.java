package com.example.licenta;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {GraphListData.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class GraphListDataDatabase extends RoomDatabase {

    public abstract GraphListDataDAO graphdao();
    private static GraphListDataDatabase INSTANCE;
    public static GraphListDataDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GraphListDataDatabase.class,
                    "TVDatabase").allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
