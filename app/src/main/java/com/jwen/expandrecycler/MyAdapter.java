package com.jwen.expandrecycler;

import android.app.Service;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jwen.expandrecycler.base.OnIClickListener;
import com.jwen.expandrecycler.base.OnItemClickListener;
import com.jwen.expandrecycler.base.OnLongClickListener;
import com.jwen.expandrecycler.base.RecyclerAdapter;
import com.jwen.expandrecycler.swipemenu.OnMenuClickListener;
import com.jwen.expandrecycler.swipemenu.SwipeMenuLayout;
import com.jwen.expandrecycler.swipemenu.SwipeMenuView;

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

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,OnMenuClickListener, View.OnLongClickListener {

        TextView vTitle;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            ((SwipeMenuView)itemView.findViewById(R.id.swipe_menu)).setOnMenuItemClick(this);

            vTitle = (TextView) itemView.findViewById(R.id.tv_item);
            vTitle.setOnClickListener(this);
        }

        public void setTitle(String title){
            vTitle.setText(title);
        }

        @Override
        public void onClick(View v) {
            if(v instanceof SwipeMenuLayout){
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            }else{
                if(onIClickListener != null){
                    onIClickListener.onIClick(getAdapterPosition(),v);
                }
            }
        }

        @Override
        public void onMenuClick(String tag,int position) {
            if(onMenuClickListener != null){
                onMenuClickListener.onMenuClick(tag,getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            Vibrator vib = (Vibrator)v.getContext().getSystemService(Service.VIBRATOR_SERVICE);
//          vib.vibrate(1000);//只震动一秒，一次
            long[] pattern = {0,100};//{静止时长，震动时长，静止时长，震动时长...}
            //第一个是自定义震动模式，第二个是“是否反复震动”,-1 不重复震动
            vib.vibrate(pattern, -1);
            if(onLongClickListener != null){
                onLongClickListener.onLongClick(getAdapterPosition());
            }
            return true;
        }
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    private OnMenuClickListener onMenuClickListener;
    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener){
        this.onMenuClickListener = onMenuClickListener;
    }

    private OnLongClickListener onLongClickListener;
    public void setOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    private OnIClickListener onIClickListener;
    public void setOnIClickListener(OnIClickListener onIClickListener){
        this.onIClickListener = onIClickListener;
    }



}
