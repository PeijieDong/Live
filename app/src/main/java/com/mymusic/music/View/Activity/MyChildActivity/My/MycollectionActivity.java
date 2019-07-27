package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.Detail.VideoPlayActivity;
import com.mymusic.music.View.Activity.Detail.VideoSingleActivity;
import com.mymusic.music.View.Activity.JubaoActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MycollectionActivity extends BaseActivity {

    @BindView(R.id.Rc)
    RecyclerView rc;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mycollection);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("page","1");
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.Collection, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
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
    TextView likeNum;
    ImageView likeIcon;
    private boolean isSelect = true;
    private void initRc(List<HomeData.DataBean.ListBean> list) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(list);
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_layout,null));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(list.get(position).getType().equals("小视频")){
                    Intent intent = new Intent(MycollectionActivity.this, VideoSingleActivity.class);
                    intent.putExtra("playData", (Serializable) list.get(position));
                    intent.putExtra("position",position);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MycollectionActivity.this, DetailsActivity.class);
                    intent.putExtra("id", list.get(position).getNewsid());
                    startActivity(intent);
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.icon_comment:
                        Intent intentx = new Intent(MycollectionActivity.this, DetailsActivity.class);
                        intentx.putExtra("id",list.get(position).getId());
                        startActivity(intentx);
                        break;
                    case R.id.user_go:
                        Intent intent2 = new Intent(MycollectionActivity.this, UserDetailActivity.class);
                        intent2.putExtra("UserId",list.get(position).getId());
                        startActivity(intent2);
                        break;
                    case R.id.themeBt:
                        Intent intent = new Intent(MycollectionActivity.this, FriendDetailActivity.class);
                        intent.putExtra("id",list.get(position).getCate());
                        startActivity(intent);
                        break;
                    case R.id.icon_more:
                        if(Live.getInstance().getUser(MycollectionActivity.this) == null){
                            startActivity(new Intent(MycollectionActivity.this,LoginActivity.class));
                            return;
                        }
                        BottomSheetDialog bottomSheet = new BottomSheetDialog(MycollectionActivity.this);//实例化
                        bottomSheet.setCancelable(true);//设置点击外部是否可以取消
                        View view1 = LayoutInflater.from(MycollectionActivity.this).inflate(R.layout.dialog_layout, null);
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
                                if(Live.getInstance().getUser(MycollectionActivity.this) == null){
                                    startActivity(new Intent(MycollectionActivity.this,LoginActivity.class));
                                    return;
                                }
                                Intent intent1 = new Intent(MycollectionActivity.this, JubaoActivity.class);
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
                        if(Live.getInstance().getUser(MycollectionActivity.this) == null){
                            startActivity(new Intent(MycollectionActivity.this,LoginActivity.class));
                            return;
                        }
                        likeNum = view.findViewById(R.id.likeNum);
                        likeIcon = view.findViewById(R.id.icon_like);
                        if(isSelect){
                            initLike(list,position);
                        }
                        break;
                    case R.id.found_head:
                        Intent intent1 = new Intent(MycollectionActivity.this, UserDetailActivity.class);
                        intent1.putExtra("UserId",1);
                        startActivity(intent1);
                        break;
                    case R.id.userBt:
                        Intent intentU = new Intent(MycollectionActivity.this, UserDetailActivity.class);
                        intentU.putExtra("UserId", list.get(position).getUid());
                        startActivity(intentU);
                        break;
                    case R.id.icon_share:
                        Intent intentS = new Intent(MycollectionActivity.this, UserDetailActivity.class);
                        intentS.putExtra("UserId", list.get(position).getUid());
                        startActivity(intentS);
                        break;
                }

            }
        });
        rc.setAdapter(adapter);
    }


    private void initBt(String url,int position,HashMap<String,String> map) {
        if(Live.getInstance().getToken(MycollectionActivity.this) == null){
            Intent intent = new Intent(MycollectionActivity.this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(MycollectionActivity.this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(MycollectionActivity.this,"操作成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(MycollectionActivity.this,e.getMessage(),Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
    private void initLike(List<HomeData.DataBean.ListBean> item, int position) {
        //like_press
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",item.get(position).getId());
        NetRequest.postFormRequest(UrlManager.Like, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                isSelect = false;
                ToastUtil.show(MycollectionActivity.this,"点赞成功",Toast.LENGTH_SHORT);
                likeIcon.setImageResource(R.drawable.like_press);
                likeNum.setText(Integer.valueOf(likeNum.getText().toString())+1+"");
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(MycollectionActivity.this,"点赞失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
    @OnClick({R.id.back})
    public void Clickevent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
