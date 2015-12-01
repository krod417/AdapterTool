package com.databinding.kswording.myapplication.listview;

import android.content.Context;
import android.database.Cursor;

import com.databinding.kswording.myapplication.listview.item.CursorHodler;
import com.databinding.kswording.myapplication.listview.item.TestCursorHodler;
import com.krod.adapter.BaseViewHolder;
import com.krod.adapter.ModeCursorAdapter;

/**
 * Created by jian.wj on 15-11-25.
 */
public class TestCursorAdapter extends ModeCursorAdapter {
    public TestCursorAdapter(Context context, Cursor c, boolean autoRequery, Class<? extends BaseViewHolder> cvh) {
        super(context, c, autoRequery, cvh);
    }

    @Override
    public Class<? extends BaseViewHolder> selectHolder(Cursor cursor) {
        int type = cursor.getInt(cursor.getColumnIndex("groupId"));

        return type % 2 == 0 ? CursorHodler.class : TestCursorHodler.class;
    }

    @Override
    public int selectViewType(Cursor cursor) {
        int type = cursor.getInt(cursor.getColumnIndex("groupId"));

        return type % 2 == 0 ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
