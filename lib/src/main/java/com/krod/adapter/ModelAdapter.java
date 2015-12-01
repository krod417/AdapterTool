/*
 * Copyright 2015 krod
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.krod.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 支持AbsListView的适配，其中可以设置null数据的item，以及单独item（比如网络加载失败展示的界面）
 * Created by wj19901117@gmail.com on 2015/10/28.
 * Description : Simple base list adapter for getting multiple item views in list.
 */
public class ModelAdapter extends BaseListAdapter<BaseViewHolder> {

    public ViewManager viewManager;
    public BaseViewHolder nullHolder;
    public ModelAdapter(Context context, ViewManager manager) {
        super(context);
        this.viewManager = manager;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder viewHolder;
        if(view == null) {
            viewHolder = getItem(i);
            view = viewHolder.onCreateView(context,viewGroup);
            view.setTag(viewHolder);
        } else {
            viewHolder = (BaseViewHolder)view.getTag();
        }
        viewHolder.bindData(i, getItem(i));
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if( !viewManager.viewMap.containsKey(getItem(position).getClass())){
            throw new RuntimeException("The list does not contain the modelView:'" + getItem(position).getClass().getName() + "'. Please check the ModelBuilder.");
        }
        return viewManager.viewMap.get(getItem(position).getClass());
    }

    @Override
    public int getViewTypeCount() {
        return viewManager.viewMap.size();
    }



    /**
     * get the tag item at the start.
     * @param list  list data
     * @param tag   tag value
     * @return      item model
     */
    public BaseViewHolder getStartItemByTag(List<BaseViewHolder> list, String tag){
        for (BaseViewHolder entity : list) {
            if (entity.getTag().equals(tag)){
                return entity;
            }
        }
        return null;
    }

    /**
     * get the tag item at the end.
     * @param list  list data
     * @param tag   tag value
     * @return      item model
     */
    public BaseViewHolder getEndItemByTag(List<BaseViewHolder> list, String tag){
        Collections.reverse(list);
        for (BaseViewHolder entity : list) {
            if (entity.getTag().equals(tag)){
                Collections.reverse(list);
                return entity;
            }
        }
        return null;
    }

    /**
     * 设置样式跟内容绑定的数据列表
     *
     * @param list
     */
    @Override
    public void setList(List<? extends BaseViewHolder> list) {
        super.setList(list);
        if (list == null || list.size() <= 0) {
            if (nullHolder != null) {
                addItem(nullHolder);
            }
        }
    }

    /**
     * 设置数据内容
     * @param c item样式
     * @param list 内容数据列表
     * @param <T> 内容类型
     */
    public <T> void setList(Class<? extends BaseViewHolder> c, ArrayList<T> list) {
        setList(AdapterUtil.setList(c, list));
    }

    /**
     * 追加数据
     * @param c item的样式
     * @param list 内容数据列表
     * @param <T> 内容类型
     */
    public <T> void addList(Class<? extends BaseViewHolder> c, ArrayList<T> list) {
        super.addList(AdapterUtil.setList(c, list));
    }

    /**
     * 设置当没有数据时加载的item
     * @param holder
     */
    public void setNullItem(BaseViewHolder holder) {
        nullHolder = holder;
    }

    /**
     * 设置单个样式的item，比如网络加载失败显示的样式
     *
     * @param holder
     */
    public void showAloneItem(BaseViewHolder holder) {
        clearList();
        addItem(holder);
    }
}
