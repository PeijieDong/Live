package com.mymusic.music.Util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mymusic.music.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignInNavigation extends LinearLayout {
    private List<View> TabView;//保存Tab背景
    private List<SignInNavigation.Tab> Tabs;//保存tab
    private Context context;

    public SignInNavigation(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SignInNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SignInNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SignInNavigation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    //初始化参数
    private void init(){
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        Tabs = new ArrayList<>();
        TabView = new ArrayList<>();
    }

    //添加Tab的方法
    public void addTab(SignInNavigation.Tab tab){
        View view = LayoutInflater.from(context).inflate(R.layout.sign_in_item_layout, null);
        tab.rewardBack = view.findViewById(R.id.reward_back);
        TextView rewardNum = view.findViewById(R.id.reward_num);
        TextView dayNum = view.findViewById(R.id.day_num);
        view.setTag(Tabs.size());
        rewardNum.setText(tab.rewardNum);
        dayNum.setText(tab.dayNum);
        Tabs.add(tab);
        TabView.add(view);

        addView(view);
    }

    //设置当前项
    public void setCurrentItem(int position){
        if(position >= Tabs.size()){
            position = Tabs.size();
        }
        if(position < 0){
            position = 0;
        }
        updateState(position);
    }
    //更新状态
    public void updateState(int position){
        for (int i = 0 ; i < position ; i++){
            Tabs.get(i).rewardBack.setImageDrawable(getResources().getDrawable(R.color.gold));
//            Tabs.get(i).rewardBack.setCircleBackgroundColorResource(R.color.gold);
        }
    }
    //修改tab的尺寸
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for(int i = 0;i<TabView.size();i++){
            View view = TabView.get(i);
            int width = getResources().getDisplayMetrics().widthPixels / Tabs.size();
            LayoutParams params = new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
        }
    }

    public static class Tab {
        String dayNum;
        String rewardNum;
        CircleImageView rewardBack;

        public String getDayNum() {
            return dayNum;
        }

        public Tab setDayNum(String dayNum) {
            this.dayNum = dayNum;
            return this;
        }

        public String getRewardNum() {
            return rewardNum;
        }

        public Tab setRewardNum(String rewardNum) {
            this.rewardNum = rewardNum;
            return this;
        }

        public CircleImageView getRewardBack() {
            return rewardBack;
        }

        public Tab setRewardBack(CircleImageView rewardBack) {
            this.rewardBack = rewardBack;
            return this;
        }
    }
}
