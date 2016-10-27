package com.jwen.expandrecycler.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;


import com.jwen.expandrecycler.swipemenu.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Jwen
 * date:2016-09-29.
 */
public class ExpandRecyclerView extends RecyclerView {

    private SwipeMenuLayout vOpenSwipeMenuLayout;
    private int mOpenSwipeMenuLayoutPosition = -1;
    protected ViewConfiguration mViewConfig;



    public ExpandRecyclerView(Context context) {
        this(context,null);
    }

    public ExpandRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpandRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mViewConfig = ViewConfiguration.get(getContext());
    }


    int startX = 0;
    int startY = 0;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean isIntercepted = super.onInterceptTouchEvent(e);
        int lastX = (int) e.getX();
        int lastY = (int) e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                isIntercepted = false;
                startX = (int) e.getX();
                startY = (int) e.getY();

                int touchingPosition = getChildAdapterPosition(findChildViewUnder(startX, startY));
                if (touchingPosition != mOpenSwipeMenuLayoutPosition && vOpenSwipeMenuLayout != null && vOpenSwipeMenuLayout.isMenuOpen()) {
                    vOpenSwipeMenuLayout.smoothCloseMenu();
                    isIntercepted = true;
                }
                if (isIntercepted) {
                    vOpenSwipeMenuLayout = null;
                    mOpenSwipeMenuLayoutPosition = -1;
                } else {
                    ViewHolder vh = findViewHolderForAdapterPosition(touchingPosition);
                    if (vh != null) {
                        View itemView = getSwipeMenuView(vh.itemView);
                        if (itemView != null && itemView instanceof SwipeMenuLayout) {
                            vOpenSwipeMenuLayout = (SwipeMenuLayout) itemView;
                            mOpenSwipeMenuLayoutPosition = touchingPosition;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isIntercepted = isMenuAction(lastX,lastY,isIntercepted);
            case MotionEvent.ACTION_UP:
                isIntercepted = isMenuAction(lastX, lastY, isIntercepted);
                break;
        }
        return isIntercepted;
    }


    private boolean isMenuAction(int x,int y, boolean defaultValue){
        int disX =  startX - x;
        int disY =  startY - y;
        // swipe action
        if (Math.abs(disX) > mViewConfig.getScaledTouchSlop())
            defaultValue = false;
        // click action
        if (Math.abs(disY) < mViewConfig.getScaledTouchSlop() && Math.abs(disX) < mViewConfig.getScaledTouchSlop())
            defaultValue = false;
        return defaultValue;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
       switch (e.getAction()){
           case MotionEvent.ACTION_DOWN:
               break;
           case MotionEvent.ACTION_MOVE:
               if (vOpenSwipeMenuLayout != null && vOpenSwipeMenuLayout.isMenuOpen()) {
                   vOpenSwipeMenuLayout.smoothCloseMenu();
               }
               break;
           case MotionEvent.ACTION_UP:

               break;

       }

        return super.onTouchEvent(e);
    }

    /**
     *获取滑动按钮
     * @param itemView
     * @return
     */
    private View getSwipeMenuView(View itemView) {
        if (itemView instanceof SwipeMenuLayout) return itemView;
        List<View> unvisited = new ArrayList<>();
        unvisited.add(itemView);
        while (!unvisited.isEmpty()) {
            View child = unvisited.remove(0);
            if (!(child instanceof ViewGroup)) {
                continue;
            }
            if (child instanceof SwipeMenuLayout) return child;
            ViewGroup group = (ViewGroup) child;
            final int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) unvisited.add(group.getChildAt(i));
        }
        return itemView;
    }








}
