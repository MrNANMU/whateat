package com.dasong.base.context;

import android.app.Application;

import com.dasong.utils.LogUtils;

public class App extends Application{

    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        LogUtils.allowLog(true);
    }

    public static App getApplication(){
        return INSTANCE;
    }

}
