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
import com.mymusic.music.DataBean.UserDetail;
import com.mymusic.music.Live;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
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
        if(bundle.getString("found") != null){
            HashMap<String, String> map = new HashMap<>();
            map.put("type", position+1+"");
            map.put("keyword",bundle.getString("found"));
            NetRequest.postFormRequest(UrlManager.Home_Find, map, new NetRequest.DataCallBack() {
                @Override
                public void requestSuccess(String result) throws Exception {
                    Log.e("333",result);
                    HomeData data = GsonUtil.GsonToBean(result, HomeData.class);
                    initRc(data.getData().getList());
                }

                @Override
                public void requestFailure(Request request, IOException e) {
                    Log.e("33",e.getMessage());
                }
                @Override
                public void TokenFail() {
                    LoginDialog dialog = new LoginDialog(getActivity());
                    dialog.Show();
                }
            });
        }else {
            HashMap<String, String> map = new HashMap<>();
            map.put("cate", position + 1 + "");
            NetRequest.getFormRequest(UrlManager.HOME_DATA, map, new NetRequest.DataCallBack() {
                @Override
                public void requestSuccess(String result) throws Exception {
                    Log.e("33", result);
                    HomeData data = GsonUtil.GsonToBean(result, HomeData.class);
                    initRc(data.getData().getList());
                }

                @Override
                public void requestFailure(Request request, IOException e) {

                }
                @Override
                public void TokenFail() {
                    LoginDialog dialog = new LoginDialog(getActivity());
                    dialog.Show();
                }
            });
        }
    }
    TextView likeNum;
    ImageView likeIcon;
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
                    case R.id.user_go:
                        Intent intent2 = new Intent(getContext(), UserDetailActivity.class);
                        intent2.putExtra("UserId",list.get(position).getId());
                        startActivity(intent2);
                        break;
                    case R.id.themeBt:
                        Intent intent = new Intent(getContext(), FriendDetailActivity.class);
                        intent.putExtra("id",list.get(position).getCate());
                        getContext().startActivity(intent);
                        break;
                    case R.id.icon_more:
                        if(Live.getInstance().getUser(getContext()) == null){
                            startActivity(new Intent(getContext(),LoginActivity.class));
                            return;
                        }
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
                                initBt(UrlManager.Focus_User,position,map);
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
                                initNet();

                            }
                        });
                        TextView jubao = view1.findViewById(R.id.bt_jubao);
                        jubao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(Live.getInstance().getUser(getContext()) == null){
                                    startActivity(new Intent(getContext(),LoginActivity.class));
                                    return;
                                }
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
                    case R.id.ll_home_like:
                        if(Live.getInstance().getUser(getContext()) == null){
                            startActivity(new Intent(getContext(),LoginActivity.class));
                            return;
                        }
                        likeNum = view.findViewById(R.id.likeNum);
                        likeIcon = view.findViewById(R.id.icon_like);
                        initLike(list,position);
                        break;
                    case R.id.found_head:
                        Intent intent1 = new Intent(getContext(), UserDetailActivity.class);
                        intent1.putExtra("UserId",1);
                        startActivity(intent1);
                        break;
                }

            }
        });
        homePagerRecyclerview.setAdapter(adapter);
    }

    private void initBt(String url,int position,HashMap<String,String> map) {
        if(Live.getInstance().getToken(getContext()) == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(getContext(),"操作成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(getContext(),e.getMessage(),Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
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

    private void initLike(List<HomeData.DataBean.ListBean> item, int position) {
        //like_press
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",item.get(position).getId());
        NetRequest.postFormRequest(UrlManager.Like, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                ToastUtil.show(getContext(),"点赞成功",Toast.LENGTH_SHORT);
                likeIcon.setImageResource(R.drawable.like_press);
                likeNum.setText(Integer.valueOf(likeNum.getText().toString())+1+"");
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(getContext(),"点赞失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }



    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initNet();
        refresh.finishRefresh();
    }

    public void checkLogin(){
        if(Live.getInstance().getToken(getContext()) == null){
            startActivity(new Intent(getContext(),LoginActivity.class));
            return;
        }
    }
}

