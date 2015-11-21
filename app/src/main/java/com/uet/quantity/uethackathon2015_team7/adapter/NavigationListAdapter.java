package com.uet.quantity.uethackathon2015_team7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.uet.quantity.uethackathon2015_team7.R;
import com.uet.quantity.uethackathon2015_team7.entity.NavigationItem;

import java.util.ArrayList;

/**
 * Created by James Crabby on 11/21/2015.
 */
public class NavigationListAdapter extends BaseAdapter {

    ArrayList<NavigationItem> list_nav;
    Context context;
    LayoutInflater inflater;

    @Override
    public int getCount() {
        return list_nav.size();
    }

    @Override
    public NavigationItem getItem(int position) {
        return list_nav.get(position);
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
        convertView = inflater.inflate(R.layout.navigation_drawer_item, parent, false);
        ImageView img_nav_icon = (ImageView) convertView.findViewById(R.id.img_nav_icon);
        TextView tv_nav_name = (TextView) convertView.findViewById(R.id.tv_nav_name);
        NavigationItem item = list_nav.get(position);
        img_nav_icon.setBackgroundResource(item.getImageID());
        tv_nav_name.setText(item.getName());

        return convertView;
    }
}
