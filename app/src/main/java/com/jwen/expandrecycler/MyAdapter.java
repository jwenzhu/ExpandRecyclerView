package com.jwen.expandrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jwen.expandrecycler.base.OnIClickListener;
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
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.setTitle(strings.get(position));
        holder.setOnItemClickListener(onIClickListener);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView vTitle;
        OnIClickListener mOnItemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);

            vTitle = (TextView) itemView.findViewById(R.id.tv_item);
        }

        public void setTitle(String title){
            vTitle.setText(title);
        }

        public void setOnItemClickListener(OnIClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }


        @Override
        public void onClick(View v) {
            if(onIClickListener != null){
                onIClickListener.onItemClickListener(getAdapterPosition());
            }
        }
    }

    private OnIClickListener onIClickListener;

    public void setOnItemClickListener(OnIClickListener onIClickListener){
        this.onIClickListener = onIClickListener;
    }


}
