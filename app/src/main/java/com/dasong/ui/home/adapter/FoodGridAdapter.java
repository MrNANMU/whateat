package com.dasong.ui.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasong.R;
import com.dasong.base.view.BaseHolder;

public class FoodGridAdapter extends RecyclerView.Adapter<FoodGridAdapter.Holder>{

    private Context context;

    public FoodGridAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_food,viewGroup,false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class Holder extends BaseHolder{

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
