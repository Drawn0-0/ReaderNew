package com.example.acer.readernew.Service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import com.example.acer.readernew.Bean.history;
import com.example.acer.readernew.Utils.BaseApplication;
import com.example.acer.readernew.Utils.DaoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class NewsService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.acer.readernew.Service.action.FOO";
    public static final String ACTION_BAZ = "com.example.acer.readernew.Service.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.acer.readernew.Service.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.acer.readernew.Service.extra.PARAM2";

    public NewsService() {
        super("NewsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String day = intent.getStringExtra(EXTRA_PARAM1);
            List<history> histories = BaseApplication.getDaoSession().getHistoryDao().loadAll();
            for (int i =0;i<histories.size();i++){
                String time = histories.get(i).getTime();
                if (!TextUtils.isEmpty(time)){
                    //有时间
                    SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINESE);
                    try {
                        Date parse = ss.parse(time);
                        long timeLong = parse.getTime();
                        long saveLong = Integer.parseInt(day)*24*60*60;
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis-timeLong>saveLong){
                            DaoUtil.deleteHistory(getApplicationContext(),histories.get(i));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
//            final String action = intent.getAction();
//            if (ACTION_FOO.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionFoo(param1, param2);
//            } else if (ACTION_BAZ.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionBaz(param1, param2);
//            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
