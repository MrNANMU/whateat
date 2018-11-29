package com.dasong.base.context;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.dasong.utils.DialogManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setStatusBarStyle();
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogManager.free(this);
    }

    abstract protected void setStatusBarStyle();

    abstract protected int getContentViewId();

    abstract protected void initView(Bundle savedInstanceState);

    abstract protected void initData(Bundle savedInstanceState);

    public <V extends View> V $(int id){
        return (V)findViewById(id);
    }

    public Drawable findDrawableById(int id){
        return ContextCompat.getDrawable(this,id);
    }

}
