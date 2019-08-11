package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mymusic.music.DataBean.Title;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.BottomNavigation;
import com.mymusic.music.Util.DpPxUtils;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.TabNavigation;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.Util.TopNavigation;
import com.mymusic.music.View.Adapter.RcAdpaterVideo;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

public class VideoPindaoActivity extends BaseActivity {

    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.tab_Collection)
    LinearLayout tab_Collection;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_pindao);
    }

    @Override
    protected void LoadData() {
        initTitle();
    }

    private void initTitle() {
        NetRequest.getFormRequest(UrlManager.GET_TITLE, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Title bean = GsonUtil.GsonToBean(result, Title.class);
                List<String> list = bean.getData().getList().get全部发布();
                for (int i = 0;i<list.size();i++){
                    TabNavigation navigation = new TabNavigation(VideoPindaoActivity.this);
                    LinearLayout.LayoutParams layoutParams =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(DpPxUtils.dip2px(VideoPindaoActivity.this,10)
                    ,DpPxUtils.dip2px(VideoPindaoActivity.this,5),
                            DpPxUtils.dip2px(VideoPindaoActivity.this,10),
                            DpPxUtils.dip2px(VideoPindaoActivity.this,5));
                    navigation.setLayoutParams(layoutParams);
                    navigation.addTab(new TabNavigation.Tab().setText(list.get(0))
                            .setPressedIcon(R.drawable.tab_back)
                            .setNormalIcon(R.drawable.tab_back_normal));
                    tab_Collection.addView(navigation);
                    int finalI = i;
                    navigation.setOnTabChechListener(new TabNavigation.OnTabCheckListener() {
                        @Override
                        public void onTabSelected(View v, int position) {
                            ToastUtil.show(VideoPindaoActivity.this,"点击了"+ finalI +"个",1);
                        }
                    });
                }
                initRc();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    private void initRc() {
        loading();
        HashMap<String, String> map = new HashMap<>();
        NetRequest.getFormRequest(UrlManager.GET_VIDEO, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                hideloading();
                VideoItem bean = GsonUtil.GsonToBean(result, VideoItem.class);
                rc.setLayoutManager(new LinearLayoutManager(VideoPindaoActivity.this));
                RcAdpaterVideo videoAdapter = new RcAdpaterVideo(R.layout.rc_adapter_item_video,bean.getData().getList());
                rc.setAdapter(videoAdapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                hideloading();
            }

            @Override
            public void TokenFail() {
                hideloading();
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }


}
