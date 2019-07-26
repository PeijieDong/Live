package com.mymusic.music.View.Activity.Community;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.Live;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.PicToBase64;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.post.putContentActivity;
import com.mymusic.music.View.Adapter.CommunityRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class CommunityAdviceActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.community_camera)
    LinearLayout camera;
    @BindView(R.id.community_advice_rc)
    RecyclerView Rc;
    @BindView(R.id.edittext)
    EditText Et;
    @BindView(R.id.adress)
    EditText adress;
    private int REQUEST_CODE_CHOOSE = 1;
    private List<Uri> mSelected;
    private CommunityRcAdapter adapter;
    private List<Uri> list;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_community_advice);
    }

    @Override
    protected void LoadData() {
        list = new ArrayList<>();
        initRc();
    }

    private void initRc() {
        Rc.setLayoutManager(new GridLayoutManager(CommunityAdviceActivity.this,3));
        adapter = new CommunityRcAdapter(R.layout.community_advice_item,list);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_community_advice_foot2, null);
        view.setOnClickListener(this);
        adapter.addFooterView(view);
        Rc.setAdapter(adapter);
    }

    @OnClick({R.id.community_camera,R.id.post})
    public void OnclickEvent(View view){
        switch (view.getId()){
            case R.id.community_camera:
                initCamera();

                break;
            case R.id.post:
                if(Et.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入举报内容",Toast.LENGTH_SHORT);
                    return;
                }
                if(adress.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入联系方式",Toast.LENGTH_SHORT);
                    return;
                }
                showLoading();
                initNet();
                break;
        }
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("content",Et.getText().toString());
        map.put("contact",adress.getText().toString());
        map.put("type","1");
        map.put("images","data:image/jpeg;base64,"+PicToBase64.imageToBase64(getRealPathFromURI(CommunityAdviceActivity.this,list.get(0))));
        NetRequest.postFormHeadRequest(UrlManager.FeedBack, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                ToastUtil.show(CommunityAdviceActivity.this,"提交成功",Toast.LENGTH_SHORT);
                closeLoading();
                finish();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                closeLoading();
            }

            @Override
            public void TokenFail() {
                closeLoading();
                LoginDialog dialog = new LoginDialog(CommunityAdviceActivity.this);
                dialog.Show();
            }
        });
    }

    private void initCamera() {
        Matisse.from(CommunityAdviceActivity.this)
                .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                .countable(true)
                .theme(R.style.Matisse_Zhihu )
                .maxSelectable(1) // 图片选择的最多数量
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new com.mymusic.music.Util.GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            list = Matisse.obtainResult(data);
            initRc();
        }
    }



    @Override
    public void onClick(View v) {
        initCamera();
    }


    public String getVideoImage(String videoPath){
        Log.e("33",videoPath);
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);
        Bitmap bitmap = media.getFrameAtTime();
        String base64 = PicToBase64.bitmapToBase64(bitmap);
        return "data:image/jpeg;base64,"+base64;
    }

    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI,
                new String[]{MediaStore.Video.VideoColumns.DATA},//
                null, null, null);
        if (cursor == null) result = contentURI.getPath();
        else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }
    @OnClick({R.id.back})
    public void Click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
