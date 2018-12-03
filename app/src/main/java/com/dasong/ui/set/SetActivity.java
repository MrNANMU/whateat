package com.dasong.ui.set;

import android.graphics.Color;
import android.os.Bundle;

import com.dasong.R;
import com.dasong.base.context.ToolbarActivity;
import com.dasong.utils.StatusbarUtils;

public class SetActivity extends ToolbarActivity {
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
        return null;
    }

    @Override
    protected void setStatusBarStyle() {
        StatusbarUtils.asColor(this,Color.parseColor("#EF881F"));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_set;
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
