package com.uet.quantity.uethackathon2015_team7.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rey.material.widget.Switch;
import com.uet.quantity.uethackathon2015_team7.R;
import com.uet.quantity.uethackathon2015_team7.entity.TimeSettingEntity;

import java.util.ArrayList;

/**
 * Created by James Crabby on 11/21/2015.
 */
public class TimeSettingAdapter extends BaseAdapter {

    ArrayList<TimeSettingEntity> list_time_setting;
    Context context;
    LayoutInflater inflater;

    public TimeSettingAdapter(Context context, ArrayList<TimeSettingEntity> list_time_setting, LayoutInflater inflater) {
        this.context = context;
        this.list_time_setting = list_time_setting;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list_time_setting.size();
    }

    @Override
    public Object getItem(int position) {
        return list_time_setting.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.time_setting_item, parent, false);
        TextView tv_time_setting_tm = (TextView) convertView.findViewById(R.id.tv_time_setting_tm);
        Switch switch_time_setting = (Switch) convertView.findViewById(R.id.switch_time_setting);
        TextView tv_time_setting_reply = (TextView) convertView.findViewById(R.id.tv_time_setting_reply);

        TimeSettingEntity item = list_time_setting.get(position);
        tv_time_setting_tm.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLTStd-Lt-large-less-greater.otf"));
        tv_time_setting_tm.setText(item.getTime());
        tv_time_setting_reply.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLTStd-Lt-large-less-greater.otf"));
        tv_time_setting_reply.setText(item.getReply());

        return convertView;
    }
}
