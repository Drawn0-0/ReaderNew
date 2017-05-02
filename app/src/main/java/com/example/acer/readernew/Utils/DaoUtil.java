package com.example.acer.readernew.Utils;

import android.content.Context;

import com.example.acer.readernew.Bean.Collection;
import com.example.acer.readernew.Bean.history;
import com.example.acer.readernew.Dao.CollectionDao;
import com.example.acer.readernew.Dao.historyDao;

import java.util.List;

/**
 * Created by acer on 2017/5/2.
 */

public class DaoUtil {
    public static void addCollection(Context context, Collection bean) {
        BaseApplication.getDaoSession().getCollectionDao().insertOrReplace(bean);
        context.getContentResolver().notifyChange(DefaultArgue.uriCollection, null);
    }

    public static void deleteCollection(Context context, Collection bean) {
        BaseApplication.getDaoSession().getCollectionDao().delete(bean);
        context.getContentResolver().notifyChange(DefaultArgue.uriCollection, null);
    }

    public static void addHistory(Context context, history bean) {
        BaseApplication.getDaoSession().getHistoryDao().insertOrReplace(bean);
        context.getContentResolver().notifyChange(DefaultArgue.uriHistory, null);
    }

    public static void deleteHistory(Context context, history bean) {
        BaseApplication.getDaoSession().getHistoryDao().delete(bean);
        context.getContentResolver().notifyChange(DefaultArgue.uriHistory, null);
    }

    public static List<history> getAllHistory() {
        return BaseApplication.getDaoSession().getHistoryDao().queryBuilder().orderDesc(historyDao.Properties.Id).list();
    }

    public static List<Collection> getAllCollect() {
        return BaseApplication.getDaoSession().getCollectionDao().queryBuilder().orderDesc(CollectionDao.Properties.Id).list();
    }
}
