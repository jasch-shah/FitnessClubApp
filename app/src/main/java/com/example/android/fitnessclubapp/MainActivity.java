package com.example.android.fitnessclubapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Fragment fragment = new HistoryFragment();
        setFragment(fragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View Header = navigationView.getHeaderView(0);
        TextView Name = Header.findViewById(R.id.nav_header_name);
        TextView Email= Header.findViewById(R.id.nav_header_email);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.getDisplayName()!=null&&user.getEmail()!=null) {
            Name.setText(user.getDisplayName());
            Email.setText(user.getEmail());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        switch (id){
            case R.id.nav_history:
                fragment = new HistoryFragment();
                break;
            case R.id.nav_exercises:
                fragment = new ExerciseFragment();
                break;
            case R.id.nav_sign_out:
                FirebaseAuth.getInstance().signOut();
                DBHelper helper = new DBHelper(MainActivity.this);
                helper.deleteHistory();
                finish();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
        }
        setFragment(fragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        if(fragment == null){
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main,fragment)
                .commit();
    }

}
