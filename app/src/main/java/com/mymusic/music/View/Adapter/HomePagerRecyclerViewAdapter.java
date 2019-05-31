package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/5/29 22:22
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomePagerRecyclerViewAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private final int PICTURE = 1;
    private final int ARTICLE = 2;
    private final int VIDEO = 3;
    private final int RECOMMEND = 4;
    private final int ADV = 5;

    public HomePagerRecyclerViewAdapter(@Nullable List<String> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<String>() {
            @Override
            protected int getItemType(String s) {
                switch (s){
                    //多图片
                    case "picture":
                        return PICTURE;
                    //文章
                    case "article":
                        return ARTICLE;
                    //视频
                    case "video":
                        return VIDEO;
                    //推荐
                    case "recommend":
                        return RECOMMEND;
                    //广告
                    case "adv":
                        return ADV;
                }
                return 0;
            }
        });
        getMultiTypeDelegate().registerItemType(PICTURE,R.layout.home_rc_item_picture)
                              .registerItemType(ARTICLE,R.layout.home_rc_item_article)
                              .registerItemType(VIDEO,R.layout.home_rc_video)
                              .registerItemType(RECOMMEND,R.layout.home_rc_recommend)
                              .registerItemType(ADV,R.layout.home_rc_adv);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int type = helper.getItemViewType();
        switch (type){
            case PICTURE:
                break;
            case ARTICLE:
                break;
            case VIDEO:
                break;
            case RECOMMEND:
                break;
            case ADV:
                break;
        }
    }

}
