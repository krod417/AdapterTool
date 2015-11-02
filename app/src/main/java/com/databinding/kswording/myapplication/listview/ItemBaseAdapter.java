package com.databinding.kswording.myapplication.listview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.databinding.kswording.myapplication.BR;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;

/**
 * Created by kswording on 15-10-20.
 */
public class ItemBaseAdapter extends BaseAdapter {
    public Context context;
    public ItemBaseAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public User getItem(int position) {
        return new User("aaa","sss" + position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding;
        if (convertView == null) {
           binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.list_item,
                    parent,
                    false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ViewDataBinding) convertView.getTag();
        }
        binding.setVariable(BR.user, getItem(position));
        binding.executePendingBindings();
        return convertView;
    }

}
