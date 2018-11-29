package com.dasong.ui.home.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasong.R;
import com.dasong.base.callback.OnSlowlyClickListener;
import com.dasong.base.context.BaseActivity;
import com.dasong.base.context.ToolbarActivity;
import com.dasong.ui.home.adapter.FoodGridAdapter;
import com.dasong.utils.BitmapUtils;
import com.dasong.utils.LogUtils;
import com.dasong.utils.PixUtils;
import com.dasong.utils.StatusbarUtils;

import butterknife.BindView;

public class MainActivity extends ToolbarActivity implements DrawerLayout.DrawerListener{

    @BindView(R.id.dl_drawer)
    DrawerLayout dl_drawer;
    @BindView(R.id.rv_img_backround)
    RecyclerView rv_img_backround;
    @BindView(R.id.iv_cover)
    ImageView iv_cover;
    @BindView(R.id.tv_get_food)
    TextView tv_get_food;

    private FoodGridAdapter adapter;
    private GridLayoutManager manager;

    private String currentStatusColor;

    @Override
    protected int getToolbarColor() {
        currentStatusColor = "#ffffff";
        return Color.parseColor(currentStatusColor);
    }

    @Override
    protected void setStatusBarStyle() {
        StatusbarUtils.asColor(this,Color.parseColor("#b2ffffff"));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initDrawer();
        rv_img_backround.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        adapter = new FoodGridAdapter(this);
        manager = new GridLayoutManager(this,3);
        rv_img_backround.setAdapter(adapter);
        rv_img_backround.setLayoutManager(manager);
        Bitmap bitmap = BitmapUtils.fit(R.mipmap.test, PixUtils.dp2px(120),PixUtils.dp2px(120));
        iv_cover.setImageBitmap(BitmapUtils.toCircle(BitmapUtils.rsBlur(bitmap,25)));
    }

    private void initDrawer(){
        dl_drawer.addDrawerListener(this);
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
    public void onDrawerSlide(@NonNull View view, float v) {
        if(v == 0){
            currentStatusColor = "#ffffff";
            StatusbarUtils.asColor(this,Color.parseColor(currentStatusColor));
        }else{
            if("#ffffff".equals(currentStatusColor)){
                currentStatusColor = "#EF881F";
                StatusbarUtils.asColor(this,Color.parseColor(currentStatusColor));
            }
        }
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {

    }

    @Override
    public void onDrawerClosed(@NonNull View view) {

    }

    @Override
    public void onDrawerStateChanged(int i) {

    }
}
