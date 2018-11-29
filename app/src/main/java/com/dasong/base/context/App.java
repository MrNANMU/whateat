package com.dasong.base.context;

import android.app.Application;

import com.dasong.utils.LogUtils;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;

public class App extends Application{

    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        LogUtils.allowLog(true);
        Bmob.initialize(this, "f18f00531bb9ae42a2ff43485fd44839");
    }

    public static App getApplication(){
        return INSTANCE;
    }

}
