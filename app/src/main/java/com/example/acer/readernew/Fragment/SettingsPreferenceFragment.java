package com.example.acer.readernew.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.acer.readernew.R;


public class SettingsPreferenceFragment extends PreferenceFragmentCompat {

    private Toolbar toolbar;
    private SharedPreferences sp;
    public static final int CLEAR_GLIDE_CACHE_DONE = 1;

    public SettingsPreferenceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting);
        initView();
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        findPreference("in_app_browser").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                sp.edit().putBoolean("in_app_browser", preference.getSharedPreferences().getBoolean("in_app_browser", false)).apply();
                return false;
            }
        });

        //保存历史文章时间
        Preference saving_articles = findPreference("time_of_saving_articles");
        saving_articles.setSummary(getTimeSummary());
        saving_articles.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                setSaveTime(preference, newValue);
                preference.setSummary(getTimeSummary());
                return true;
            }
        });


        //Todo 添加缓存大小显示
        findPreference("clear_glide_cache").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(getContext()).clearDiskCache();
                        Message msg = new Message();
                        msg.what = CLEAR_GLIDE_CACHE_DONE;
                        handler.sendMessage(msg);
                    }
                }).start();
                Glide.get(getContext()).clearMemory();
                return false;
            }
        });

    }

    private void setSaveTime(Preference preference, Object newValue) {
        sp.edit().putString("time_of_saving_articles",(String)newValue).apply();
    }

    private String getTimeSummary() {
        String[] options = getResources().getStringArray(R.array.time_to_save_opts);
        String value = sp.getString("time_of_saving_articles", "3");
        switch (value) {
            case "1":
                return options[0];
            case "5":
                return options[2];
            case "7":
                return options[3];
            default:
                return options[1];
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CLEAR_GLIDE_CACHE_DONE:
                    Snackbar.make(toolbar, "清理完成", Snackbar.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void initView() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    public static SettingsPreferenceFragment newInstance() {
        return new SettingsPreferenceFragment();
    }
    //Todo 添加言语选择
//    private void setLocal(int newValue) {
//        Configuration config = getResources().getConfiguration();
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        if (newValue == 0){
//            config.locale = Locale.CHINESE;
//        } else {
//            config.locale = Locale.ENGLISH;
//        }
//        getResources().updateConfiguration(config,dm);
//        //重启应用
//        Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//
//    }

//    private String getArray(int i){
//        String[] array = getResources().getStringArray(R.array.language_opts);
//        return array[i];
//    }
//    private int getLanguage(){
//        //获取应用语言
//        String language = getContext().getResources().getConfiguration().locale.getLanguage();
//        if (language.equals("zh")){
//            return 0;
//        }else return 1;
//    }
}
