package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.TextView;

import com.mymusic.music.DataBean.MyShare;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Adapter.MyShareRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MyshareActivity extends BaseActivity {

    @BindView(R.id.Rc)
    RecyclerView rc;
    @BindView(R.id.shareNum)
    TextView shareNum;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myshare);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormRequest(UrlManager.MY_SHARE, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                MyShare bean= GsonUtil.GsonToBean(result, MyShare.class);
                if(bean.getData().getList() != null){
                    shareNum.setText("我总共推广"+bean.getData().getList().size()+"人");
                }else{
                    shareNum.setText("我总共推广0人");
                }
                rc.setLayoutManager(new LinearLayoutManager(MyshareActivity.this));
                rc.setAdapter(new MyShareRcAdapter(R.layout.my_share_item_layout,bean.getData().getList()));
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(MyshareActivity.this,"网络请求失败",1);
            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
