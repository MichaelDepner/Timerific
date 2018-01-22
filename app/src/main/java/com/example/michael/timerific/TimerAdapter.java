package com.example.michael.timerific;

import android.content.Context;
import android.support.wear.widget.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TimerAdapter extends
        WearableRecyclerView.Adapter<TimerAdapter.ViewHolder> {

    public class ViewHolder extends WearableRecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.timer_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
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
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Timer timer = mTimers.get(position);
        TextView textView = viewHolder.nameTextView;
        textView.setText(timer.getName());
        Button button = viewHolder.messageButton;
        button.setText( String.valueOf( timer.getTime() ) );
        button.setEnabled(true);

    }

    @Override
    public int getItemCount() {
        return mTimers.size();
    }

}
