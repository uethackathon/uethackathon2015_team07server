package com.uet.quantity.uethackathon2015_team7.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView tv_tree_desc = (TextView) v.findViewById(R.id.tv_tree_desc);
        //tv_tree_desc.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueLTStd-Lt-large-less-greater.otf"));
        tv_tree_desc.setText("Cây lịch sử của bạn !");
        return v;
    }
}
