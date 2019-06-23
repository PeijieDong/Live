package com.mymusic.music.View.Activity.post;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mymusic.music.R;
import com.mymusic.music.Util.BottomNavigation;
import com.mymusic.music.Util.PutBottomNavigation;
import com.mymusic.music.View.Activity.Community.CommunityAdviceActivity;
import com.mymusic.music.View.Activity.FriendFoundActivity;
import com.mymusic.music.View.Adapter.CommunityRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class putContentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.chose_sign)
    LinearLayout choseSign;
    @BindView(R.id.navigation_put)
    PutBottomNavigation navigation;
    @BindView(R.id.content_text)
    EditText text;
    @BindView(R.id.image_icon)
    RecyclerView rc;
    private boolean isVideo = true;
    private List<Uri> image = new ArrayList<>();
    private int REQUEST_CODE_CHOOSE = 2;
    CommunityRcAdapter adapter;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
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
                startActivityForResult(intent1,100);
                break;
            case R.id.chose_sign:
                Intent intent = new Intent(this, SignActivity.class);
                startActivityForResult(intent,200);
                break;
            case R.id.post:
                initNet();
                break;
        }
    }

    private void initNet() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 100){
            List<String> list = (List<String>)data.getStringArrayListExtra("data");
            if(list.size() > 0){
                choseSign.setVisibility(View.GONE);
                flowLayout.setVisibility(View.VISIBLE);
                flowLayout.setAdapter(new TagAdapter<String>(list) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView textView = (TextView) LayoutInflater.from(putContentActivity.this).inflate(R.layout.search_page_flowlayout_tv2,null);
                        textView.setText(s);
                        return textView;
                    }
                });
            }else{
                choseSign.setVisibility(View.VISIBLE);
                flowLayout.setVisibility(View.GONE);
            }
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

}
