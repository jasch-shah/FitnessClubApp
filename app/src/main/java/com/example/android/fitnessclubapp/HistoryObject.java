package com.example.android.fitnessclubapp;

/**
 * Created by jashshah on 20/03/18.
 */

public class HistoryObject {

    String name;
    String Date;
    String TotalTime;

    public HistoryObject(String name, String date, String totalTime) {
        this.name = name;
        Date = date;
        TotalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return Date;
    }

    public String getTotalTime() {
        return TotalTime;
    }
}
