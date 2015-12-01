package com.krod.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

/**
 * 二级列表的适配类
 * Created by jian.wj on 15-11-25.
 */
public class ModeExpandableAdapter extends BaseExpandableListAdapter {
    private ArrayList<ExpandableHolder> list;
    private Context context;
    private ViewManager parentViewManager;
    private ViewManager childViewManager;

    public ModeExpandableAdapter(Context context, ViewManager parentVm, ViewManager childVm) {
        list = new ArrayList<>();
        this.parentViewManager = parentVm;
        this.childViewManager = childVm;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChild().size();
    }

    @Override
    public ExpandableHolder getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public ExpandableHolder getChild(int groupPosition, int childPosition) {
        ArrayList<ExpandableHolder> child = list.get(groupPosition).getChild();
        return child.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpand, View convertView, ViewGroup parent) {
        ExpandableHolder holder;
        if (convertView == null) {
            holder = getGroup(groupPosition);
            convertView = holder.onCreateView(context, parent);
            holder.afterViewCreated();
            convertView.setTag(holder);
        } else {
            holder = (ExpandableHolder) convertView.getTag();
        }
        holder.setIsExpanded(isExpand);
        holder.bindData(groupPosition, getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ExpandableHolder holder;
        if (convertView == null) {
            holder = getChild(groupPosition, childPosition);
            convertView = holder.onCreateView(context, parent);
            holder.afterViewCreated();
            convertView.setTag(holder);
        } else {
            holder = (ExpandableHolder) convertView.getTag();
        }
        holder.setIsLastChild(isLastChild);
        holder.bindData(childPosition, getChild(groupPosition, childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void setList(ArrayList<ExpandableHolder> list) {
        this.list = list;
    }

    @Override
    public int getGroupType(int groupPosition) {
        if (!parentViewManager.viewMap.containsKey(getGroup(groupPosition).getClass())) {
            throw new RuntimeException("The list does not contain the modelView:'" + getGroup(groupPosition).getClass().getName() + "'. Please check the ModelBuilder.");
        }
        return parentViewManager.viewMap.get(getGroup(groupPosition).getClass());
    }

    @Override
    public int getGroupTypeCount() {
        return parentViewManager.viewMap.size();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (!childViewManager.viewMap.containsKey(getChild(groupPosition, childPosition).getClass())) {
            throw new RuntimeException("The list does not contain the modelView:'" + getChild(groupPosition, childPosition).getClass().getName() + "'. Please check the ModelBuilder.");
        }
        return childViewManager.viewMap.get(getChild(groupPosition, childPosition).getClass());
    }

    @Override
    public int getChildTypeCount() {
        return childViewManager.viewMap.size();
    }

}
