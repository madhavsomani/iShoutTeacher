package com.madhav.ishoutteacher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        // Proceed with initialization
                    }

                    @Override
                    public void onDenied(String permission) {
                        // Notify the user that you need all of the permissions
                    }
                });


        getSupportActionBar().setElevation(0);

        Fragment fm = new home();
        FragmentManager manger = getFragmentManager();
        manger.beginTransaction().replace(R.id.container, fm).commit();






        //Need to Study This

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView name = (TextView) header.findViewById(R.id.textView1);
        TextView work = (TextView) header.findViewById(R.id.textView2);
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);

        String str1 = spf.getString("strname", "");
        String str2 = spf.getString("strwork" , "");

        if(!str1.equals("") && !str2.equals("") ) {
            name.setText(str1);
            work.setText(str2);
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                this.finish();
            } else {
                getFragmentManager().popBackStack();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {

           this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.home) {
            Fragment fm = new home();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container,fm).commit();

        } else if (id == R.id.directattendence) {


            String str[] = new String[2];

            str[1]="lol";
            str[0]="lol2";

            Bundle b = new Bundle();
            b.putStringArray("wifistring", str);

            Fragment fm = new recive_shouts();
            FragmentManager manger = getFragmentManager();
            fm.setArguments(b);
            manger.beginTransaction().replace(R.id.container, fm).commit();


        } else if (id == R.id.database) {

            Fragment fm = new displaydata();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container, fm).addToBackStack(null).commit();

        } else if (id == R.id.settings) {

            Fragment fm = new settings();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container, fm).commit();


        } else if (id == R.id.Timetable) {

            Fragment fm = new timetable();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container,fm).commit();
        }

         else if (id == R.id.serversetup) {

            Fragment fm = new serversetuplock();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container,fm).commit();

        } else if (id == R.id.quicksend) {

            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

        }

       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == true)
        {
            Toast.makeText(getApplicationContext(), "wifi disabled!", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(false);
        }
    }




}
