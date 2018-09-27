package com.jesse.concurrent;

import android.app.Application;
import android.util.Log;

/**
 * $desc$
 *
 * @author wuzhao
 * @Date 2018/8/31
 * @mail： wuzhao@in66.com
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("test", "Application getColor" + getResources().getColor(R.color.colorPrimary));
    }
}
