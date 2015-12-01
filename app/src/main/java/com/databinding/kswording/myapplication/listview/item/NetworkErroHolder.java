package com.databinding.kswording.myapplication.listview.item;

import com.databinding.kswording.myapplication.R;
import com.krod.adapter.BaseViewHolder;

/**
 * Created by jian.wj on 15-12-1.
 */
public class NetworkErroHolder extends BaseViewHolder<Object> {

    public NetworkErroHolder() {
        super();
    }

    public NetworkErroHolder(Object content) {
        super(content);
    }

    @Override
    public int itemViewId() {
        return R.layout.networkerror_item;
    }

    @Override
    public void afterViewCreated() {

    }

    @Override
    public void bindView(Object obj) {

    }
}
