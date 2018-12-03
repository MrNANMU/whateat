package com.dasong.ui.home.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasong.R;
import com.dasong.base.context.ToolbarActivity;
import com.dasong.ui.home.adapter.FoodGridAdapter;
import com.dasong.ui.home.view.DrawerHelper;
import com.dasong.utils.AnimUtils;
import com.dasong.utils.BitmapUtils;
import com.dasong.utils.PixUtils;
import com.dasong.utils.StatusbarUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends ToolbarActivity implements DrawerLayout.DrawerListener,DrawerHelper.OnItemChangeListener {

    @BindView(R.id.dl_drawer)
    DrawerLayout dl_drawer;
    /*@BindView(R.id.nv_drawer)
    NavigationView nv_drawer;*/
    @BindView(R.id.v_drawer)
    View v_drawer;
    DrawerHelper helper;
    @BindView(R.id.rv_img_backround)
    RecyclerView rv_img_backround;
    @BindView(R.id.fl_cover_wrap)
    FrameLayout fl_cover_wrap;
    @BindView(R.id.iv_cover)
    ImageView iv_cover;
    @BindView(R.id.tv_get_food)
    TextView tv_get_food;

    private FoodGridAdapter adapter;
    private GridLayoutManager manager;

    private String currentStatusColor;
    private boolean scollable = false;

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
        initListBackground();
        initMainUi();
    }

    private void initDrawer(){
        dl_drawer.addDrawerListener(this);
        helper = DrawerHelper.bind(this,v_drawer);
        helper.setOnItemChangeListener(this);
        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initListBackground(){
        rv_img_backround.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return !scollable;
            }
        });
        adapter = new FoodGridAdapter(this);
        manager = new GridLayoutManager(this,3);
        rv_img_backround.setAdapter(adapter);
        rv_img_backround.setLayoutManager(manager);
    }

    private void initMainUi(){
        Bitmap bitmap = BitmapUtils.fit(R.mipmap.test, PixUtils.dp2px(120),PixUtils.dp2px(120));
        iv_cover.setImageBitmap(BitmapUtils.toCircle(BitmapUtils.rsBlur(bitmap,25)));
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

    @OnClick(R.id.tv_get_food)
    public void getRandomFood(){
        scollable = !scollable;
        anim();

    }

    private void anim(){

        AnimUtils.toss(fl_cover_wrap, rv_img_backround, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tv_get_food.setEnabled(false);
                fl_cover_wrap.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv_get_food.setEnabled(true);
                fl_cover_wrap.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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

    @Override
    public void onItemChanged(List<String> newGrid) {
        //更新数据源
    }

    @Override
    public void onBackPressed() {
        if(dl_drawer.isDrawerOpen(GravityCompat.START)){
            dl_drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
