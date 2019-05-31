package com.mymusic.music.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.View.Adapter.VideoRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/5/29 21:30
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoFragment extends BaseFragment {

    @BindView(R.id.video_Rc)
    RecyclerView videoRc;
    private PagerSnapHelper helper;
    private LinearLayoutManager layoutManager;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_video,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        List<String> list = new ArrayList<>();
        list.add("33");
        list.add("33");
        list.add("33");
        //Recycleview页面滑动
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(videoRc);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        videoRc.setLayoutManager(layoutManager);
        videoRc.setAdapter(new VideoRecyclerViewAdapter(R.layout.video_item_layout,list));
        videoRc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState){
                    //停止滚吨
                    case RecyclerView.SCROLL_STATE_IDLE:
                        View view = helper.findSnapView(layoutManager);
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                        if(viewHolder != null && viewHolder instanceof BaseViewHolder){
                            //播放视频
                        }
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
