package com.xdx.dllo.beautygoodsdemo.main;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by dllo on 16/7/21.
 */
public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Bmob.initialize(this, "dd2e106f9bc1821a2ab933daed4cef77");
    }

}
