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
import java.util.Map;

import butterknife.BindView;
import okhttp3.Request;

public class VideoPindaoActivity extends BaseActivity {

    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.tab_Collection)
    LinearLayout tab_Collection;
    private static String title1 ="";
    private static String title2 ="";
    private static String title3 ="";
    private static String title4 ="";
    private static String title5 ="";

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
                List<Title.DataBean.ListBean> list = bean.getData().getList();
                for (int i = 0;i<list.size();i++){
                    for(int a = 0 ;a<list.get(i).getList().size();i++){
                        TabNavigation navigation = new TabNavigation(VideoPindaoActivity.this);
                        LinearLayout.LayoutParams layoutParams =
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(DpPxUtils.dip2px(VideoPindaoActivity.this,10)
                                ,DpPxUtils.dip2px(VideoPindaoActivity.this,5),
                                DpPxUtils.dip2px(VideoPindaoActivity.this,10),
                                DpPxUtils.dip2px(VideoPindaoActivity.this,5));
                        navigation.setLayoutParams(layoutParams);
                        navigation.addTab(new TabNavigation.Tab().setText(list.get(i).getList().get(a))
                                .setPressedIcon(R.drawable.tab_back)
                                .setNormalIcon(R.drawable.tab_back_normal));
                        navigation.setCurrentItem(0);
                        tab_Collection.addView(navigation);
                        int finalI = i;
                        navigation.setOnTabChechListener(new TabNavigation.OnTabCheckListener() {
                            @Override
                            public void onTabSelected(View v, int position) {
                                if(finalI == 0){
                                    title1 = list.get(finalI).getList().get(position);
                                }
                                if(finalI == 1){
                                    title2 = list.get(finalI).getList().get(position);
                                }
                                if(finalI == 2){
                                    title3 = list.get(finalI).getList().get(position);
                                }
                                if(finalI == 3){
                                    title4 = list.get(finalI).getList().get(position);
                                }
                                if(finalI == 4){
                                    title5 = list.get(finalI).getList().get(position);
                                }
                                initRc();
                            }
                        });
                    }
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
        map.put("cate1",title1);
        map.put("cate2",title2);
        map.put("cate3",title3);
        map.put("cate4",title4);
        map.put("cate5",title5);
        map.put("page","0");
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
