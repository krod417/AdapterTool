package com.databinding.kswording.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;

import com.databinding.kswording.myapplication.BaseActivity;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.databinding.ActivityListviewBinding;

import java.util.ArrayList;

/**
 * Created by kswording on 15-10-20.
 */
public class ListViewActivity extends BaseActivity {
    public ActivityListviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_listview);
        ArrayList<Class> classList = new ArrayList();
        classList.add(UserModeHolder.class);
        ArrayList<UserModeHolder> dataList = new ArrayList();
        for (int i = 0; i < 30; i ++) {
            dataList.add(new UserModeHolder(new User("name" + i, "friend" + i)));
        }

        binding.setHoldelist(classList);
        binding.setDatalist(dataList);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<UserHolder> dataList = new ArrayList();
                for (int i = 0; i < 10; i ++) {
                    dataList.add(new UserHolder(new User("name" + i, "friendSS" + i)));
                }
                binding.setDatalist(dataList);
            }
        }, 4000);
    }


    //public void setCurpage(int i) {
     //   Log.e("TAGGG", "SSSSS");
    //}
}
