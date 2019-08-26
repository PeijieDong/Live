package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.NewVideo;
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
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Adapter.RcAdapterVideo2;
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
    @BindView(R.id.title)
    TextView titleTv;
    private static String title1 ="";
    private static String title2 ="";
    private static String title3 ="";
    private static String title4 ="";
    private static String title5 ="";
    private String id;
    String title;

    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_pindao);
    }

    @Override
    protected void LoadData() {
        titleTv.setText(title);
        initTitle();
    }

    private void initTitle() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pid",id);
        NetRequest.getFormRequest(UrlManager.GET_TITLE, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Title bean = GsonUtil.GsonToBean(result, Title.class);
                List<Title.DataBean.ListBeanX> list = bean.getData().getList();
                for (int i = 0;i<list.size();i++){
                    TabNavigation navigation = new TabNavigation(VideoPindaoActivity.this);
                    LinearLayout.LayoutParams layoutParams =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(DpPxUtils.dip2px(VideoPindaoActivity.this,7)
                            ,DpPxUtils.dip2px(VideoPindaoActivity.this,5),
                            DpPxUtils.dip2px(VideoPindaoActivity.this,10),
                            DpPxUtils.dip2px(VideoPindaoActivity.this,5));
                    navigation.setLayoutParams(layoutParams);
                    for(int a = 0 ;a<list.get(i).getList().size();a++) {
                        navigation.setCurrentPosition(i);
                        navigation.addTab(new TabNavigation.Tab().setText(list.get(i).getList().get(a).getTitle())
                                .setPressedIcon(R.drawable.tab_back)
                                .setNormalIcon(R.drawable.tab_back_normal));
                    }
                        navigation.setCurrentItem(0);
                        if(i == 0){
                            title1 = list.get(i).getList().get(0).getDid();
                        }
                        if(i == 1){
                            title2 = list.get(i).getList().get(0).getCid();
                        }
                        if(i == 2){
                            title3 = list.get(i).getList().get(0).getId();
                        }
                        tab_Collection.addView(navigation);
                        navigation.setOnTabChechListener(new TabNavigation.OnTabCheckListener() {
                            @Override
                            public void onTabSelected(View v, int position) {
                                int finalI = navigation.getCurrtPosition();
                                if(finalI == 0){
                                    title1 = list.get(finalI).getList().get(position).getDid();
                                }
                                if(finalI == 1){
                                    title2 = list.get(finalI).getList().get(position).getCid();
                                }
                                if(finalI == 2){
                                    title3 = list.get(finalI).getList().get(position).getId();
                                }
                                initRc();
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
        map.put("cid",title1);
        map.put("did",title2);
        map.put("pid",title3);
//        map.put("cate4",title4);
//        map.put("cate5",title5);
        map.put("page","0");
        NetRequest.getFormRequest(UrlManager.GET_VIDEO, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                hideloading();
                NewVideo bean = GsonUtil.GsonToBean(result, NewVideo.class);
                rc.setLayoutManager(new GridLayoutManager(VideoPindaoActivity.this,2));
                RcAdapterVideo2 videoAdapter = new RcAdapterVideo2(R.layout.gridtype2_layout,bean.getData().getList());
                videoAdapter.setEmptyView(LayoutInflater.from(VideoPindaoActivity.this).inflate(R.layout.empty_layout,null));
                videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(VideoPindaoActivity.this, DetailsActivity.class);
                        intent.putExtra("id",bean.getData().getList().get(position).getId());
                        intent.putExtra("new",true);
                        startActivity(intent);
                    }
                });
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
