package com.example.acer.readernew.Fragment;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;

import com.example.acer.readernew.R;

import java.util.Locale;


public class SettingsPreferenceFragment extends PreferenceFragmentCompat {

    private Toolbar toolbar;

    public SettingsPreferenceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting);
        initView();
        //Preference language = findPreference("setting_language");
        //getLocal();
        //language.setSummary();
    }

    private void initView() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    public static SettingsPreferenceFragment newInstance() {
        return new SettingsPreferenceFragment();
    }


    public int getLocal() {
        String language = Locale.getDefault().getLanguage();
        if (language.equals(Locale.CHINESE.toString())) {
            return 0;
        }
        return 1;
    }
}
