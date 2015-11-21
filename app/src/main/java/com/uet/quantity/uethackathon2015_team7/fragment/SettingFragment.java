package com.uet.quantity.uethackathon2015_team7.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uet.quantity.uethackathon2015_team7.R;

/**
 * Created by James Crabby on 11/21/2015.
 */
public class SettingFragment extends Fragment {

    public static SettingFragment instance;

    public static SettingFragment newInstance() {
        if(instance == null) {
            instance = new SettingFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        return v;
    }
}
