package chencheng.bwie.com.fjd_activity;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import chencheng.bwie.com.fjd_activity.green.GreenDaoManager;

public class MyApp extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mContext=getApplicationContext();
        GreenDaoManager.getInstance();
    }
    public static Context getContext() {
        return mContext;
    }
}
