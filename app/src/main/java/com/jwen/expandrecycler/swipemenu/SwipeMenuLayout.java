package com.jwen.expandrecycler.swipemenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.OverScroller;

import com.jwen.expandrecycler.R;


/**
 * author: Jwen
 * date:2016-09-28.
 */
public class SwipeMenuLayout  extends FrameLayout {

    public static int DEFAULT_DURATION = 200;
    private int mMenuViewId = -1;
    private int mContentViewId = -1;
    private int mMenuWidth = 0;
    private boolean mIsMenuOpen = false;
    private OverScroller mScroller;

    public SwipeMenuLayout(Context context) {
        this(context,null);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new OverScroller(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwipeMenuLayout);
        mMenuViewId = typedArray.getResourceId(R.styleable.SwipeMenuLayout_menuViewId,mMenuViewId);
        mContentViewId = typedArray.getResourceId(R.styleable.SwipeMenuLayout_contentViewId,mContentViewId);
        typedArray.recycle();

    }

    private SwipeMenuView vMenu;
    private View vContent;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(mMenuViewId != -1){
            vMenu = (SwipeMenuView) findViewById(mMenuViewId);
        }
        if(mContentViewId != -1){
            vContent = findViewById(mContentViewId);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept =  super.onInterceptTouchEvent(ev);
        float disX = 0;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                disX = startX - ev.getX();

                Log.i("jwen", String.valueOf(disX));
                if(disX > 5){
                    return false;
                }
        }
        return isIntercept;
    }

    float startX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isTouch = super.onTouchEvent(event);

        float disX = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                if(mIsMenuOpen){
                    disX = startX - event.getX() + mMenuWidth;
                }else{
                    disX = startX - event.getX();
                }
                if(disX < 0){
                    disX = 0;
                }else if(disX > mMenuWidth){
                    disX = mMenuWidth;
                }
                scrollTo((int)disX,0);
                break;
            case MotionEvent.ACTION_UP:
                if(mIsMenuOpen){
                    disX = startX - event.getX() + mMenuWidth;
                }else{
                    disX = startX - event.getX();
                }
                if(disX > mMenuWidth/2){
                    smoothOpenMenu();
                }else{
                    smoothCloseMenu();
                }
                return false;
            case MotionEvent.ACTION_CANCEL:
                if (!mScroller.isFinished())
                    mScroller.abortAnimation();
                break;
        }
        return isTouch;
    }




    public void smoothCloseMenu(){
        int scrollX =  getScrollX();
        mScroller.startScroll(-Math.abs(scrollX), 0, Math.abs(scrollX), 0, DEFAULT_DURATION);
        mIsMenuOpen = false;
        invalidate();
    }

    public boolean isMenuOpen(){
        return mIsMenuOpen;
    }

    public void smoothOpenMenu() {
        int scrollX = getScrollX();
        mScroller.startScroll(Math.abs(scrollX), 0, mMenuWidth - Math.abs(scrollX), 0, DEFAULT_DURATION);
        mIsMenuOpen = true;
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
                scrollTo(Math.abs(mScroller.getCurrX()), 0);
                invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mMenuWidth = vMenu.getMeasuredWidth();

        mMenuWidth = vMenu.getMeasuredWidth();

        int contentViewWidth = vContent.getMeasuredWidthAndState();
        int contentViewHeight = vContent.getMeasuredHeightAndState();
        LayoutParams lp = (LayoutParams) vContent.getLayoutParams();
        int start = getPaddingLeft();
        int contentTop = getPaddingTop() + lp.topMargin;
        vContent.layout(start, contentTop, start + contentViewWidth, contentTop + contentViewHeight);

        int menuViewWidth = vMenu.getMeasuredWidthAndState();
        int menuViewHeight = vMenu.getMeasuredHeightAndState();
        LayoutParams menuLp = (LayoutParams) vMenu.getLayoutParams();
        int menuTop = getPaddingTop() + menuLp.topMargin;
        int parentViewWidth = getMeasuredWidthAndState();
        vMenu.layout(parentViewWidth, menuTop, parentViewWidth + menuViewWidth, menuTop + menuViewHeight);


    }

}
