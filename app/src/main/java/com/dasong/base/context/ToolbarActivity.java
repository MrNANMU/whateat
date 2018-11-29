package com.dasong.base.context;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dasong.R;

import butterknife.BindView;

abstract public class ToolbarActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.left_button)
    public FrameLayout left_button;
    @BindView(R.id.iv_left_button)
    ImageView iv_left_button;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.right_button)
    public FrameLayout right_button;
    @BindView(R.id.iv_right_button)
    ImageView iv_right_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    private void initToolbar(){
        toolbar.setBackgroundColor(getToolbarColor());
        iv_left_button.setImageResource(getLeftButtonIcon());
        tv_title.setText(getToolbarTitle());
        iv_right_button.setImageResource(getRightButtonIcon());
    }

    abstract protected int getToolbarColor();
    abstract protected @DrawableRes int getLeftButtonIcon();
    abstract protected @DrawableRes int getRightButtonIcon();
    abstract protected String getToolbarTitle();
}
