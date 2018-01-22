package com.example.michael.timerific;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends WearableActivity {
    public static final String EXTRA_MESSAGE = "com.example.michael.timerific.MESSAGE";

    private TextView mTextView;
    ArrayList<Timer> timers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();

        WearableRecyclerView rvTimers = findViewById(R.id.main_list);

        timers = Timer.createTimerList(10);
        TimerAdapter adapter = new TimerAdapter(this, timers);
        rvTimers.setEdgeItemsCenteringEnabled(true);
        rvTimers.setAdapter(adapter);
        //rvTimers.setLayoutManager(new LinearLayoutManager(this));
        rvTimers.setLayoutManager( new WearableLinearLayoutManager(this));


        //List<String> exampleArray = Arrays.asList("Example 1", "Example 2", "Example 4?", "Just kidding", "Hello", "Another one");
        //["Element 1", "Element 2"];
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exampleArray);

        //view.setLayoutManager(new WearableLinearLayoutManager(this));

        //view.setAdapter(adapter);


    }

    /*public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/
}