package com.databinding.kswording.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;

import com.databinding.kswording.myapplication.BaseActivity;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.databinding.kswording.myapplication.databinding.ActivityGridviewBinding;
import com.databinding.kswording.myapplication.listview.item.UserHolder;

import java.util.ArrayList;

/**
 * 使用了databinding，目前不要使用这种
 * Created by kswording on 15-10-20.
 */
public class GridViewActivity extends BaseActivity {
    public ActivityGridviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_gridview);
        ArrayList<Class> classList = new ArrayList();
        classList.add(UserHolder.class);
        ArrayList<UserHolder> dataList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            dataList.add(new UserHolder(new User("name" + i, "friend" + i)));
        }

        binding.setHoldelist(classList);
        binding.setDatalist(dataList);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<UserHolder> dataList = new ArrayList();
                for (int i = 0; i < 10; i++) {
                    dataList.add(new UserHolder(new User("name" + i, "friendSS" + i)));
                }
                binding.setDatalist(dataList);
            }
        }, 4000);
    }

}
