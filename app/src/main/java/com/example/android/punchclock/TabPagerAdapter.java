package com.example.android.punchclock;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
            return new TabOne();
        }
        else if(i==1){
            return new TabTwo();
        }
        else if (i == 2) {
            return new TabThree();
        }
        else
            return new ProfileTab();
    }

    @Override
    public int getCount() {
        //Total number of tabs
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return context.getString(R.string.tab_one);
        }
        else if(position == 1){
            return context.getString(R.string.tab_two);
        }
        else if (position == 2) {
            return context.getString(R.string.tab_three);
        }
        else
            return context.getString(R.string.profile_tab);
    }
}
