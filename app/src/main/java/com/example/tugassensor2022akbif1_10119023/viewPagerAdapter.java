package com.example.tugassensor2022akbif1_10119023;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
 */

public class viewPagerAdapter extends FragmentStateAdapter {

    private MainActivity ma;

    public viewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 1:
                return new ProfileFragment();
            case 2:
                return new InfoFragment();
            default:
                return new MapsActivity();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
