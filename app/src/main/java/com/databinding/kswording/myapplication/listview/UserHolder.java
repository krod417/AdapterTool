package com.databinding.kswording.myapplication.listview;

import android.util.Log;
import android.view.View;

import com.databinding.kswording.myapplication.BR;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.databinding.ListItemBinding;
import com.databinding.kswording.myapplication.listview.base.DataBindViewHolder;

/**
 * Created by kswording on 15-10-26.
 */
public class UserHolder extends DataBindViewHolder<User, ListItemBinding> {
    public UserHolder(User user) {
        super(user);
    }

    @Override
    public int itemModeId() {
        return BR.user;
    }

    @Override
    public int itemViewId() {
        return R.layout.list_item;
    }

    @Override
    public void afterViewCreated() {
        binding.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAGGG", "" + getContent().getDisplayName());
            }
        });
    }


}
