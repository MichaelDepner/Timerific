package com.example.michael.timerific;

import java.util.ArrayList;

public class Timer {
    private String mName;
    private int mTime;

    public Timer(String name, int time) {
        mName = name;
        mTime = time;
    }

    public String getName() {
        return mName;
    }

    public int getTime() {
        return mTime;
    }

    public static int timerNumer = 0;

    public static ArrayList<Timer> createTimerList(int numTimers) {
        ArrayList<Timer> timers = new ArrayList<>();

        for (int i = 1; i<= numTimers; i++) {
            timers.add( new Timer("Timer "+ ++timerNumer, timerNumer));
        }
        return timers;
    }
}
