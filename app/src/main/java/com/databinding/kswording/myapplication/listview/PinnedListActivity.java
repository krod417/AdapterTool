package com.databinding.kswording.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.databinding.kswording.myapplication.BaseActivity;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.databinding.kswording.myapplication.databinding.ActivityPinnedBinding;
import com.databinding.kswording.myapplication.listview.item.ChildViewHolder;
import com.databinding.kswording.myapplication.listview.item.ParentViewHolder;
import com.krod.adapter.BaseViewHolder;
import com.krod.adapter.ViewManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jian.wj on 15-12-1.
 */
public class PinnedListActivity extends BaseActivity {
    private ActivityPinnedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pinned);
        PinnedHolderAdapter adapter;
        binding.listView.getRefreshableView().setShadowVisible(false);
        binding.listView.setAdapter(adapter = new PinnedHolderAdapter(this, ViewManager.begin()
                .addModel(ChildViewHolder.class)
                .addModel(ParentViewHolder.class, true)));//标签一定要加在最后一项
        ArrayList<BaseViewHolder> parent = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ParentViewHolder holder = new ParentViewHolder(new User(i + "wang", "4"));
            parent.add(holder);
            int len = new Random().nextInt(10);
            for (int j = 0; j < len; j++) {
                parent.add(new ChildViewHolder(new User("wang " + j, "name")));
            }
        }
        adapter.setList(parent);
        adapter.notifyDataSetChanged();
    }
}
