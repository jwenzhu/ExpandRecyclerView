package com.jwen.expandrecycler.swipemenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jwen.expandrecycler.R;


/**
 * author: Jwen
 * date:2016-09-29.
 */
public class SwipeMenuView extends LinearLayout {


    private OnMenuClickListener onMenuItemClick;
    private int DEFAULT_POSITION = -1;
    public void setOnMenuItemClick(OnMenuClickListener onMenuItemClick){
        this.onMenuItemClick = onMenuItemClick;
    }

    private Context mContext;
    private Drawable mDefaultDrawable = getResources().getDrawable(R.drawable.selector_red);

    public SwipeMenuView(Context context) {
        this(context,null);
    }

    public SwipeMenuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        init();
    }


    private void init() {
        Menu add = new Menu("添加",R.drawable.ic_add_black_24dp,R.drawable.selector_red);
        Menu update = new Menu("修改",R.drawable.ic_build_black_24dp,R.drawable.selector_green);
        Menu edit = new Menu("编辑",R.drawable.ic_mode_edit_black_24dp,R.drawable.selector_purple);

        addItem(add,"add");
        addItem(update,"update");
        addItem(edit,"edit");
    }

    private void addItem(final Menu menu, final String tag) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_swipemenu,null);
        if(menu.getDrawableId() == -1){
            view.setBackgroundDrawable(mDefaultDrawable);
        }else{
            view.setBackgroundDrawable(getResources().getDrawable(menu.getDrawableId()));
        }
        ((TextView)view.findViewById(R.id.tv_title)).setText(menu.getTitle());
        ((ImageView)view.findViewById(R.id.iv_icon)).setImageResource(menu.getIconId());
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onMenuItemClick != null){
                    onMenuItemClick.onMenuClick(tag,DEFAULT_POSITION);
                }
            }
        });
        this.addView(view,params);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;
        for(int i = 0; i < getChildCount();i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            width += childWidth;
            height = Math.max(height,childHeight);
        }
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth: width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight: height);
    }


}

