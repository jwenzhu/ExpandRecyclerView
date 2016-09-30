package com.jwen.expandrecycler.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author: Jwen
 * date:2016-09-29.
 */
public abstract class BaseViewHolder<V>  extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindItem(Context context, V v);
}
