package com.krod.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kswording on 15-11-2.
 */
public abstract class DataBindViewHolder<T, B extends ViewDataBinding> extends BaseViewHolder<T>{
    protected B binding;
    public DataBindViewHolder(T t) {
        super(t);
    }
    @Override
    public View createItemView(ViewGroup root) {
        this.binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), itemViewId(),
                root,
                false);
        return binding.getRoot();
    }

    public abstract int itemModeId();

    @Override
    public void bindView() {
        binding.setVariable(itemModeId(), content);
        binding.executePendingBindings();
    }
}
