package com.example.michael.timerific;

import android.os.CountDownTimer;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class Timer {
    private String mName;
    private long mTotalTime;
    private long mCurrentTime;
    private CountDownTimer mCountDownTimer;
    private boolean running;

    public Timer(String name, long time) {
        mName = name;
        mTotalTime = time;
        mCurrentTime = time;
        running = false;
    }

    public String getName() {
        return mName;
    }

    public long getCurrentTime() {
        return mCurrentTime;
    }

    public long getTotalTime() {
        return mTotalTime;
    }

    public void startCountDownTimer(final TextView timerText, final CompoundButton button) {
        //if ( mCurrentTime == 0 ) { mCurrentTime = mTotalTime; }
        System.out.println("Starting timer that runs for "+mCurrentTime+" milliseconds");
        mCountDownTimer = new CountDownTimer(mCurrentTime, 100) {
            @Override
            public void onTick(long l) {
                mCurrentTime = l;
                timerText.setText(getCurrentFormattedTime());
            }

            @Override
            public void onFinish() {
                mCurrentTime = mTotalTime; //Resetting time
                System.out.println("Timer finished!");
                button.setChecked(false);
                timerText.setText(getCurrentFormattedTime());
            }
        };
        mCountDownTimer.start();
        running = true;
    }

    public void stopCountDownTimer() {
        if (mCountDownTimer != null) {
            System.out.println("Stopping "+mName);
            mCountDownTimer.cancel();
        }
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public String getCurrentFormattedTime() {
        long seconds = mCurrentTime / 1000;
        long minutes = mCurrentTime / (60*1000);
        long hours = mCurrentTime / (3600*1000);
        //If total timer is longer than an hour, display HH:mm:ss, else mm:ss
        if (( mTotalTime / (60*1000) ) > 60) {
            return String.format(Locale.UK,"%02d:%02d:%02d", hours,
                    minutes % 60,
                    seconds % 60);
        } else {
            return String.format(Locale.UK, "%02d:%02d", minutes, seconds % 60);
        }
    }

    //tmp stuff for making dummy timers

    public static int timerNumber = 0;

    public static ArrayList<Timer> createTimerList(int numTimers) {
        ArrayList<Timer> timers = new ArrayList<>();

        for (int i = 1; i<= numTimers; i++) {
            timers.add( new Timer("Timer "+ ++timerNumber, timerNumber*1000000));
        }
        return timers;
    }
}
