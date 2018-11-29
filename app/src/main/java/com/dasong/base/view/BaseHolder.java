package com.dasong.base.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract public class BaseHolder extends RecyclerView.ViewHolder{

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected <V extends View> V $(int id){
        return (V)itemView.findViewById(id);
    }
}
