package com.example.tugassensor2022akbif1_10119023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView botNav;
    ViewPager2 vp2;
    viewPagerAdapter vpAdapter;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        botNav = findViewById(R.id.bottomNavigationView);
        vp2 = findViewById(R.id.viewPager);

        FragmentManager fm = getSupportFragmentManager();
        vpAdapter = new viewPagerAdapter(fm, getLifecycle());
        vp2.setAdapter(vpAdapter);

        if (vp2.getCurrentItem() == 0){
            vp2.setUserInputEnabled(false);
        } else{
            vp2.setUserInputEnabled(true);
        }

        botNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.maps:
                        vp2.setCurrentItem(0);

                        break;
                    case R.id.profile:
                        vp2.setCurrentItem(1);
                        break;
                    case R.id.info:
                        vp2.setCurrentItem(2);
                        break;
                }

                return true;
            }

        });

        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                botNav.getMenu().getItem(position).setChecked(true);
                if(position == 0){
                    vp2.setUserInputEnabled(false);
                }else{
                    vp2.setUserInputEnabled(true);
                }
            }
        });


    }

    public ViewPager2 getViewPager()
    {
        return vp2;
    }
//    private void AttachFragment(Fragment frag)
//    {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction fmt = fm.beginTransaction();
//        fmt.replace(R.id.replaceFrame, frag);
//        fmt.commit();
//    }
}