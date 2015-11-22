package com.uet.quantity.uethackathon2015_team7.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rey.material.widget.ProgressView;
import com.uet.quantity.uethackathon2015_team7.R;

import java.io.InputStream;

/**
 * Created by James Crabby on 11/20/2015.
 */
public class DetailFragment extends Fragment {

    public static DetailFragment instance;
    ImageView img_infographic;
    ProgressView progress;
    private final String URL = "http://uethackathon07.herokuapp.com/api/pictures/21_11";

    public static DetailFragment newInstance() {
        if(instance == null) {
            instance = new DetailFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        img_infographic = (ImageView) v.findViewById(R.id.img_infographic);
        progress = (ProgressView) v.findViewById(R.id.detail_progress);
        new DownloadImageTask(img_infographic).execute(URL);

        return v;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            progress.setVisibility(View.GONE);
            img_infographic.setVisibility(View.VISIBLE);
        }
    }
}
