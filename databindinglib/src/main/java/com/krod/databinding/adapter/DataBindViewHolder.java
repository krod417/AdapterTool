package com.krod.databinding.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.krod.adapter.BaseViewHolder;
import com.krod.adapter.ModelAdapter;
import com.krod.adapter.ViewManager;

import java.util.ArrayList;

/**
 * Created by krod on 15-11-2.
 */
public abstract class DataBindViewHolder<T, B extends ViewDataBinding> extends BaseViewHolder<T> {
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

    @BindingAdapter({"bind:data", "bind:holder"})
    public static void loadadapter(ListView view, ArrayList<BaseViewHolder> data, ArrayList<Class> holder) {
        ModelAdapter adapter;
        if (view.getAdapter() == null) {
            ViewManager vm = ViewManager.begin();
            for (Class hold : holder) {
                vm.addModel(hold);
            }
            adapter = new ModelAdapter(view.getContext(), vm);
            view.setAdapter(adapter);
            adapter.setList(data);
        } else {
            adapter = (ModelAdapter) view.getAdapter();
            adapter.addList(data);
            adapter.notifyDataSetChanged();
        }

    }
}
