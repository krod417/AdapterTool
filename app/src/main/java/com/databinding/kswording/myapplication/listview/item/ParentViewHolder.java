package com.databinding.kswording.myapplication.listview.item;

import android.util.Log;
import android.widget.TextView;

import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.krod.adapter.ExpandableHolder;

/**
 * Created by jian.wj on 15-12-1.
 */
public class ParentViewHolder extends ExpandableHolder<User> {
    public TextView name;

    public ParentViewHolder(User user) {
        super(user);
    }

    public ParentViewHolder() {
        super();
    }

    @Override
    public int itemViewId() {
        return R.layout.expand_parent;
    }

    @Override
    public void afterViewCreated() {
        name = (TextView) findViewById(R.id.item_name);
    }

    @Override
    public void bindView(User user) {
        Log.e("TAGGG", "" + user.getFirstName() + "  " + viewPosition);
        name.setText(user.getFirstName());
    }
}
