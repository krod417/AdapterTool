package com.databinding.kswording.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.databinding.ActivityViewpageBinding;
import com.databinding.kswording.myapplication.listview.item.TestAFragment;
import com.databinding.kswording.myapplication.listview.item.TestBFragment;
import com.krod.adapter.ModelPagerAdapter;
import com.krod.adapter.PagerManager;

/**
 * Created by jian.wj on 15-12-1.
 */
public class ModeFragmentActivity extends FragmentActivity {
    private ActivityViewpageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewpage);
        binding.viewpage.setAdapter(
                new ModelPagerAdapter(getSupportFragmentManager(),
                        PagerManager.begin().addFragment(new TestAFragment()).addFragment(new TestBFragment())));
    }
}
