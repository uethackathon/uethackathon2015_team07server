package com.uet.quantity.uethackathon2015_team7.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
                Dialog.Builder builder = new TimePickerDialog.Builder(isLightTheme ? R.style.Material_App_Dialog_TimePicker_Light : R.style.Material_App_Dialog_TimePicker, 24, 00){
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        TimePickerDialog dialog = (TimePickerDialog)fragment.getDialog();
                        Toast.makeText(getActivity(), "Time is " + dialog.getFormattedTime(SimpleDateFormat.getTimeInstance()), Toast.LENGTH_SHORT).show();
                        String time = dialog.getFormattedTime(new SimpleDateFormat("HH:mm"));
                        list_time_setting.get(position).setTime(time);
                        adapter.notifyDataSetChanged();
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(getActivity(), "Cancelled" , Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("OK")
                        .negativeAction("CANCEL");

                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getFragmentManager(), null);
            }
        });

        return v;
    }
}
