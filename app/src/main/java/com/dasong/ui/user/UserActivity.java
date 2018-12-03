package com.dasong.ui.user;

import android.graphics.Color;
import android.os.Bundle;

import com.dasong.R;
import com.dasong.base.context.ToolbarActivity;
import com.dasong.utils.StatusbarUtils;

public class UserActivity extends ToolbarActivity {
    @Override
    protected int getToolbarColor() {
        return Color.parseColor("#EF881F");
    }

    @Override
    protected int getLeftButtonIcon() {
        return R.mipmap.back;
    }

    @Override
    protected int getRightButtonIcon() {
        return 0;
    }

    @Override
    protected String getToolbarTitle() {
        return "";
    }

    @Override
    protected void setStatusBarStyle() {
        StatusbarUtils.asColor(this,Color.parseColor("#EF881F"));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        back();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
