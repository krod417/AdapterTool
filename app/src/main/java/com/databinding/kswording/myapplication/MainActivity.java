package com.databinding.kswording.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.databinding.kswording.myapplication.databinding.ActivityMainBinding;
import com.databinding.kswording.myapplication.listview.GridViewActivity;
import com.databinding.kswording.myapplication.listview.ListViewActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.content.listview.setText("GOlist");
        binding.setMain(this);
    }

    public void goList(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void goGrid(View view) {
        startActivity(new Intent(this, GridViewActivity.class));
    }

}
