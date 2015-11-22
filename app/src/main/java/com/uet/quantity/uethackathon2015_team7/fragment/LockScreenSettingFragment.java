package com.uet.quantity.uethackathon2015_team7.fragment;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rey.material.widget.Switch;
import com.uet.quantity.uethackathon2015_team7.R;

import java.util.concurrent.locks.Lock;

public class LockScreenSettingFragment extends Fragment {

    public static LockScreenSettingFragment instance;

    public static LockScreenSettingFragment newInstance() {
        if(instance == null) {
            instance = new LockScreenSettingFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lock_screen_setting, container, false);

        TextView tv_ = (TextView) v.findViewById(R.id.tv_lock_setting_desc);
        tv_.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueLTStd-Lt-large-less-greater.otf"));

        Switch switch_lock_setting = (Switch) v.findViewById(R.id.switch_lock_setting);

        return v;
    }

}
