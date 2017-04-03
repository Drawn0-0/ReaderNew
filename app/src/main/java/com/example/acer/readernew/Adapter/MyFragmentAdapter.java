package com.example.acer.readernew.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by acer on 2017/4/2.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private int count;
    public MyFragmentAdapter(FragmentManager fm,int count) {
        super(fm);
        this.count = count;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return count;
    }
}
