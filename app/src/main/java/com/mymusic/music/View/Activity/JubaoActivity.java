package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.PicToBase64;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class JubaoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.radio_group)
    RadioGroup group;
    @BindView(R.id.Rc)
    RecyclerView rc;
    @BindView(R.id.des)
    EditText des;
    private View view;
    private int REQUEST_CODE_CHOOSE = 2;
    JubaoAdapter adapter;
    private List<Uri> imageList = new ArrayList<>();
    String uid;
    String touid;
    @Override
    protected void initVariables(Intent intent) {
        uid = intent.getStringExtra("uid");
        touid = intent.getStringExtra("touid");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jubao);
    }

    @Override
    protected void LoadData() {
       initRc();
    }

    private void initRc() {
        rc.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new JubaoAdapter(R.layout.jubao_item_layout, imageList);
        view = LayoutInflater.from(this).inflate(R.layout.jubao_foot_layout,null);
        view.setOnClickListener(this);
        adapter.addFooterView(view);
        rc.setAdapter(adapter);
    }

    @OnClick({R.id.close,R.id.post})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.post:
                initNet();
                break;
            case R.id.close:
                finish();
                break;
        }
    }

    private void initNet() {
        if(imageList.size() == 0){
            ToastUtil.show(JubaoActivity.this,"请上传图片",Toast.LENGTH_SHORT);
            return;
        }
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        if(group.getCheckedRadioButtonId() == -1){
            ToastUtil.show(JubaoActivity.this,"请选择举报类型",Toast.LENGTH_SHORT);
            return;
        }
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("type",group.getCheckedRadioButtonId()+"");
        map.put("content",des.getText().toString());
        map.put("nid",uid);
        map.put("touid",touid);
        map.put("file",PicToBase64.imageToBase64(imageList.get(0).getPath()));
        NetRequest.postFormHeadRequest(UrlManager.JuBao, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                closeLoading();
                ToastUtil.show(JubaoActivity.this,"举报成功",Toast.LENGTH_SHORT);
                finish();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                closeLoading();
            }
            @Override
            public void TokenFail() {
                closeLoading();
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Matisse.from(this)
                .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                .countable(true)
                .theme(R.style.Matisse_Zhihu)
                .maxSelectable(1) // 图片选择的最多数量
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.8f) // 缩略图的比例
                .imageEngine(new com.mymusic.music.Util.GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }

    private class JubaoAdapter extends BaseQuickAdapter<Uri,BaseViewHolder> {

        public JubaoAdapter(int layoutResId, @Nullable List<Uri> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Uri item) {
            Glide.with(mContext).load(item).error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.pic));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            imageList = Matisse.obtainResult(data);
            initRc();
        }
    }
}
