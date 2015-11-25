package com.databinding.kswording.myapplication.listview;

import android.util.Log;
import android.widget.TextView;

import com.databinding.kswording.myapplication.R;
import com.krod.adapter.CursorViewHolder;

/**
 * Created by kswording on 15-10-26.
 */
public class CursorHodler extends CursorViewHolder {

    private TextView name;

    public CursorHodler() {
        super();
    }

    @Override
    public int itemViewId() {
        return R.layout.list_item;
    }

    @Override
    public void afterViewCreated() {
        Log.e("TAG", "1144441");
        name = (TextView) getView().findViewById(R.id.item_name);
    }

    @Override
    public void bindView() {
        super.bindView();
        Log.e("TAG", "111");
        name.setText(content.getString(content.getColumnIndex("groupId")));
    }
}
