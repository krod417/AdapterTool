package com.databinding.kswording.myapplication.listview.item;

import com.databinding.kswording.myapplication.R;
import com.krod.adapter.BaseViewHolder;

/**
 * Created by jian.wj on 15-12-1.
 */
public class EmptyItemHolder extends BaseViewHolder<String> {

    public EmptyItemHolder() {
        super();
    }

    public EmptyItemHolder(String content) {
        super(content);
    }

    @Override
    public int itemViewId() {
        return R.layout.empty_item;
    }

    @Override
    public void afterViewCreated() {

    }

    @Override
    public void bindView(String str) {

    }
}
