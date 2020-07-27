package com.mymusic.music.View.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mymusic.music.DataBean.MyShare;
import com.mymusic.music.DataBean.Yaoqing;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyshareActivity;
import com.mymusic.music.View.Adapter.MyShareRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class ShareActivity extends BaseActivity {


    @BindView(R.id.share_earnings)
    TextView shareEarning;
    @BindView(R.id.share_num)
    TextView shareNum;
    @BindView(R.id.my_code)
    TextView myCode;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_share);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        loading();
        myCode.setText("我的邀请码："+SharedPrefrenceUtils.getString(this,"code"));
        NetRequest.postFormRequest(UrlManager.YAOQING, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                hideloading();
                Yaoqing bean = GsonUtil.GsonToBean(result, Yaoqing.class);
                shareEarning.setText(bean.getData().getInvitdetails().replace("\\n", "\n"));
//                shareEarning.setText(bean.getData().getInvitdetails());
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                hideloading();
            }

            @Override
            public void TokenFail() {
                hideloading();
            }
        });
        NetRequest.postFormRequest(UrlManager.MY_SHARE, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                MyShare bean= GsonUtil.GsonToBean(result, MyShare.class);
                if(bean.getData().getList() != null){
                    shareNum.setText("推广用户数量: "+bean.getData().getList().size());
                }else{
                    shareNum.setText("推广用户数量: 0");
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(ShareActivity.this,"网络请求失败",1);
            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }



    @OnClick({R.id.back,R.id.my_share_tv,R.id.share_now,R.id.share_code})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.my_share_tv:
                Intent intent1 = new Intent(this, MyshareActivity.class);
                startActivity(intent1);
                break;
            case R.id.share_now:
                Intent intent2 = new Intent(this, ShareDetailActivity.class);
                startActivity(intent2);
                break;
            case R.id.share_code:
                Intent intent3 = new Intent(this, ShareDetailActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
