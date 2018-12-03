package com.dasong.ui.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasong.R;
import com.dasong.base.callback.recyclerview.OnItemClickListener;
import com.dasong.base.view.BaseHolder;
import com.dasong.utils.PixUtils;

import java.util.List;

import cn.bmob.v3.b.V;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Holder>{

    private Context context;
    private List<String> menu;
    private OnItemClickListener listener;
    private boolean isEdited = false;
    private int editedPos = -1;

    public MenuAdapter(Context context,List<String> menu){
        this.context = context;
        this.menu = menu;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_drawer,viewGroup,false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tv_item_name.setText(menu.get(i));
    }

    @Override
    public int getItemCount() {
        return menu == null ? 0 : menu.size();
    }

    public void setOnItemClickListener(OnItemClickListener l){
        listener = l;
    }

    public boolean isEdited(){
        return isEdited;
    }

    public void cencelEdited(){
        if(isEdited){
            notifyDataSetChanged();
        }
    }

    class Holder extends BaseHolder implements View.OnClickListener,View.OnLongClickListener{

        TextView tv_item_name;
        TextView tv_cancel;
        ImageView iv_remove;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_item_name = $(R.id.tv_item_name);
            tv_cancel = $(R.id.tv_cancel);
            iv_remove = $(R.id.iv_remove);
            itemView.setOnClickListener(this);
            itemView.setLongClickable(true);
            itemView.setOnLongClickListener(this);
            tv_cancel.setOnClickListener(this);
            iv_remove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_remove:
                    isEdited = false;
                    tv_cancel.setVisibility(View.GONE);
                    iv_remove.setVisibility(View.GONE);
                    menu.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    editedPos = -1;
                    break;
                case R.id.tv_cancel:
                    isEdited = false;
                    iv_remove.setVisibility(View.GONE);
                    itemView.setElevation(0);
                    tv_cancel.setVisibility(View.GONE);
                    editedPos = -1;
                    break;
                default:
                    if(listener != null && !isEdited){
                        listener.onItemClick(getLayoutPosition(),v);
                    }
                    break;
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(getAdapterPosition() == 0){
                return false;
            }
            v.setElevation(PixUtils.dp2px(16));
            tv_cancel.setVisibility(View.VISIBLE);
            iv_remove.setVisibility(View.VISIBLE);
            isEdited = true;
            editedPos = getAdapterPosition();
            return true;
        }
    }
}
