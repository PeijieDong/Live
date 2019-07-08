package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Focus;
import com.mymusic.music.DataBean.FocusPerson;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyfansActivity;
import com.mymusic.music.View.Adapter.FocusRcAdaper;
import com.mymusic.music.View.Adapter.FocusrcAdaper2;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/2 20:41
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FocusFragment extends BaseFragment {

    @BindView(R.id.focus_Rc)
    RecyclerView focusRc;
    @BindView(R.id.focus_tv)
    TextView focusTv;
    private String url;
    private FocusPerson bean;
    private Focus data;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_focus,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        url = bundle.getString("url");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        if (url.equals(UrlManager.Focus_Person)) {
            if(Live.getInstance().getToken(getContext()) == null){
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                return;
            }
            NetRequest.postFormHeadRequest(url, null, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
                @Override
                public void requestSuccess(String result) throws Exception {
                    Log.e("3333", result);
                    bean = GsonUtil.GsonToBean(result, FocusPerson.class);
                    focusRc.setLayoutManager(new LinearLayoutManager(getContext()));
                    FocusrcAdaper2 adaper = new FocusrcAdaper2(R.layout.focus_rc_layout, bean.getData().getList());
                    adaper.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_layout,null));
                    adaper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                            TextView focus = view.findViewById(R.id.focus_rc_focusbt);
                            if (focus.getText().toString().equals("+关注")) {
                                focus.setText("已关注");
                                initFocus(true,i);
                                focus.setBackgroundResource(R.drawable.back_friend_detail_cencelfocus);
                            } else {
                                focus.setText("+关注");
                                initFocus(false,i);
                                focus.setBackgroundResource(R.drawable.back_friend_detail_focus);
                            }
                        }
                    });
                    adaper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(getContext(), UserDetailActivity.class);
                            intent.putExtra("UserId",bean.getData().getList().get(position).getUid());
                            getContext().startActivity(intent);
                        }
                    });
                    focusRc.setAdapter(adaper);
                    focusTv.setText("已关注"+bean.getData().getTotal()+"人");
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
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("page", "1");
            if(Live.getInstance().getToken(getContext()) == null){
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                return;
            }
            NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
                @Override
                public void requestSuccess(String result) throws Exception {
                    Log.e("3333", result);
                    data = GsonUtil.GsonToBean(result, Focus.class);

                    focusRc.setLayoutManager(new LinearLayoutManager(getContext()));
                    FocusRcAdaper adaper = new FocusRcAdaper(R.layout.focus_rc_layout, data.getData().getList());
                    adaper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                            TextView focus = view.findViewById(R.id.focus_rc_focusbt);
                            focusTv.setText("已关注"+bean.getData().getTotal()+"个");
                            if (focus.getText().toString().equals("+关注")) {
                                focus.setText("已关注");
                                focus.setBackgroundResource(R.drawable.back_friend_detail_cencelfocus);
                                initFocusFriend(true,i);
                            } else {
                                focus.setText("+关注");
                                focus.setBackgroundResource(R.drawable.back_friend_detail_focus);
                                initFocusFriend(true,i);
                            }
                        }
                    });
                    adaper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(getContext(), FriendDetailActivity.class);
                            intent.putExtra("id",data.getData().getList().get(position).getTouid());
                            getContext().startActivity(intent);
                        }
                    });
                    focusRc.setAdapter(adaper);
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

    private void initFocus(boolean isFocus, int i) {
        String url = "";
        if(isFocus){
            url = UrlManager.Focus_User;
        }else{
            url = UrlManager.NoFocus_User;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",bean.getData().getList().get(i).getUid());
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
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getContext(),"操作失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initFocusFriend(boolean isFocus, int i) {
        String url = "";
        if(isFocus){
            url = UrlManager.Focus_Friend;
        }else{
            url = UrlManager.NoFocus_Friend;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",data.getData().getList().get(i).getUid());
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
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getContext(),"操作失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
