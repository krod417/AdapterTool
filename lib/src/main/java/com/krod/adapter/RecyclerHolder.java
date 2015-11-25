package com.krod.adapter;

import android.view.View;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by jian.wj on 15-11-25.
 */
public class RecyclerHolder extends ViewHolder {
    private BaseViewHolder holder;

    public RecyclerHolder(View itemView) {
        super(itemView);
    }

    public void setHolder(BaseViewHolder holder) {
        this.holder = holder;
    }

    public BaseViewHolder getHolder() {
        return holder;
    }
}
