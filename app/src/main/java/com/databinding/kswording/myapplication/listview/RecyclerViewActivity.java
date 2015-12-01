package com.databinding.kswording.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.databinding.kswording.myapplication.BaseActivity;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.databinding.kswording.myapplication.databinding.ActivityRecyclerviewBinding;
import com.databinding.kswording.myapplication.listview.item.UserModeHolder;
import com.krod.adapter.ModeRecyclerAdapter;
import com.krod.adapter.ViewManager;

import java.util.ArrayList;

/**
 * Created by jian.wj on 15-11-25.
 */
public class RecyclerViewActivity extends BaseActivity {
    ActivityRecyclerviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview);
        binding.listview.setLayoutManager(new LinearLayoutManager(this));
        ModeRecyclerAdapter adapter = new ModeRecyclerAdapter(this, ViewManager.begin().addModel(UserModeHolder.class));
        ArrayList<UserModeHolder> dataList = new ArrayList();
        for (int i = 0; i < 100; i++) {
            dataList.add(new UserModeHolder(new User("name" + i, "friend" + i)));
        }
        adapter.setList(dataList);
        binding.listview.setAdapter(adapter);

    }
}
