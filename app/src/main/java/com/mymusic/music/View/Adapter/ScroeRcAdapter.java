package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/11 0:01
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ScroeRcAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ScroeRcAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.title,item)
                .setText(R.id.time,item)
                .setText(R.id.scroe,item)
                .setText(R.id.type,"积分");
    }
}
