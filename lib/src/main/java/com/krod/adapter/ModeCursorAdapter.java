package com.krod.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by jian.wj on 15-11-24.
 */
public class ModeCursorAdapter extends CursorAdapter {
    public Class<? extends CursorViewHolder> holder;
    public Context context;


    public ModeCursorAdapter(Context context, Cursor c, boolean autoRequery, Class<? extends CursorViewHolder> cvh) {
        super(context, c, autoRequery);
        this.context = context;
        this.holder = cvh;
    }

    public ModeCursorAdapter(Context context, Cursor c, int flags, Class<? extends CursorViewHolder> cvh) {
        super(context, c, flags);
        this.context = context;
        this.holder = cvh;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = null;
        try {
            CursorViewHolder cvh = selectHolder(cursor).newInstance();
            v = cvh.onCreateView(context, viewGroup);
            cvh.afterViewCreated();
            v.setTag(cvh);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CursorViewHolder cvh = (CursorViewHolder) view.getTag();
        int position = cursor.getPosition();
        cvh.setContent(cursor);
        cvh.bindData(position, cvh);
    }

    @Override
    public int getItemViewType(int position) {
        return selectViewType((Cursor) getItem(position));
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    public int selectViewType(Cursor cursor) {
        return 0;
    }

    public Class<? extends CursorViewHolder> selectHolder(Cursor cursor) {
        return holder;
    }
}
