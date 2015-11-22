package com.uet.quantity.uethackathon2015_team7.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.ThemeManager;
import com.rey.material.app.TimePickerDialog;
import com.uet.quantity.uethackathon2015_team7.R;
import com.uet.quantity.uethackathon2015_team7.adapter.TimeSettingAdapter;
import com.uet.quantity.uethackathon2015_team7.entity.TimeSettingEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TimeSettingFragment extends Fragment {

    public static TimeSettingFragment instance;
    private ListView list;
    private TimeSettingAdapter adapter;
    private ArrayList<TimeSettingEntity> list_time_setting;
    private SharedPreferences sharedPreferences;

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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String time1 = sharedPreferences.getString("pref_time_1", "6:00");
        String time2 = sharedPreferences.getString("pref_time_2", "21:00");

        list = (ListView) v.findViewById(R.id.list_time_setting);
        list_time_setting = new ArrayList<>();
        list_time_setting.add(new TimeSettingEntity("Hàng ngày", true, time1));
        list_time_setting.add(new TimeSettingEntity("Hàng ngày", true, time2));
        adapter = new TimeSettingAdapter(getActivity(), list_time_setting, null);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
                Dialog.Builder builder = new TimePickerDialog.Builder(isLightTheme ? R.style.Material_App_Dialog_TimePicker_Light : R.style.Material_App_Dialog_TimePicker, 24, 00) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        TimePickerDialog dialog = (TimePickerDialog) fragment.getDialog();
                        String time = dialog.getFormattedTime(new SimpleDateFormat("HH:mm"));
                        list_time_setting.get(position).setTime(time);
                        adapter.notifyDataSetChanged();
                        if (position == 1) {
                            sharedPreferences.edit().putString("pref_time_1", time).commit();
                        } else {
                            sharedPreferences.edit().putString("pref_time_2", time).commit();
                        }
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("Đồng ý")
                        .negativeAction("Thoát");

                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getFragmentManager(), null);
            }
        });

        return v;
    }
}
