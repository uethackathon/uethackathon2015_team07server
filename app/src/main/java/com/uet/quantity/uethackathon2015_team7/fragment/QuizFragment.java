package com.uet.quantity.uethackathon2015_team7.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uet.quantity.uethackathon2015_team7.R;

public class QuizFragment extends Fragment {

    public static QuizFragment instance;

    public static QuizFragment newInstance() {
        if(instance == null) {
            instance = new QuizFragment();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        return v;
    }

}
