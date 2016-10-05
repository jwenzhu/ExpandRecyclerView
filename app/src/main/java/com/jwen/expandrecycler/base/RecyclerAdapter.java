package com.jwen.expandrecycler.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jwen.expandrecycler.R;
import com.jwen.expandrecycler.swipemenu.OnMenuClickListener;
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


    @Override
    public void onBindViewHolder(VH holder, int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder)holder;
        baseViewHolder.setOnIClickListener(onIClickListener);
        baseViewHolder.setOnLongClickListener(onLongClickListener);
        baseViewHolder.setOnMenuClickListener(onMenuClickListener);
        baseViewHolder.setOnItemClickListener(onItemClickListener);
    }



    public OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public OnMenuClickListener onMenuClickListener;
    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener){
        this.onMenuClickListener = onMenuClickListener;
    }

    public OnLongClickListener onLongClickListener;
    public void setOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    public OnIClickListener onIClickListener;
    public void setOnIClickListener(OnIClickListener onIClickListener){
        this.onIClickListener = onIClickListener;
    }



}
