package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.content.UriMatcher;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.Live;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.JubaoActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;
import com.mymusic.music.base.UrlManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/5/29 22:19
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomePagerFragment extends BaseFragment implements  OnRefreshListener {

    @BindView(R.id.home_pager_Rc)
    RecyclerView homePagerRecyclerview;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private int position;
    private List<HomeData.DataBean.ListBean> list;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_pager,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        refresh.autoRefresh();
    }

    private void initNet() {
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        HashMap<String, String> map = new HashMap<>();
        map.put("cate", position+1+"");
        NetRequest.getFormRequest(UrlManager.HOME_DATA, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                HomeData data = GsonUtil.GsonToBean(result, HomeData.class);
                initRc(data.getData().getList());
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }
    ImageView like;
    TextView likeNum;
    private void initRc(List<HomeData.DataBean.ListBean> list) {
        this.list = list;
        homePagerRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.themeBt:
                        Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
                        intent.putExtra("id",list.get(position).getUid());
                        getContext().startActivity(intent);
                        break;
                    case R.id.icon_more:
                        BottomSheetDialog bottomSheet = new BottomSheetDialog(getContext());//实例化
                        bottomSheet.setCancelable(true);//设置点击外部是否可以取消
                        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout, null);
                        TextView cencel = view1.findViewById(R.id.cencel);
                        TextView focus = view1.findViewById(R.id.bt_focus);
                        focus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("touid",list.get(position).getUid());
                                initBt(UrlManager.Friend_Focus,position,map);
                                bottomSheet.dismiss();
                            }
                        });
                        TextView collection = view1.findViewById(R.id.bt_collection);
                        collection.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("id",list.get(position).getId());
                                initBt(UrlManager.Collection_Home,position,map);
                                bottomSheet.dismiss();
                            }
                        });
                        TextView pingbi = view1.findViewById(R.id.bt_pingbi);
                        pingbi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("touid",list.get(position).getUid());
                                initBt(UrlManager.PingBI,position,map);
                                bottomSheet.dismiss();

                            }
                        });
                        TextView jubao = view1.findViewById(R.id.bt_jubao);
                        jubao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent(getContext(), JubaoActivity.class);
                                intent1.putExtra("uid",list.get(position).getId());
                                intent1.putExtra("touid",list.get(position).getUid());
                                startActivity(intent1);
                                bottomSheet.dismiss();
                            }
                        });
                        cencel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bottomSheet.dismiss();
                            }
                        });
                        bottomSheet.setContentView(view1);//设置对框框中的布局
                        bottomSheet.show();//显示弹窗
                        break;
                }

            }
        });
        homePagerRecyclerview.setAdapter(adapter);
    }

    private void initBt(String url,int position,HashMap<String,String> map) {
        Log.e("33",Live.getInstance().getToken(getContext()));
        if(Live.getInstance().getToken(getContext()) == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(getContext(),"操作成功",Toast.LENGTH_SHORT).show();
                initNet();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        refresh.setOnRefreshListener(this);
    }

    @Override
    protected void LoadData() {

    }

//    private void initLike(HomeData.DataBean.ListBean item) {
//        //like_press
//        HashMap<String, String> map = new HashMap<>();
//        map.put("type","1");
//        map.put("id",item.getId());
//        NetRequest.postFormRequest(UrlManager.Like, map, new NetRequest.DataCallBack() {
//            @Override
//            public void requestSuccess(String result) throws Exception {
//                Toast.makeText(getContext(),"点赞成功",Toast.LENGTH_SHORT).show();
//                like.setBackground(getContext().getResources().getDrawable(R.drawable.ic_launcher_background));
//                like.setClickable(false);
//                likeNum.setText(Integer.valueOf(likeNum.getText().toString())+1+"");
//            }
//p
//            @Override
//            public void requestFailure(Request request, IOException e) {
//                Toast.makeText(getContext(),"点赞失败",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }



    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initNet();
        refresh.finishRefresh();
    }
}

