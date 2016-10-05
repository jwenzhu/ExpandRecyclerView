package com.jwen.expandrecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jwen.expandrecycler.base.BaseViewHolder;
import com.jwen.expandrecycler.base.RecyclerAdapter;

import java.util.List;

/**
 * author: Jwen
 * date:2016-09-29.
 */
public class MyAdapter extends RecyclerAdapter<MyAdapter.MyViewHolder> {
    private List<String> strings;

    public MyAdapter(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getItemCount() {
        return strings == null ? 0:strings.size();
    }
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe,null);
    }
    @Override
    public MyAdapter.MyViewHolder onCreateRealViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setTitle(strings.get(position));
    }

    class MyViewHolder extends BaseViewHolder<String> {

        TextView vTitle;


        public MyViewHolder(View itemView) {
            super(itemView);
            vTitle = (TextView) itemView.findViewById(R.id.tv_item);
        }

        @Override
        public void bindItem(Context context, String s) {
            vTitle.setOnClickListener(this);
        }

        public void setTitle(String title){
            vTitle.setText(title);
        }

    }



}
