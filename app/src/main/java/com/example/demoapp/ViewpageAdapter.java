package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewpageAdapter extends FragmentStatePagerAdapter {
    public ViewpageAdapter(@NonNull FragmentManager fragmentManager,int lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new Home2Fragment();
            case 2:
                return new Home3Fragment();
            default:
                return new HomeFragment();

        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:

                title="home";
                break;
            case 1:
                title="Home2";
                break;
            case 2:
                title="Home3";
                break;
        }
        return title;
    }

}
