package com.uet.quantity.uethackathon2015_team7;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uet.quantity.uethackathon2015_team7.fragment.DetailFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DetailFragment detail = new DetailFragment();
        transaction.replace(R.id.container, detail);
        transaction.addToBackStack(null);
        transaction.commit();

        /*Intent service = new Intent(MainActivity.this, NotificationService.class);
        startService(service);*/
    }
}
