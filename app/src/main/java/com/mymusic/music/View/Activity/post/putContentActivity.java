package com.mymusic.music.View.Activity.post;

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
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.BottomNavigation;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.PicToBase64;
import com.mymusic.music.Util.PutBottomNavigation;
import com.mymusic.music.Util.Uri2PathUtil;
import com.mymusic.music.View.Activity.Community.CommunityAdviceActivity;
import com.mymusic.music.View.Activity.FriendFoundActivity;
import com.mymusic.music.View.Adapter.CommunityRcAdapter;
import com.mymusic.music.View.Adapter.FocusRcAdaper;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.litepal.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class putContentActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.chose_sign)
    LinearLayout choseSign;
    @BindView(R.id.navigation_put)
    PutBottomNavigation navigation;
    @BindView(R.id.content_text)
    EditText text;
    @BindView(R.id.image_icon)
    RecyclerView rc;
    @BindView(R.id.have_chose)
    TextView haveChose;
    @BindView(R.id.home_rc_type)
    TextView typeTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.icon_more)
    TextView icon_more;
    @BindView(R.id.chose_sign2)
    ImageView logosign;
    @BindView(R.id.chose_flow)
    TagFlowLayout choseFlow;
    private boolean isVideo = true;
    private List<Uri> image = new ArrayList<>();
    private int REQUEST_CODE_CHOOSE = 2;
    private String cid = "";
    CommunityRcAdapter adapter;
    private String type ;
    private StringBuilder tag = new StringBuilder();
    private List<String> list = new ArrayList<>();
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstapostnceState) {
        setContentView(R.layout.activity_put_content);
    }

    @Override
    protected void LoadData() {
        navigation.addTab(new PutBottomNavigation.Tab().setNormalIcon(R.drawable.publish_video_n)
                .setPressedIcon(R.drawable.publish_video_s).setText("视频").setTextColor(R.color.navi_title_color));
        navigation.addTab(new PutBottomNavigation.Tab().setNormalIcon(R.drawable.publish_image_n)
                .setPressedIcon(R.drawable.publish_image_s).setText("图片").setTextColor(R.color.navi_title_color));
        navigation.addTab(new PutBottomNavigation.Tab().setNormalIcon(R.drawable.publish_read_n)
                .setPressedIcon(R.drawable.publish_read_s).setText("短文").setTextColor(R.color.navi_title_color));
        navigation.setOnTabChechListener(new PutBottomNavigation.OnTabCheckListener() {
            @Override
            public void onTabSelected(View v, int position) {
                switch (position){
                    case 0:
                        text.setVisibility(View.GONE);
                        rc.setVisibility(View.VISIBLE);
                        isVideo = true;
                        image.clear();
                        initRc();
                        break;
                    case 1:
                        text.setVisibility(View.GONE);
                        rc.setVisibility(View.VISIBLE);
                        isVideo = false;
                        image.clear();
                        initRc();
                        break;
                    case 2:
                        text.setVisibility(View.VISIBLE);
                        rc.setVisibility(View.GONE);
                        break;
                }
            }
        });
        navigation.setCurrentItem(0);
        initRc();
    }
    @OnClick({R.id.choseFriend,R.id.chose_sign,R.id.post,R.id.close})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.close:
                finish();
                break;
            case R.id.choseFriend:
                Intent intent1 = new Intent(putContentActivity.this, FriendFoundActivity.class);
                intent1.putExtra("chose",true);
                startActivityForResult(intent1,100);
                break;
            case R.id.chose_sign:
                Intent intent = new Intent(this, SignActivity.class);
                startActivityForResult(intent,200);
                break;
            case R.id.post:
                verifyStoragePermissions(this);
                switch (navigation.getPosition()){
                    case 0:
                        type = "5";
                        break;
                    case 1:
                        type = "4";
                        break;
                    case 2:
                        type = "3";
                        break;
                }
                for (int i = 0;i<list.size();i++){
                    tag.append(list.get(i));
                    tag.append(",");
                }
                initNet();
                break;
        }
    }

    private void initNet() {
        if(title.getText().toString().equals("")){
            Toast.makeText(this,"请输入标题",Toast.LENGTH_SHORT).show();
            return;
        }
        if(cid.equals("")){
            Toast.makeText(this,"请选择圈子",Toast.LENGTH_SHORT).show();
            return;
        }
        String url = UrlManager.Post_Video;
        File file = null;
        HashMap<String, String> map = new HashMap<>();
        map.put("cate", cid);
        map.put("type", type);
        map.put("tag", tag.toString());
        map.put("content", title.getText().toString());
        List<File> fileList = new ArrayList<>();
        if (navigation.getPosition() == 1) {
            if(image.size() == 0){
                Toast.makeText(this,"请选择要上传的图片",Toast.LENGTH_SHORT).show();
                return;
            }
            showLoading();
            map.put("images", "data:image/jpeg;base64,"+PicToBase64.imageToBase64(getRealPathFromURI(putContentActivity.this,image.get(0))));
            for (int i = 0 ;i<image.size();i++){
                File file1 = getFileByUri(image.get(i),putContentActivity.this);
                fileList.add(file1);
            }
            NetRequest.postmorePicRequest(url, this, map, fileList, new NetRequest.DataCallBack() {
                @Override
                public void requestSuccess(String result) throws Exception {
                    Log.e("23",result);
                    Toast.makeText(putContentActivity.this,"提交成功，等待管理员审核",Toast.LENGTH_SHORT).show();
                    finish();
                    closeLoading();
                }

                @Override
                public void requestFailure(Request request, IOException e) {
                    Log.e("23",e.getMessage());
                    closeLoading();
                }
                @Override
                public void TokenFail() {
                    closeLoading();
                    LoginDialog dialog = new LoginDialog(getActivity());
                    dialog.Show();
                }
            });
            return;
        }
        if (navigation.getPosition() == 0) {
            if(image.size() == 0){
                Toast.makeText(this,"请选择要上传的视频",Toast.LENGTH_SHORT).show();
                return;
            }
            file = getFileByUri(image.get(0), this);
            map.put("playtime", getRingDuring(image.get(0)));
            map.put("images", "data:image/jpeg;base64,"+PicToBase64.imageToBase64(getRealPathFromURI(putContentActivity.this,image.get(0))));
        }
        if(navigation.getPosition() == 2){
            if(text.getText().toString().equals("")){
                Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        showLoading();
        NetRequest.postmoreRequest(url, this, map, file, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33", result);
                closeLoading();
                Toast.makeText(putContentActivity.this,"提交成功，等待管理员审核",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e("33",e.getMessage());
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 100){
            int i = data.getIntExtra("data", 0);
            list = (List<String>)data.getStringArrayListExtra("tag");
            if(list.size() == 0){
                logosign.setImageResource(R.drawable.tag_icon_grey);
            }
            if(i == 0){
                list = new ArrayList<>();
                initFlow();
                haveChose.setText("添加标签优先审核");
                logosign.setImageResource(R.drawable.tag_icon_grey);
            }else{
                initFlow();
                haveChose.setText("已选"+i+"个标签");
                logosign.setImageResource(R.drawable.tag_icon_red_l);
            }
        }
        if(requestCode == 100 && resultCode == 300){
            cid = data.getStringExtra("cid");
            String title = data.getStringExtra("title");
            icon_more.setText(title+" >");
        }
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            image = Matisse.obtainResult(data);
            initRc();
        }
    }

    public void initFlow(){
        choseFlow.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(putContentActivity.this).inflate(R.layout.search_page_flowlayout_tv3,null);
                textView.setText(s);
                return textView;
            }
        });
    }

    private void initRc() {
        rc.setLayoutManager(new GridLayoutManager(putContentActivity.this,3));
        adapter = new CommunityRcAdapter(R.layout.community_advice_item,image);
        View view = null;
        if(isVideo){
            view = LayoutInflater.from(putContentActivity.this).inflate(R.layout.activity_community_advice_foot, null);
        }else{
            view = LayoutInflater.from(putContentActivity.this).inflate(R.layout.activity_community_advice_foot2, null);
        }
        view.setOnClickListener(putContentActivity.this);
        adapter.addFooterView(view);
        rc.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(isVideo){
            Matisse.from(this)
                    .choose(MimeType.ofVideo(), false) // 选择 mime 的类型
                    .countable(true)
                    .theme(R.style.Matisse_Zhihu)
                    .maxSelectable(1) // 图片选择的最多数量
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f) // 缩略图的比例
                    .imageEngine(new com.mymusic.music.Util.GlideEngine()) // 使用的图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
        }else{
            Matisse.from(this)
                    .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                    .countable(true)
                    .theme(R.style.Matisse_Zhihu)
                    .maxSelectable(9) // 图片选择的最多数量
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f) // 缩略图的比例
                    .imageEngine(new com.mymusic.music.Util.GlideEngine()) // 使用的图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
        }
    }

    public String getRingDuring(Uri mUri){
        String uri = getRealPathFromURI(putContentActivity.this, mUri);
        String duration="30";
        android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();
        try {
            if (mUri != null) {
                HashMap<String, String> headers=null;
                if (headers == null) {
                    headers = new HashMap<String, String>();
                    headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
                }
                mmr.setDataSource(uri, headers);
            }
            duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
        } catch (Exception ex) {

        } finally {
            mmr.release();
        }
        return duration;
    }



    public static void verifyStoragePermissions(Activity activity) {
            // Check if we have write permission
            int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE);
            }
        }

    public File getFileByUri(Uri uri,Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA }, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
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



}
