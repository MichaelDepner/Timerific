package com.example.michael.timerific;

import android.content.Context;
import android.support.wear.widget.WearableRecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class TimerAdapter extends
        WearableRecyclerView.Adapter<TimerAdapter.ViewHolder> {

    public class ViewHolder extends WearableRecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView numberTextView;
        public ToggleButton messageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.timer_name);
            numberTextView = itemView.findViewById(R.id.timer_number);
            messageButton = itemView.findViewById(R.id.message_button);


        }
    }

    private List<Timer> mTimers;
    private Context mContext;

    public TimerAdapter(Context context, List<Timer> timers) {
        mContext = context;
        mTimers = timers;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View timerView = inflater.inflate(R.layout.item_timer, parent, false);
        ViewHolder viewHolder = new ViewHolder(timerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final Timer timer = mTimers.get(position);
        Log.v("TimerAdapter","onBindViewHolder for "+timer.getName());

        //viewHolder.setIsRecyclable(false);

        final TextView nameTextView = viewHolder.nameTextView;
        nameTextView.setText(timer.getName());

        final TextView numberTextView = viewHolder.numberTextView;
        numberTextView.setText( timer.getCurrentFormattedTime() );

        final ToggleButton button = viewHolder.messageButton;
        button.setText( String.valueOf( timer.isRunning() ) );
        if (timer.isRunning()) {
            button.setChecked(true);
            timer.stopCountDownTimer();
            timer.startCountDownTimer(numberTextView, button);
            System.out.println("Starting "+nameTextView.getText());
        } else {
            button.setChecked(false);
        }
        button.setOnClickListener(new ToggleButton.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (button.isChecked()) {
                    if ( !timer.isRunning() ) {
                        timer.stopCountDownTimer();
                        timer.startCountDownTimer(numberTextView, button);
                        System.out.println("Starting "+nameTextView.getText());
                    }
                } else {
                    if ( timer.isRunning() ) {
                        timer.stopCountDownTimer();
                        System.out.println("Stopping "+nameTextView.getText());
                    }
                }
            }
        });

       /* button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if ( !timer.isRunning() ) {
                        timer.startCountDownTimer(numberTextView, compoundButton);
                        System.out.println("Starting "+nameTextView.getText());
                    }
                } else {
                    if ( timer.isRunning() ) {
                        timer.stopCountDownTimer();
                        System.out.println("Stopping "+nameTextView.getText());
                    }
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mTimers.size();
    }

}
