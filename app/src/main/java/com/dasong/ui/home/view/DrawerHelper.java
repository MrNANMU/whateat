package com.dasong.ui.home.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dasong.R;
import com.dasong.base.callback.recyclerview.OnItemClickListener;
import com.dasong.ui.home.activity.MainActivity;
import com.dasong.ui.home.adapter.MenuAdapter;
import com.dasong.ui.set.SetActivity;
import com.dasong.ui.user.UserActivity;
import com.dasong.utils.BitmapUtils;
import com.dasong.utils.PixUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.b.I;

public class DrawerHelper implements View.OnClickListener,OnItemClickListener{

    private Activity activity;
    private View drawer;
    private NavigationView navigationView;
    private LinearLayout ll_user_wrap;
    private ImageView iv_user_img;
    private TextView tv_user_name;
    private RecyclerView rv_items;
    private ImageView iv_set;
    private TextView tv_set;
    private ImageView iv_add;

    private MenuAdapter adapter;
    private List<String> menu;
    private OnItemChangeListener itemChangeListener;

    private DrawerHelper(){}

    public static DrawerHelper bind(Activity activity,NavigationView navigationView){
        DrawerHelper dh = new DrawerHelper();
        dh.activity = activity;
        dh.navigationView = navigationView;
        dh.init();
        return dh;
    }

    public static DrawerHelper bind(Activity activity,View drawer){
        DrawerHelper dh = new DrawerHelper();
        dh.activity = activity;
        dh.drawer = drawer;
        dh.init2();
        return dh;
    }

    private void init(){
        initView();
        initListeners();
        initDrawer();

        initData();
    }

    private void init2(){
        initView2();
        initListeners();
        initDrawer();
        initData();
    }

    private void initView(){
        View header = navigationView.getHeaderView(0);
        ll_user_wrap = header.findViewById(R.id.ll_user_wrap);
        iv_user_img = header.findViewById(R.id.iv_user_img);
        tv_user_name = header.findViewById(R.id.tv_user_name);
        rv_items = header.findViewById(R.id.rv_items);
        iv_set = header.findViewById(R.id.iv_set);
        tv_set = header.findViewById(R.id.tv_set);
        iv_add = header.findViewById(R.id.iv_add);
    }

    private void initView2(){
        ll_user_wrap = drawer.findViewById(R.id.ll_user_wrap);
        iv_user_img = drawer.findViewById(R.id.iv_user_img);
        tv_user_name = drawer.findViewById(R.id.tv_user_name);
        rv_items = drawer.findViewById(R.id.rv_items);
        iv_set = drawer.findViewById(R.id.iv_set);
        tv_set = drawer.findViewById(R.id.tv_set);
        iv_add = drawer.findViewById(R.id.iv_add);
    }

    private void initListeners(){
        ll_user_wrap.setOnClickListener(this);
        iv_set.setOnClickListener(this);
        tv_set.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    private void initDrawer(){
        menu = new ArrayList<>();
        menu.add("推荐");
        menu.add("公司周围");
        adapter = new MenuAdapter(activity,menu);
        adapter.setOnItemClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_items.setAdapter(adapter);
        rv_items.setLayoutManager(manager);
    }

    private void initData(){
        getUserInfoSuccess();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_user_wrap:
                startActivity(UserActivity.class);
                break;
            case R.id.iv_set:
            case R.id.tv_set:
                startActivity(SetActivity.class);
                break;
            case R.id.iv_add:

                break;
        }
    }

    @Override
    public void onItemClick(int position, View view) {
        /**
         * 网络请求，成功后调用下面方法
         */
        if(itemChangeListener != null){
            itemChangeListener.onItemChanged(null);
        }
    }

    private void startActivity(Class actClass){
        activity.startActivity(new Intent(activity, actClass));
    }

    public void add(String menuName){
        menu.add(menuName);
        adapter.notifyDataSetChanged();
    }

    public void setOnItemChangeListener(OnItemChangeListener l){
        itemChangeListener = l;
    }

    public boolean isEdited(){
        return adapter.isEdited();
    }

    //模拟网络请求后设置用户头像，背景以及用户名
    public void getUserInfoSuccess(){
        Bitmap bitmap = BitmapUtils.fit(R.mipmap.test, PixUtils.dp2px(80),PixUtils.dp2px(80));
        iv_user_img.setImageBitmap(BitmapUtils.toCircle(bitmap));
        BitmapDrawable bitmapDrawable = new BitmapDrawable(BitmapUtils.rsBlur(bitmap,25));
        ll_user_wrap.setBackground(bitmapDrawable);
        tv_user_name.setText("测试用户");
    }

    public interface OnItemChangeListener{
        void onItemChanged(List<String> newGrid);
    }

}
