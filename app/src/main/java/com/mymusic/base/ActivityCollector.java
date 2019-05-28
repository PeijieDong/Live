package com.mymusic.base;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By mr.mao in 2019/5/28 19:37
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ActivityCollector {

    public static List<AppCompatActivity> activities = new ArrayList<AppCompatActivity>();

    public static void addActivity(AppCompatActivity activity){
        activities.add(activity);
    }
    public static void removeActivity(AppCompatActivity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (AppCompatActivity activity :activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
