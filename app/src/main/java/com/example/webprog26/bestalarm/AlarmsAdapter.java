package com.example.webprog26.bestalarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webprog26 on 08.06.18.
 */

public class AlarmsAdapter extends RecyclerView.Adapter {

    @NonNull
    private final Context mContext;

    @NonNull
    private final MainFragment.OnAlarmClickedListener mOnAlarmClickedListener;

    @NonNull
    private List<Alarm> mAlarmsList = new ArrayList<>();

    public AlarmsAdapter(@NonNull final Context context, @NonNull final MainFragment.OnAlarmClickedListener listener) {
        this.mContext = context;
        this.mOnAlarmClickedListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlarmsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.alarm_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!mAlarmsList.isEmpty()) {
            ((AlarmsViewHolder) holder).bind(mAlarmsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mAlarmsList.size();
    }

    class AlarmsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_alarm_time)
        TextView mTvAlarmTime;

        @BindView(R.id.tv_alarm_label)
        TextView mTvAlarmLabel;

        @BindView(R.id.tv_alarm_is_repeatable)
        TextView mTvAlarmIsRepeatable;

        @BindView(R.id.sw_alarm_active)
        SwitchCompat mSwAlarmIsActive;

        public AlarmsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull final Alarm alarm) {
            mTvAlarmTime.setText(mContext.getString(R.string.alarm_time, alarm.getHours(), getMinutesString(alarm.getMinutes())));
            mTvAlarmLabel.setText(alarm.getLabel());
            mTvAlarmIsRepeatable.setText(alarm.isRepeatable() ? mContext.getString(R.string.alarm_repeat_on)
                    : mContext.getString(R.string.alarm_repeat_off));
            mSwAlarmIsActive.setChecked(alarm.isActive());

            itemView.setOnClickListener((v) -> mOnAlarmClickedListener.onAlarmClicked(alarm.getId()));
        }

        private String getMinutesString(final int minutes) {
            if (minutes < 10) {
                return "0" + minutes;
            }
            return String.valueOf(minutes);
        }
    }

    void updateAlarmList(@NonNull final List<Alarm> alarmsList) {
        this.mAlarmsList = alarmsList;
        notifyDataSetChanged();
    }
}
