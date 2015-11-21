package com.uet.quantity.uethackathon2015_team7;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.uet.quantity.uethackathon2015_team7.fragment.DetailFragment;
import com.uet.quantity.uethackathon2015_team7.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private FragmentManager manager;
    private Toolbar toolbar;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        return true;
                    case R.id.navigation_item_2:
                        Toast.makeText(getApplicationContext(), "You Clicked On List Item 2", Toast.LENGTH_SHORT).show();
                        Fragment fragmentCate = SettingFragment.newInstance();
                        manager.popBackStack();
                        manager.beginTransaction().replace(R.id.container, fragmentCate).addToBackStack(null).commit();
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

    }
}
