package com.uet.quantity.uethackathon2015_team7;

import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
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

        Intent intent1 = getIntent();
        int widgetID = intent1.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);

        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
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
                        Fragment fragmentHome = DetailFragment.newInstance();
                        //manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentHome).addToBackStack(null).commit();
                        navigationView.getMenu().getItem(0).setChecked(true);
                        navigationView.getMenu().getItem(1).setChecked(false);
                        navigationView.getMenu().getItem(2).setChecked(false);
                        navigationView.getMenu().getItem(3).setChecked(false);
                        return true;
                    case R.id.navigation_item_2:
                        Fragment fragmentTree = TreeFragment.newInstance();
                        //manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentTree).addToBackStack(null).commit();
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(true);
                        navigationView.getMenu().getItem(2).setChecked(false);
                        navigationView.getMenu().getItem(3).setChecked(false);
                        return true;
                    case R.id.navigation_item_3:
                        Fragment fragmentQuiz = QuizFragment.newInstance();
                        //manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentQuiz).addToBackStack(null).commit();
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(false);
                        navigationView.getMenu().getItem(2).setChecked(true);
                        navigationView.getMenu().getItem(3).setChecked(false);
                        return true;
                    case R.id.navigation_item_4:
                        Fragment fragmentCate = SettingFragment.newInstance();
                        //manager.popBackStack();
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

        if(widgetID == -1) {
            DetailFragment home = new DetailFragment();
            manager.beginTransaction().replace(R.id.container, home).addToBackStack(null).commit();
            navigationView.getMenu().getItem(0).setChecked(true);
            navigationView.getMenu().getItem(1).setChecked(false);
            navigationView.getMenu().getItem(2).setChecked(false);
            navigationView.getMenu().getItem(3).setChecked(false);
        } else {
            TreeFragment home = new TreeFragment();
            manager.beginTransaction().replace(R.id.container, home).addToBackStack(null).commit();
            navigationView.getMenu().getItem(0).setChecked(false);
            navigationView.getMenu().getItem(1).setChecked(true);
            navigationView.getMenu().getItem(2).setChecked(false);
            navigationView.getMenu().getItem(3).setChecked(false);
        }

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

    @Override
    public void onBackPressed() {
        mDrawer.closeDrawers();
        if(manager.getBackStackEntryCount() == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setMessage("Thoát ứng dụng ?");
            builder.show();
        }
        super.onBackPressed();
    }
}
