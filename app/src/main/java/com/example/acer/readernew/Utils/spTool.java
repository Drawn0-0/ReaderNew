package com.example.acer.readernew.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by acer on 2017/4/22.
 */

public class spTool {
    public static void saveInfo(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).apply();
    }
    public static String getInfo(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }
    public static void putBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).apply();
    }
    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
}
