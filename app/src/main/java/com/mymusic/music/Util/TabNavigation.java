package com.mymusic.music.Util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mymusic.music.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By mr.mao in 2019/8/11 22:16
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class TabNavigation extends LinearLayout implements View.OnClickListener {
    private List<View> TabView;//保存Tab背景
    private List<Tab> Tabs;//保存tab
    private Context context;
    private OnTabCheckListener tabCheckListener;
    private int currentPosition=0;

    public TabNavigation(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TabNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public TabNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabNavigation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    //对图标的点击事件接口
    public interface OnTabCheckListener{
        void onTabSelected(View v, int position);
    }

    public void setOnTabChechListener(OnTabCheckListener onTabChechListener){
        this.tabCheckListener = onTabChechListener;
    }

    //初始化参数
    private void init(){
        setOrientation(HORIZONTAL);
        Tabs = new ArrayList<>();
        TabView = new ArrayList<>();
    }

    //添加Tab的方法
    public void addTab(Tab tab){
        View view = LayoutInflater.from(context).inflate(R.layout.tabnavigation_layout, null);
        TextView btText = view.findViewById(R.id.btText);
        btText.setText(tab.getText());

        view.setTag(Tabs.size());
        view.setOnClickListener(this);
        //将tab、view添加至集合中
        Tabs.add(tab);
        TabView.add(view);

        addView(view);
    }
    //图标的点击事件
    @Override
    public void onClick(View view) {
        int position = (int)view.getTag();
        if(tabCheckListener != null){
            tabCheckListener.onTabSelected(view,position);
        }
        updateState(position);
    }

    //修改tab的尺寸
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }

    //设置当前项
    public void setCurrentItem(int position){
        if(position >= Tabs.size() || position < 0){
            position = 0;
        }
        TabView.get(position).performClick();
        updateState(position);
    }
    public void setCurrentPosition(int position){
        this.currentPosition = position;
    }

    public int getCurrtPosition(){
        return currentPosition;
    }
    //更新状态
    public void updateState(int position){
        for(int i = 0 ; i<TabView.size();i++){
            View view = TabView.get(i);
            TextView btText = view.findViewById(R.id.btText);
            LinearLayout back = view.findViewById(R.id.back);
            btText.setText(Tabs.get(i).getText());
            if(i == position){
                btText.setTextColor(ContextCompat.getColor(context,R.color.text_gray_33));
                back.setBackgroundResource(Tabs.get(i).getPressedIcon());
            }else{
                btText.setTextColor(ContextCompat.getColor(context,R.color.text_gray_66));
                back.setBackgroundResource(Tabs.get(i).getNormalIcon());
            }
        }
    }

    public static class Tab {
        //正常图标
        private int normalIcon;
        //点击后的图标
        private int pressedIcon;
        //名称
        private String text;

        public int getNormalIcon() {
            return normalIcon;
        }

        public Tab setNormalIcon(int normalIcon) {
            this.normalIcon = normalIcon;
            return this;
        }

        public int getPressedIcon() {
            return pressedIcon;
        }

        public Tab setPressedIcon(int pressedIcon) {
            this.pressedIcon = pressedIcon;
            return this;
        }

        public String getText() {
            return text;
        }

        public Tab setText(String text) {
            this.text = text;
            return this;
        }
    }
}
