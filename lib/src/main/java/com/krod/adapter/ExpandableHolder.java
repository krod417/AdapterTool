package com.krod.adapter;

import java.util.ArrayList;

/**
 * 当需要使用多级列表时样式需要继承该类
 * Created by jian.wj on 15-11-25.
 */
public abstract class ExpandableHolder<T> extends BaseViewHolder<T> {
    public ExpandableHolder parent;
    public ArrayList<ExpandableHolder> child;
    private boolean isExpanded;
    private boolean isLastChild;

    public ExpandableHolder(T t) {
        super(t);
    }

    public ExpandableHolder() {
        super();
    }

    public ArrayList<ExpandableHolder> getChild() {
        return child;
    }

    public void setParent(ExpandableHolder parent) {
        this.parent = parent;
    }

    public ExpandableHolder getParent() {
        return parent;
    }

    public void setIsExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }

    public void setIsLastChild(boolean isLastChild) {
        this.isLastChild = isLastChild;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public boolean isLastChild() {
        return isLastChild;
    }

    public void setChild(ArrayList<ExpandableHolder> childList) {
        child = childList;
    }
}
