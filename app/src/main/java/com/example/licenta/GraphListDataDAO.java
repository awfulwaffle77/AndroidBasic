package com.example.licenta;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface GraphListDataDAO {

    @Query("SELECT * FROM GraphListData")
    List<GraphListData> getAllGraphListData();

    @Query("SELECT * FROM GraphListData LIMIT 1")
    GraphListData getLastGraphListData();

    @Query("SELECT * FROM GraphListData WHERE timeBegin = :p1 AND timeEnd = :p2 AND date = :p3")
    GraphListData getThisGraphListData(String p1, String p2, String p3);

    @Insert
    void insertGraphListData(GraphListData... data);

    @Query("DELETE FROM GraphListData")
    void nukeTable();
}
