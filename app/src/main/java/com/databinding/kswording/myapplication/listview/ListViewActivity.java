package com.databinding.kswording.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.databinding.kswording.myapplication.BaseActivity;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.User;
import com.databinding.kswording.myapplication.databinding.ActivityListviewBinding;
import com.databinding.kswording.myapplication.listview.item.EmptyItemHolder;
import com.databinding.kswording.myapplication.listview.item.NetworkErroHolder;
import com.databinding.kswording.myapplication.listview.item.UserModeHolder;
import com.krod.adapter.ModelAdapter;
import com.krod.adapter.ViewManager;

import java.util.ArrayList;

/**
 * Created by kswording on 15-10-20.
 */
public class ListViewActivity extends BaseActivity {
    public ActivityListviewBinding binding;
    private ModelAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_listview);

        final ArrayList<UserModeHolder> dataList = new ArrayList();
        for (int i = 0; i < 100; i++) {
            dataList.add(new UserModeHolder(new User("name" + i, "friend" + i)));
        }

        binding.listview.setAdapter(adapter = new ModelAdapter(this, ViewManager.begin().
                addModel(UserModeHolder.class).addModel(NetworkErroHolder.class).
                addModel(EmptyItemHolder.class)));

        adapter.setList(dataList);
        Toast.makeText(ListViewActivity.this, "请稍等,看下没有数据的情况", Toast.LENGTH_LONG).show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                adapter.setNullItem(new EmptyItemHolder());//必须设置在setlist之前
//                adapter.setList(new ArrayList<BaseViewHolder>());
//                adapter.notifyDataSetChanged();
//                Toast.makeText(ListViewActivity.this, "请稍等,看下没有网络的情况",Toast.LENGTH_LONG).show();
//            }
//        }, 4000);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                adapter.showAloneItem(new NetworkErroHolder());
//                adapter.notifyDataSetChanged();
//
//            }
//        }, 8000);

    }


    //public void setCurpage(int i) {
     //   Log.e("TAGGG", "SSSSS");
    //}
}
