package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Find;
import com.mymusic.music.R;
import com.mymusic.music.Util.MyGridView;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 20:40
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class pindaoRcAdapter extends BaseQuickAdapter<Find.DataBean.ListBean,BaseViewHolder> {

    ClickItemListener listener;
    public pindaoRcAdapter(int layoutResId, @Nullable List<Find.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Find.DataBean.ListBean s) {
        MyGridView grid = helper.getView(R.id.grid);
        GridAdapter gridAdapter = new GridAdapter(mContext, s.getList());
        gridAdapter.setListener(new GridAdapter.ItemListener() {
            @Override
            public boolean click(String title) {
                return listener.ClickEvent(title);
            }

            @Override
            public void remove(String title) {
                listener.Remove(title);
            }

            @Override
            public void ClickEvent(String title) {
                listener.ClickTitle(title);
            }
        });
        grid.setAdapter(gridAdapter);
        helper.setText(R.id.title,s.getTitle());
    }

    public interface ClickItemListener{
        boolean ClickEvent(String title);

        void Remove(String title);

        void ClickTitle(String title);
    }
    public void setClickListener(ClickItemListener listener){
        this.listener = listener;
    }

}
