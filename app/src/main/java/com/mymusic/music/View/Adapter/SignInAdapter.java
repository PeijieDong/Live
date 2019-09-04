package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.SignIn;
import com.mymusic.music.R;

import java.util.List;

public class SignInAdapter extends BaseQuickAdapter <SignIn.DataBean.ListBean,BaseViewHolder>{

    public SignInAdapter(int layoutResId, @Nullable List<SignIn.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SignIn.DataBean.ListBean item) {
        helper.setText(R.id.title,"签到获得")
                .setText(R.id.time,item.getAddtime())
                .setText(R.id.scroe,item.getMoney())
                .setText(R.id.type,"元");
    }
}
