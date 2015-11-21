package com.uet.quantity.uethackathon2015_team7;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.uet.quantity.uethackathon2015_team7.fragment.DetailFragment;
import com.uet.quantity.uethackathon2015_team7.receiver.AlarmManagerBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private AlarmManagerBroadcastReceiver alarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarm = new AlarmManagerBroadcastReceiver();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DetailFragment detail = new DetailFragment();
        transaction.replace(R.id.container, detail);
        transaction.addToBackStack(null);
        transaction.commit();

        /*Intent service = new Intent(MainActivity.this, NotificationService.class);
        startService(service);*/
        startRepeatingTimer();
    }

    public void startRepeatingTimer() {
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.SetAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

}
