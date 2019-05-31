package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/**
 * Create By MR.D
 * 2019/5/30
 * USE:视频页Rc适配器
 **/

public class VideoRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public VideoRecyclerViewAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ViewGroup.LayoutParams params = helper.itemView.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
    }
}
