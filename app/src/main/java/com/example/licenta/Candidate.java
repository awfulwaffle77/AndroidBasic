package com.example.licenta;

import android.os.Parcel;
import android.os.Parcelable;

public class Candidate implements Parcelable {
    public static final Creator<Candidate> CREATOR = new Creator<Candidate>() {
        @Override
        public Candidate createFromParcel(Parcel in) {
            return new Candidate(in);
        }

        @Override
        public Candidate[] newArray(int size) {
            return new Candidate[size];
        }
    };
    private String name;
    private double accuracy;

    protected Candidate(Parcel in) {
        name = in.readString();
        accuracy = in.readDouble();
    }

    public Candidate(String name, double accuracy) {
        this.name = name;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(accuracy);
    }
}
