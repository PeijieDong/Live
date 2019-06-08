package com.mymusic.music.View.Adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/7 13:47
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class CommunityRcAdapter extends BaseQuickAdapter <Uri,BaseViewHolder>{

    public CommunityRcAdapter(int layoutResId, @Nullable List<Uri> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Uri item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.community_select_image));
    }
}
