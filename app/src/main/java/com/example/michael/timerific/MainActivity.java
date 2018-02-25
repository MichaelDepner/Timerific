package com.example.michael.timerific;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.drawer.WearableNavigationDrawer;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends WearableActivity implements MenuItem.OnMenuItemClickListener {
    public static final String EXTRA_MESSAGE = "com.example.michael.timerific.MESSAGE";

    private static final String TAG = "MainActivity";
    ArrayList<Timer> timers;

    private WearableActionDrawerView mWearableActionDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();

        WearableRecyclerView rvTimers = findViewById(R.id.main_list);

        timers = Timer.createTimerList(10);
        final TimerAdapter adapter = new TimerAdapter(this, timers);
        rvTimers.setEdgeItemsCenteringEnabled(true);
        rvTimers.setAdapter(adapter);

        //WearableRecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //rvTimers.addItemDecoration(itemDecoration);

        //rvTimers.setLayoutManager(new LinearLayoutManager(this));
        rvTimers.setLayoutManager( new WearableLinearLayoutManager(this));
        rvTimers.setItemAnimator( new DefaultItemAnimator() );


        //Navigation drawer stuff
        mWearableActionDrawer = (WearableActionDrawerView)findViewById(R.id.bottom_action_drawer);
        mWearableActionDrawer.setOnMenuItemClickListener(this);

        //List<String> exampleArray = Arrays.asList("Example 1", "Example 2", "Example 4?", "Just kidding", "Hello", "Another one");
        //["Element 1", "Element 2"];
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exampleArray);

        //view.setLayoutManager(new WearableLinearLayoutManager(this));

        //view.setAdapter(adapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Log.d(TAG, "onMenuItemClick(): " + menuItem);

        final int itemId = menuItem.getItemId();
        String toastMessage = "";

        switch (itemId) {
            case R.id.menu_new_timer:
                //TODO: Instead of toasting, launch new timer activity
                toastMessage = "Creating new timer!";
                break;
        }

        mWearableActionDrawer.getController().closeDrawer();

        if (toastMessage.length() > 0) {
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    toastMessage,
                    Toast.LENGTH_SHORT);
            toast.show();
            return true;
        } else {
            return false;
        }
    }

}