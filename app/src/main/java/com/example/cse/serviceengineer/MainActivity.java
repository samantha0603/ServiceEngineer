package com.example.cse.serviceengineer;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Fragment_home1 fragment_home1;
    Fragment_status fragment_status;
    Fragment_report fragment_report;



    public BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view =getSupportActionBar().getCustomView();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, 100);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.SEND_SMS}, 100);

fragment_home1=new Fragment_home1();
fragment_status=new Fragment_status();
fragment_report=new Fragment_report();

        //logo
       // Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setLogo(R.drawable.logo);
      // getSupportActionBar().setDisplayUseLogoEnabled(true);

        setupBottomNavigation();

        if (savedInstanceState == null) {

           loaddFragment(fragment_home1);
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }

    //more options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dropdown,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.drop1:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
            case R.id.drop2:
                SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.remove("username");
                edit.remove("password");
                edit.remove("logined");
                edit.commit();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
                break;
            case R.id.drop3:
                startActivity(new Intent(getApplicationContext(),Password.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBottomNavigation() {

        mBottomNavigationView =  findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        loaddFragment(fragment_home1);
                        return true;

                    case R.id.status:
                        loaddFragment(fragment_status);
                        return true;
                    case R.id.report:
                        loaddFragment(fragment_report);
                        return true;

                }
                return false;
            }
        });
    }

    private void loaddFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.fragment_frame, fragment).commit();
    }


}