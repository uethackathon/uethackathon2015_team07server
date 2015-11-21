package com.uet.quantity.uethackathon2015_team7;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.uet.quantity.uethackathon2015_team7.database.DatabaseHandler;
import com.uet.quantity.uethackathon2015_team7.fragment.DetailFragment;
import com.uet.quantity.uethackathon2015_team7.fragment.QuizFragment;
import com.uet.quantity.uethackathon2015_team7.fragment.SettingFragment;
import com.uet.quantity.uethackathon2015_team7.fragment.TreeFragment;
import com.uet.quantity.uethackathon2015_team7.model.HistoryItem;
import com.uet.quantity.uethackathon2015_team7.receiver.AlarmManagerBroadcastReceiver;
import com.uet.quantity.uethackathon2015_team7.service.MyService;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {


    private AlarmManagerBroadcastReceiver alarm;

    DatabaseHandler db;
    public static final String url = "http://uethackathon07.herokuapp.com/api/histories";
    Gson gson = new Gson();


    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private FragmentManager manager;
    private Toolbar toolbar;
    public static MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DatabaseHandler(this);

        alarm = new AlarmManagerBroadcastReceiver();

        manager = getSupportFragmentManager();
        mainActivity = this;


        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Navigation View must close when any of this item is clicked.
                //To do this we use the closeDrawers() method.
                mDrawer.closeDrawers();

                //Using switch case to identify the ID of the menu item
                // and then performing relevant action.
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        //Action Code Here
                        Toast.makeText(getApplicationContext(), "You Clicked On List Item 1", Toast.LENGTH_SHORT).show();
                        Fragment fragmentHome = DetailFragment.newInstance();
                        manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentHome).addToBackStack(null).commit();
                        navigationView.getMenu().getItem(0).setChecked(true);
                        navigationView.getMenu().getItem(1).setChecked(false);
                        navigationView.getMenu().getItem(2).setChecked(false);
                        navigationView.getMenu().getItem(3).setChecked(false);
                        return true;
                    case R.id.navigation_item_2:
                        Toast.makeText(getApplicationContext(), "You Clicked On List Item 2", Toast.LENGTH_SHORT).show();
                        Fragment fragmentTree = TreeFragment.newInstance();
                        manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentTree).addToBackStack(null).commit();
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(true);
                        navigationView.getMenu().getItem(2).setChecked(false);
                        navigationView.getMenu().getItem(3).setChecked(false);
                        return true;
                    case R.id.navigation_item_3:
                        Toast.makeText(getApplicationContext(), "You Clicked On List Item 3", Toast.LENGTH_SHORT).show();
                        Fragment fragmentQuiz = QuizFragment.newInstance();
                        manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentQuiz).addToBackStack(null).commit();
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(false);
                        navigationView.getMenu().getItem(2).setChecked(true);
                        navigationView.getMenu().getItem(3).setChecked(false);
                        return true;
                    case R.id.navigation_item_4:
                        Toast.makeText(getApplicationContext(), "You Clicked On List Item 4", Toast.LENGTH_SHORT).show();
                        Fragment fragmentCate = SettingFragment.newInstance();
                        manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentCate).addToBackStack(null).commit();
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(false);
                        navigationView.getMenu().getItem(2).setChecked(false);
                        navigationView.getMenu().getItem(3).setChecked(true);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }

            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,  mDrawer, toolbar,
                R.string.app_name, R.string.action_settings
        );
        mDrawer.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        DetailFragment home = new DetailFragment();
        manager.beginTransaction().replace(R.id.container, home).commit();

        startRepeatingTimer();

        if(db.getHistoryCount() == 0){
            getData();
        }

        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);

    }

    public void startRepeatingTimer(){

        alarm.SetAlarm(this);

    }

    public void getData(){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler(){

            ProgressDialog dialog;

            @Override
            public void onStart() {
                super.onStart();
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Loading data");
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if(dialog.isShowing()) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                for (int i =0; i < response.length(); i ++){

                    try {
                        HistoryItem item = gson.fromJson(response.get(i).toString(), HistoryItem.class);
                        db.addHistory(item);
                    }catch (Exception e){

                    }

                }


            }
        });

    }

}
