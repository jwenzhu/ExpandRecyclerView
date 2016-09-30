package com.jwen.expandrecycler.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jwen.expandrecycler.R;
import com.jwen.expandrecycler.swipemenu.SwipeMenuLayout;
import com.jwen.expandrecycler.swipemenu.SwipeMenuView;


/**
 * author: Jwen
 * date:2016-09-29.
 */
public abstract class RecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        View contentView = onCreateContentView(parent, viewType);
        SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_swipe, parent, false);
        SwipeMenuView menu = (SwipeMenuView) swipeMenuLayout.findViewById(R.id.swipe_menu);
        FrameLayout content = (FrameLayout) swipeMenuLayout.findViewById(R.id.swipe_content);
        content.addView(contentView);
        contentView = swipeMenuLayout;
        return onCreateRealViewHolder(contentView,viewType);
    }

    /**
     * content布局
     * @param parent
     * @param viewType
     * @return
     */
    public abstract View onCreateContentView(ViewGroup parent, int viewType);

    /**
     * 创建viewHolder
     * @param realContentView
     * @param viewType
     * @return
     */
    public abstract VH onCreateRealViewHolder(View realContentView, int viewType);



}
