package com.example.acer.readernew.Activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.acer.readernew.Adapter.MyViewPagerAdapter;
import com.example.acer.readernew.Fragment.CustomFragment;
import com.example.acer.readernew.R;
import com.example.acer.readernew.Utils.DefaultArgue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TabLayout tabMain;
    private ViewPager vpMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ConfigurationUtil.setLocal(getApplicationContext(),ConfigurationUtil.getLocal(getApplicationContext()));
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    private void initData() {
        ShortcutManager manager = getSystemService(ShortcutManager.class);
        List<ShortcutInfo> infos = new ArrayList<>();
        ShortcutInfo collect = new ShortcutInfo.Builder(this,"id1")
                .setShortLabel(getString(R.string.nav_collection))
                .setLongLabel(getString(R.string.nav_collection))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_collections))
                .setIntent(new Intent(this,CollectActivity.class).setAction(Intent.ACTION_VIEW))
                .build();
        ShortcutInfo history = new ShortcutInfo.Builder(this,"id2")
                .setShortLabel(getString(R.string.nav_history))
                .setLongLabel(getString(R.string.nav_history))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_history))
                .setIntent(new Intent(this,HistoryActivity.class).setAction(Intent.ACTION_VIEW))
                .build();
        ShortcutInfo setting = new ShortcutInfo.Builder(this,"id3")
                .setShortLabel(getString(R.string.nav_setting))
                .setLongLabel(getString(R.string.nav_setting))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_settings))
                .setIntent(new Intent(this,Setting.class).setAction(Intent.ACTION_VIEW))
                .build();
        infos.add(collect);
        infos.add(history);
        infos.add(setting);
        manager.setDynamicShortcuts(infos);
    }

    private void initEvent() {

        //仿qq侧划效果
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View content = drawer.getChildAt(0);
                float offset = drawerView.getWidth() * slideOffset;
                content.setTranslationX(offset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //获取TabLayout
        tabMain = (TabLayout) findViewById(R.id.tab_main);
        //设置tab可滑动
        tabMain.setTabMode(TabLayout.MODE_SCROLLABLE);
        //viewPager
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        List<Fragment> fragments = new ArrayList<>();
        CustomFragment head = CustomFragment.newInstance(DefaultArgue.Channel.headline, "HeadlineCacheFile", "HeadlineCache");
        CustomFragment news = CustomFragment.newInstance(DefaultArgue.Channel.news, "NewsCacheFile", "NewsCache");
        CustomFragment finance = CustomFragment.newInstance(DefaultArgue.Channel.finance, "financeCacheFile", "financeCache");
        CustomFragment sport = CustomFragment.newInstance(DefaultArgue.Channel.sport, "sportCacheFile", "sportCache");
        CustomFragment entertainment = CustomFragment.newInstance(DefaultArgue.Channel.entertainment, "entertainmentCacheFile", "entertainmentCache");
        CustomFragment military = CustomFragment.newInstance(DefaultArgue.Channel.military, "militaryCacheFile", "militaryCache");
        CustomFragment education = CustomFragment.newInstance(DefaultArgue.Channel.education, "educationCacheFile", "educationCache");
        CustomFragment technology = CustomFragment.newInstance(DefaultArgue.Channel.technology, "technologyCacheFile", "technologyCache");
        CustomFragment NBA = CustomFragment.newInstance(DefaultArgue.Channel.NBA, "NBACacheFile", "NBACache");
        CustomFragment stock = CustomFragment.newInstance(DefaultArgue.Channel.stock, "stockCacheFile", "stockCache");
        CustomFragment constellation = CustomFragment.newInstance(DefaultArgue.Channel.constellation, "constellationCacheFile", "constellationCache");
        CustomFragment woman = CustomFragment.newInstance(DefaultArgue.Channel.woman, "womanCacheFile", "womanCache");
        CustomFragment health = CustomFragment.newInstance(DefaultArgue.Channel.health, "healthCacheFile", "healthCache");
        CustomFragment parenting = CustomFragment.newInstance(DefaultArgue.Channel.parenting, "parentingCacheFile", "parentingCache");
        fragments.add(head);
        fragments.add(news);
        fragments.add(finance);
        fragments.add(sport);
        fragments.add(entertainment);
        fragments.add(military);
        fragments.add(education);
        fragments.add(technology);
        fragments.add(NBA);
        fragments.add(stock);
        fragments.add(constellation);
        fragments.add(woman);
        fragments.add(health);
        fragments.add(parenting);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), MainActivity.this, fragments);
        vpMain.setAdapter(adapter);
        tabMain.setupWithViewPager(vpMain);
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

        if (id == R.id.nav_homepage) {

        } else if (id == R.id.nav_collection) {
            Intent intent = new Intent(this, CollectActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
