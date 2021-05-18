package com.example.licenta;

import android.util.Pair;

import androidx.room.Insert;
import androidx.room.TypeConverter;

public class Point {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Point(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }



    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }
}
