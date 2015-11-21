package com.uet.quantity.uethackathon2015_team7.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uet.quantity.uethackathon2015_team7.R;
public class TreeFragment extends Fragment {

    public static TreeFragment instance;

    public static TreeFragment newInstance() {
        if(instance == null) {
            instance = new TreeFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tree, container, false);
        return v;
    }
}
