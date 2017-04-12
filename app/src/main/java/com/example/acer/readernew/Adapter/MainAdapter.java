package com.example.acer.readernew.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.acer.readernew.R;

import java.util.List;

/**
 * Created by acer on 2017/4/2.
 * ViewPager的适配器
 */

public class MainAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private String[] tabTitle;

    public MainAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList) {
        super(fm);
        fragments = fragmentList;
        //tab的标题
        tabTitle = new String[]{context.getString(R.string.Headline_fragment),context.getString(R.string.News_fragment),context.getString(R.string.Finance_fragment),
                context.getString(R.string.Sport_fragment),context.getString(R.string.Entertainment_fragment),context.getString(R.string.Military_fragment),
                context.getString(R.string.Education_fragment),context.getString(R.string.Technology_fragment),context.getString(R.string.NBA_fragment),
                context.getString(R.string.Stock_fragment),context.getString(R.string.Constellation_fragment),context.getString(R.string.Woman_fragment),
                context.getString(R.string.Health_fragment),context.getString(R.string.Parenting_fragment)};
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
