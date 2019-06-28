package com.mymusic.music.View.Activity.post;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.BottomNavigation;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.PutBottomNavigation;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

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
    private boolean isVideo = true;
    private List<Uri> image = new ArrayList<>();
    private int REQUEST_CODE_CHOOSE = 2;
    private String cid = "";
    CommunityRcAdapter adapter;
    private String type ;
    private StringBuilder tag = new StringBuilder();
    private List<String> list = new ArrayList<>();
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstapostnceState) {
        setContentView(R.layout.activity_put_content);
    }

    @Override
    protected void LoadData() {
        navigation.addTab(new PutBottomNavigation.Tab().setNormalIcon(R.drawable.icon_home_normal)
                .setPressedIcon(R.drawable.icon_home_pressed).setText("视频").setTextColor(R.color.navi_title_color));
        navigation.addTab(new PutBottomNavigation.Tab().setNormalIcon(R.drawable.icon_home_normal)
                .setPressedIcon(R.drawable.icon_home_pressed).setText("图片").setTextColor(R.color.navi_title_color));
        navigation.addTab(new PutBottomNavigation.Tab().setNormalIcon(R.drawable.icon_home_normal)
                .setPressedIcon(R.drawable.icon_home_pressed).setText("短文").setTextColor(R.color.navi_title_color));
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
    @OnClick({R.id.choseFriend,R.id.chose_sign,R.id.post})
    public void ClickEvent(View view){
        switch (view.getId()){
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
        verifyStoragePermissions(this);
        String url = UrlManager.Post_Video;
        File file = null;
        HashMap<String, String> map = new HashMap<>();
        map.put("cate", cid);
        map.put("type", type);
        map.put("tag", tag.toString());
        map.put("content", title.getText().toString());
        if (navigation.getPosition() == 0) {
            file = new File(image.get(0).getPath());
            map.put("playtime", "0");
            map.put("images", "0");
        } else if (navigation.getPosition() == 1) {
            file = new File(image.get(0).getPath());
            map.put("playtime", "0");
            map.put("images", "0");
        }
        NetRequest.postmoreRequest(url, this, map, file, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33", result);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e("33",e.getMessage());
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 100){
            int i = data.getIntExtra("data", 0);
            list = (List<String>)data.getStringArrayListExtra("tag");
            if(i == 0){

            }else{
                haveChose.setText("已选"+i+"个标签");
            }
        }
        if(requestCode == 100 && resultCode == 300){
            cid = data.getStringExtra("cid");
            String title = data.getStringExtra("title");
            typeTitle.setText(title);
        }
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            image = Matisse.obtainResult(data);
            initRc();
        }
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
                    .theme(R.style.Matisse_Zhihu | R.style.Matisse_Dracula)
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
                    .theme(R.style.Matisse_Zhihu | R.style.Matisse_Dracula)
                    .maxSelectable(9) // 图片选择的最多数量
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f) // 缩略图的比例
                    .imageEngine(new com.mymusic.music.Util.GlideEngine()) // 使用的图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
        }
    }
    public static int getLocalVideoDuration(String videoPath) {
        int duration;
        try {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(videoPath);
            duration = Integer.parseInt(mmr.extractMetadata
                    (MediaMetadataRetriever.METADATA_KEY_DURATION));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return duration;
    }

    // Storage Permissions
      private static final int REQUEST_EXTERNAL_STORAGE = 1;
      private static String[] PERMISSIONS_STORAGE = {
                         Manifest.permission.READ_EXTERNAL_STORAGE,
                         Manifest.permission.WRITE_EXTERNAL_STORAGE };


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

}
