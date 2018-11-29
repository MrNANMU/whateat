package com.dasong.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 适用于Android 5.0+
 */
public class StatusbarUtils {

    public static final int FULLSCREEN = 501;


    public static void fullScreen(Activity activity){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void translucent(Activity activity){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public static void asColor(Activity activity,int color){
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

    }

    /**
     * Android 6.0+可用，第二个参数为true时使用白色文字
     * @param activity
     * @param lightText
     */
    /*public static void setStatusbarTextColor(Activity activity,boolean lightText){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(lightText){
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }else {
                activity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }*/
}
