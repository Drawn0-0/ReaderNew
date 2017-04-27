package com.example.acer.readernew.Utils;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.acer.readernew.Dao.DaoMaster;
import com.example.acer.readernew.Dao.DaoSession;

/**
 * Created by acer on 2017/4/23.
 */

public class BaseApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setDataBase();
    }

    private void setDataBase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "bookDB.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
