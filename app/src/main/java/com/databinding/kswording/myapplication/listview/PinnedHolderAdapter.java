package com.databinding.kswording.myapplication.listview;

import android.content.Context;

import com.hb.views.PinnedSectionListView;
import com.krod.adapter.ModelAdapter;
import com.krod.adapter.ViewManager;


/**
 * @author chenupt create on 2015/10/10
 */
public class PinnedHolderAdapter extends ModelAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

    public PinnedHolderAdapter(Context context, ViewManager manager) {
        super(context, manager);
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType >= 0 ? viewManager.pinnedMap.get(viewType) : false;
    }

}
