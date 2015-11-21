package com.uet.quantity.uethackathon2015_team7.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.fragment_lock_screen_setting, container, false);
    }

}
