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
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wj19901117@gmail.com on 2015/10/28.
 * Description : Base list Adapter with some list functions.
 *
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected List<T> list;
    protected Context context;
    
    public BaseListAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public void setList(List<? extends T> list) {
        this.list.clear();
        if (list != null) {
            this.list.addAll(list);
        }
    }

    public List<T> getList(){
        return list;
    }

    public void addList(List<? extends T> list) {
        if(list != null){
            this.list.addAll(list);
        }
    }


    public void addItem(T item){
        this.list.add(item);
    }

    public void clearList(){
        this.list.clear();
    }

    public Context getContext(){
    	return context;
    }
}
