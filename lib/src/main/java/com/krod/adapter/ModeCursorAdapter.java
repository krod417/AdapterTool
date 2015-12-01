package com.krod.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * 支持适配CursorAdapter类型，cursor的样式item需要个默认的构造函数
 * Created by jian.wj on 15-11-24.
 */
public class ModeCursorAdapter extends CursorAdapter {
    public Class<? extends BaseViewHolder> holder;
    public Context context;

    /**
     * @param context
     * @param c
     * @param autoRequery
     * @param cvh         默认的样式
     */
    public ModeCursorAdapter(Context context, Cursor c, boolean autoRequery, Class<? extends BaseViewHolder> cvh) {
        super(context, c, autoRequery);
        this.context = context;
        this.holder = cvh;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = null;
        try {
            BaseViewHolder cvh = selectHolder(cursor).newInstance();
            v = cvh.onCreateView(context, viewGroup);
            cvh.afterViewCreated();
            v.setTag(cvh);
        } catch (InstantiationException e) {
            throw new RuntimeException("BaseViewHolder Missing default constructor");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("BaseViewHolder Missing default constructor");
        }
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        BaseViewHolder cvh = (BaseViewHolder) view.getTag();
        int position = cursor.getPosition();
        cvh.setContent(cursor);
        cvh.bindData(position, cvh);
    }

    @Override
    public int getItemViewType(int position) {
        return selectViewType((Cursor) getItem(position));
    }

    /**
     * 如果需要支持多种样式，则需要重载这个方法
     * 设置样式数量
     * @param
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    /**
     * 如果需要支持多种样式，则需要重载这个方法
     * 根据cursor选取对应的Type
     * @param cursor
     * @return
     */
    public int selectViewType(Cursor cursor) {
        return 0;
    }

    /**
     * 如果需要支持多种样式，则需要重载这个方法
     * 根据cursor选取样式
     *
     * @param cursor
     * @return
     */
    public Class<? extends BaseViewHolder> selectHolder(Cursor cursor) {
        return holder;
    }
}
