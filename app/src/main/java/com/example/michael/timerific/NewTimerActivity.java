package com.example.michael.timerific;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.NumberPicker;
import android.widget.TextView;

public class NewTimerActivity extends WearableActivity {

    private NumberPicker numberPickerHours, numberPickerMinutes, numberPickerSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timer);

        numberPickerHours = findViewById(R.id.numberPickerHours);
        numberPickerMinutes = findViewById(R.id.numberPickerMinutes);
        numberPickerSeconds = findViewById(R.id.numberPickerSeconds);

        numberPickerHours.setMinValue(0);
        numberPickerMinutes.setMinValue(0);
        numberPickerSeconds.setMinValue(0);

        numberPickerHours.setMaxValue(99);
        numberPickerMinutes.setMaxValue(59);
        numberPickerSeconds.setMaxValue(59);

        numberPickerHours.setWrapSelectorWheel(false);
        numberPickerMinutes.setWrapSelectorWheel(true);
        numberPickerSeconds.setWrapSelectorWheel(true);

        // Enables Always-on
        setAmbientEnabled();
    }
}
