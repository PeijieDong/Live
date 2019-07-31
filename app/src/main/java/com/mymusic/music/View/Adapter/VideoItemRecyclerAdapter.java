package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/31 13:24
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoItemRecyclerAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public VideoItemRecyclerAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {

    }
}
