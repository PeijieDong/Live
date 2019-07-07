package com.mymusic.music.View.Activity.Community;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.CommunityRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommunityReportActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.community_camera)
    LinearLayout camera;
    @BindView(R.id.community_advice_rc)
    RecyclerView Rc;
    private int REQUEST_CODE_CHOOSE = 1;
    private List<Uri> mSelected;
    private CommunityRcAdapter adapter;
    private List<Uri> list;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_community_report);
    }

    @Override
    protected void LoadData() {
        list = new ArrayList<>();
        initRc();
    }

    private void initRc() {
        Rc.setLayoutManager(new GridLayoutManager(this,3));
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

                break;
        }
    }

    private void initCamera() {
        Matisse.from(this)
                .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                .countable(true)
                .theme(R.style.Matisse_Zhihu )
                .maxSelectable(9) // 图片选择的最多数量
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
}
