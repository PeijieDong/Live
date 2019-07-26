package com.mymusic.music.View.Activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.TextView;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class JubaoVideoActiviy extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.Rc)
    RecyclerView rc;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.des)
    EditText des;
    private int REQUEST_CODE_CHOOSE = 2;
    private List<Uri> imageList = new ArrayList<>();
    private String id;
    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("id");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jubao_video_activiy);
    }

    @Override
    protected void LoadData() {
        initRc();
    }

    private void initRc() {
        rc.setLayoutManager(new GridLayoutManager(this,3));
        View view = LayoutInflater.from(this).inflate(R.layout.image_add_layout, null);
        JubaoAdapter adapter = new JubaoAdapter(R.layout.item_jubao_layout,imageList);
        view.setOnClickListener(this);
        adapter.addFooterView(view);
        rc.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Matisse.from(this)
                .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                .countable(true)
                .theme(R.style.Matisse_Zhihu )
                .maxSelectable(1) // 图片选择的最多数量
                .showSingleMediaType(true)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new com.mymusic.music.Util.GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }

    private class JubaoAdapter extends BaseQuickAdapter<Uri,BaseViewHolder> {

        public JubaoAdapter(int layoutResId, @Nullable List<Uri> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Uri item) {
            Glide.with(mContext).load(item).error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.img_jubao));
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
    @OnClick({R.id.post})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.post:
                if(des.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入举报内容",Toast.LENGTH_SHORT);
                    return;
                }
                if(group.getCheckedRadioButtonId() == -1){
                    ToastUtil.show(this,"请选择举报类型",Toast.LENGTH_SHORT);
                    return;
                }
                if(imageList.size() == 0){
                    ToastUtil.show(this,"请上传举报截图",Toast.LENGTH_SHORT);
                    return;
                }
                initNet();
                break;
        }
    }

    private void initNet() {
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("type",group.getCheckedRadioButtonId()+"");
        map.put("content",des.getText().toString());
        map.put("nid",id);
        if(Live.getInstance().getUser(this) != null){
            map.put("touid",Live.getInstance().getUser(this).getData().getId());
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        map.put("file",PicToBase64.imageToBase64(imageList.get(0).getPath()));
        File file = getFileByUri(imageList.get(0), this);
        NetRequest.postFormHeadRequest(UrlManager.Jubao_Video, map ,Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                closeLoading();
                ToastUtil.show(JubaoVideoActiviy.this,"提交成功",Toast.LENGTH_SHORT);
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

    public static File getFileByUri(Uri uri,Context context) {
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
}
