package com.uet.quantity.uethackathon2015_team7;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.uet.quantity.uethackathon2015_team7.database.DatabaseHandler;
import com.uet.quantity.uethackathon2015_team7.fragment.DetailFragment;
import com.uet.quantity.uethackathon2015_team7.model.HistoryItem;
import com.uet.quantity.uethackathon2015_team7.receiver.AlarmManagerBroadcastReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private AlarmManagerBroadcastReceiver alarm;

    DatabaseHandler db;
    public static final String url = "http://uethackathon07.herokuapp.com/api/histories";
    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

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

        getData();
    }

    public void startRepeatingTimer() {
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.SetAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null ", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData () {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                super.onSuccess(statusCode, headers, array);

                for(int i =0; i < array.length(); i ++) {
                    try {
                        HistoryItem item = gson.fromJson(array.get(i).toString(), HistoryItem.class);
                        db.addHistory(item);

                    }catch (Exception e) {

                    }

                }

                Log.d("database count", db.getHistoryCount() + "");
            }
        });
    }

}
