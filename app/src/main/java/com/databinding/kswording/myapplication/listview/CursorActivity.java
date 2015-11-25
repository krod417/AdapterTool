package com.databinding.kswording.myapplication.listview;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.com.example.testsql.GroupMode;
import com.databinding.kswording.myapplication.BaseActivity;
import com.databinding.kswording.myapplication.R;
import com.databinding.kswording.myapplication.databinding.ActivityCursorlistviewBinding;
import com.krod.database.DBHelper;

/**
 * Created by jian.wj on 15-11-24.
 */
public class CursorActivity extends BaseActivity {
    public ActivityCursorlistviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cursorlistview);
        Cursor cursor = getCursor();
        Log.e("TAGGG", "2222333" + cursor.getCount());
        binding.curListview.setAdapter(new TestCursorAdapter(this, cursor, false, CursorHodler.class));
    }

    public Cursor getCursor() {
        Log.e("TAGGG", "2222");
        return DBHelper.getInstance().listCursor(GroupMode.class);
    }
}
