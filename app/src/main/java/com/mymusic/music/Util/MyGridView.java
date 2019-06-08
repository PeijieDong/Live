package com.mymusic.music.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Create By mr.mao in 2019/4/10 13:23
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MyGridView extends GridView {


    public MyGridView(Context context) {
        super(context);
    }
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }



}
