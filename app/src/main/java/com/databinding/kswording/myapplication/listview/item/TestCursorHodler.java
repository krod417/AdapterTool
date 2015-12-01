package com.databinding.kswording.myapplication.listview.item;

import android.database.Cursor;
import android.widget.TextView;

import com.databinding.kswording.myapplication.R;
import com.krod.adapter.BaseViewHolder;

/**
 * Created by kswording on 15-10-26.
 */
public class TestCursorHodler extends BaseViewHolder<Cursor> {

    private TextView name;

    public TestCursorHodler() {
        super();
    }

    @Override
    public int itemViewId() {
        return R.layout.list_item;
    }

    @Override
    public void afterViewCreated() {
        name = (TextView) getView().findViewById(R.id.item_name);
    }

    @Override
    public void bindView(Cursor cursor) {
        name.setText(cursor.getString(cursor.getColumnIndex("groupId")) + "11122222");
    }
}
