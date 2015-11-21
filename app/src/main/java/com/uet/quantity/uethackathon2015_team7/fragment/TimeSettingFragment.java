package com.uet.quantity.uethackathon2015_team7.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uet.quantity.uethackathon2015_team7.R;
import com.uet.quantity.uethackathon2015_team7.adapter.TimeSettingAdapter;
import com.uet.quantity.uethackathon2015_team7.entity.TimeSettingEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeSettingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimeSettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeSettingFragment extends Fragment {

    public static TimeSettingFragment instance;
    private ListView list;
    private TimeSettingAdapter adapter;
    private ArrayList<TimeSettingEntity> list_time_setting;

    public static TimeSettingFragment newInstance() {
        if(instance == null) {
            instance = new TimeSettingFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_time_setting, container, false);

        list = (ListView) v.findViewById(R.id.list_time_setting);
        list_time_setting = new ArrayList<>();
        list_time_setting.add(new TimeSettingEntity("Hàng ngày", true, "6:00"));
        list_time_setting.add(new TimeSettingEntity("Hàng ngày", true, "21:00"));
        adapter = new TimeSettingAdapter(getActivity(), list_time_setting, null);
        list.setAdapter(adapter);

        return v;
    }

}
