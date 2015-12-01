package com.databinding.kswording.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.databinding.kswording.myapplication.BaseActivity;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.databinding.kswording.myapplication.databinding.ActivityExpandlistviewBinding;
import com.databinding.kswording.myapplication.listview.item.ChildViewHolder;
import com.databinding.kswording.myapplication.listview.item.ParentViewHolder;
import com.databinding.kswording.myapplication.listview.item.UserHolder;
import com.krod.adapter.ExpandableHolder;
import com.krod.adapter.ModeExpandableAdapter;
import com.krod.adapter.ViewManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * 二级列表
 * Created by kswording on 15-10-20.
 */
public class ExpandableActivity extends BaseActivity {
    public ActivityExpandlistviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_expandlistview);
        ArrayList<Class> classList = new ArrayList();
        classList.add(UserHolder.class);
        ArrayList<ExpandableHolder> parent = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ParentViewHolder holder = new ParentViewHolder(new User(i + "wang", "4"));
            parent.add(holder);
            int len = new Random().nextInt(10);
            ArrayList<ExpandableHolder> childList = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                childList.add(new ChildViewHolder(new User("wang " + i, "name")));
            }
            holder.setChild(childList);
        }
        ModeExpandableAdapter adapter;
        binding.expandistview.setGroupIndicator(null);
        binding.expandistview.setAdapter(adapter =
                new ModeExpandableAdapter(this,
                        ViewManager.begin().addModel(ParentViewHolder.class),
                        ViewManager.begin().addModel(ChildViewHolder.class)));
        adapter.setList(parent);
    }

}
