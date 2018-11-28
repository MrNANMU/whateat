package com.dasong.base.callback;

import android.view.View;

/**
 * 抖动过滤
 */
public class OnSlowlyClickListener implements View.OnClickListener{

    private boolean firstClick = true;
    private long lastClickTime;
    public int INTERVAL = 0;

    private View.OnClickListener listener;

    public OnSlowlyClickListener(int interval){
        INTERVAL = interval;
    }

    public OnSlowlyClickListener(int interval,View.OnClickListener listener){
        INTERVAL = interval;
        this.listener = listener;
    }

    /**
     * 有效点击，被拦截的点击无法触发此回调
     * @param v 监听的View
     */
    public void onValidClick(View v){

    }

    /**
     * 点击
     * @param v 监听的View
     * @param intercept 是否被拦截
     */
    public void isIntercepted(View v,boolean intercept){

    }

    @Override
    public void onClick(View v) {
        long clickTime = System.currentTimeMillis();
        if(firstClick){
            lastClickTime = clickTime;
            firstClick = false;
        }
        if(clickTime - lastClickTime < INTERVAL){
            isIntercepted(v,true);
            onValidClick(v);
            if(listener != null){
                listener.onClick(v);
            }
        }else{
            isIntercepted(v,false);
        }
        lastClickTime = clickTime;
    }


}
