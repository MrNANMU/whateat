package com.dasong.ui.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dasong.R;
import com.dasong.base.callback.OnSlowlyClickListener;
import com.dasong.base.context.BaseActivity;
import com.dasong.base.context.ToolbarActivity;
import com.dasong.utils.LogUtils;
import com.dasong.utils.StatusbarUtils;

public class MainActivity extends ToolbarActivity {

    @Override
    protected int getToolbarColor() {
        return Color.parseColor("#ffffff");
    }

    @Override
    protected void setStatusBarStyle() {
        StatusbarUtils.asColor(this,Color.parseColor("#ffffff"));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLeftButtonIcon() {
        return R.mipmap.icon_menu;
    }

    @Override
    protected int getRightButtonIcon() {
        return 0;
    }

    @Override
    protected String getToolbarTitle() {
        return "今天吃什么";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_button:

                break;
        }
    }
}
