package com.dasong.base.callback.recyclerview;

import android.view.View;

public interface OnItemTouchListener {
    void onItemClick(int position,View view);
    void onItemLongClick(int position,View view);
}
