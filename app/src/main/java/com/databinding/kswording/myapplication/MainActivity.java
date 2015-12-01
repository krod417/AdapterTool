package com.databinding.kswording.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.com.example.testsql.HermesSqlHelper;
import com.databinding.kswording.myapplication.databinding.ActivityMainBinding;
import com.databinding.kswording.myapplication.listview.CursorActivity;
import com.databinding.kswording.myapplication.listview.ExpandableActivity;
import com.databinding.kswording.myapplication.listview.GridViewActivity;
import com.databinding.kswording.myapplication.listview.ListViewActivity;
import com.databinding.kswording.myapplication.listview.ModeFragmentActivity;
import com.databinding.kswording.myapplication.listview.PinnedListActivity;
import com.databinding.kswording.myapplication.listview.RecyclerViewActivity;
import com.databinding.kswording.myapplication.listview.TypeCursorActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertData();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.content.listview.setText("GOlist");
        binding.setMain(this);

    }

    public void insertData() {
        HermesSqlHelper.createDatabase(this, "wj");
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        long startTime = System.currentTimeMillis();
//                        DBHelper.getInstance().startTransaction();
//                        GroupMode temp = null;
//                        GroupMode temp1 = null;
//                        for (int i = 0; i < 110; i++) {
//                            GroupMode person = new GroupMode(i);
//                            if (i == 50) {
//                                temp = person;
//                            }
//                            if (i == 100) {
//                                temp1 = person;
//                            }
//                            DBHelper.getInstance().insert(person);
//                        }
//                        DBHelper.getInstance().successfulTransaction();
//                        DBHelper.getInstance().endTransaction();
//                        Log.e("TAGG", "1111");
//                        temp.groupName = "testupdate";
//                        DBHelper.getInstance().update(temp);
//                        DBHelper.getInstance().delete(temp1);
//                        Log.e("TAGG", "11112222");
//                        ArrayList<GroupMode> list = DBHelper.getInstance().list(GroupMode.class);
//                        Log.e("TAGG", "111122223333" + list.size());
//                        int len = list.size();
//                        for (int i = 0; i < len; i ++) {
//                            GroupMode gm = list.get(i);
//                            Log.e("TAGGGG", "" + gm.groupId + " name" + gm.groupName + " " + gm.test);
//                        }
//                        long endTime = System.currentTimeMillis();
//                        Log.e("result",""+(endTime - startTime));
//                    }
//                }).start();
//            }
//        }, 3000);
    }

    public void goList(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void goGrid(View view) {
        startActivity(new Intent(this, GridViewActivity.class));
    }

    public void goCursor(View view) {
        startActivity(new Intent(this, CursorActivity.class));
    }

    public void goRecycler(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void goExpand(View view) {
        startActivity(new Intent(this, ExpandableActivity.class));
    }

    public void gofragment(View view) {
        startActivity(new Intent(this, ModeFragmentActivity.class));
    }

    public void goTypeCursor(View view) {
        startActivity(new Intent(this, TypeCursorActivity.class));
    }

    public void goPinned(View view) {
        startActivity(new Intent(this, PinnedListActivity.class));
    }

}
