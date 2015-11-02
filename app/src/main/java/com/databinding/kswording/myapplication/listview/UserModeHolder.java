package com.databinding.kswording.myapplication.listview;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.krod.adapter.BaseViewHolder;

/**
 * Created by kswording on 15-10-26.
 */
public class UserModeHolder extends BaseViewHolder<User> {

    public TextView name;

    public UserModeHolder(User user) {
        super(user);
    }


    @Override
    public int itemViewId() {
        return R.layout.list_item;
    }

    @Override
    public void afterViewCreated() {
        name = (TextView) getView().findViewById(R.id.item_name);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAGGG", "11111" + content.getFirstName());
            }
        });
    }

    @Override
    public void bindView() {
        name.setText(content.getFirstName());
    }

}
