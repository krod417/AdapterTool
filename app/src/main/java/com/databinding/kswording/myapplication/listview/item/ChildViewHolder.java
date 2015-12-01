package com.databinding.kswording.myapplication.listview.item;

import android.widget.TextView;

import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.krod.adapter.ExpandableHolder;

/**
 * Created by jian.wj on 15-12-1.
 */
public class ChildViewHolder extends ExpandableHolder<User> {
    public TextView name;

    public ChildViewHolder(User user) {
        super(user);
    }

    public ChildViewHolder() {
        super();
    }


    @Override
    public int itemViewId() {
        return R.layout.expand_child;
    }

    @Override
    public void afterViewCreated() {
        name = (TextView) findViewById(R.id.item_name);
    }

    @Override
    public void bindView(User user) {
        name.setText(user.getFirstName());
    }
}
