package com.mymusic.music.View.Activity.post;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.R;
import com.mymusic.music.Util.FileUtils;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.PicToBase64;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.FriendFoundActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class PutVideoActivity extends BaseActivity {

    @BindView(R.id.chose_sign)
    LinearLayout choseSign;
    @BindView(R.id.video_content)
    ImageView Video;
    @BindView(R.id.post)
    TextView Post;
    @BindView(R.id.chose_flow)
    TagFlowLayout flowLayout;
    @BindView(R.id.et_content)
    EditText content;
    @BindView(R.id.have_chose)
    TextView haveChose;
    @BindView(R.id.chose_sign2)
    ImageView logosign;
    private List<String> list;
    private int REQUEST_CODE_CHOOSE = 2;
    private List<Uri> image = new ArrayList<>();

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_put_video);
    }

    @Override
    protected void LoadData() {

    }

    @OnClick({R.id.chose_sign,R.id.post,R.id.video_content,R.id.close})
    public void ClickEvent(View view) {
        switch (view.getId()) {
            case R.id.chose_sign:
                Intent intent = new Intent(this, SignActivity.class);
                startActivityForResult(intent, 200);
                break;
            case R.id.video_content:
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
                break;
            case R.id.post:
                if(content.getText().toString().length()<4){
                    ToastUtil.show(this,"标题不可以少于4个字符",Toast.LENGTH_SHORT);
                    return;
                }
                if(image.size() != 1){
                    ToastUtil.show(this,"请点击选择您要上传的短视频",Toast.LENGTH_SHORT);
                    return;
                }
                if(list == null || list.size() == 0){
                    ToastUtil.show(this,"请选择对应的标签",Toast.LENGTH_SHORT);
                    return;
                }
                showLoading();
                StringBuilder tag = new StringBuilder();
                for(int i = 0;i<list.size();i++){
                    tag.append(list.get(i)+",");
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("tag",tag.toString());
                map.put("content",content.getText().toString());
                map.put("images", getVideoImage(image.get(0)));
//                Uri uri = image.get(0);
//                String[] arr = {MediaStore.Images.Media.DATA};
//                Cursor cursor = this.getContentResolver().query(uri, arr, null, null, null);
//                int img_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                cursor.moveToFirst();
//                String img_path = cursor.getString(img_index);
                File file = getFileByUri(image.get(0),PutVideoActivity.this);
                loading();
                NetRequest.postmoreRequest(UrlManager.Put_Video,this, map, file, new NetRequest.DataCallBack() {
                    @Override
                    public void requestSuccess(String result) throws Exception {
                        closeLoading();
                        ToastUtil.show(PutVideoActivity.this,"提交成功，请等待管理员审核",Toast.LENGTH_SHORT);
                        finish();
                    }

                    @Override
                    public void requestFailure(Request request, IOException e) {
                        closeLoading();
                        ToastUtil.show(PutVideoActivity.this,"上传错误",Toast.LENGTH_SHORT);
                        Log.e("23",e.getMessage());
                    }
                    @Override
                    public void TokenFail() {
                        LoginDialog dialog = new LoginDialog(getActivity());
                        dialog.Show();
                    }
                });
                hideloading();
                break;
            case R.id.close:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 100){
            int i = data.getIntExtra("data", 0);
            if((List<String>)data.getStringArrayListExtra("tag") != null){
                list = (List<String>)data.getStringArrayListExtra("tag");
                flowLayout.setAdapter(new TagAdapter<String>(list) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView textView = (TextView) LayoutInflater.from(PutVideoActivity.this).inflate(R.layout.search_page_flowlayout_tv3,null);
                        textView.setText(s);
                        return textView;
                    }
                });
            }
            if(list.size() == 0){
                logosign.setImageResource(R.drawable.tag_icon_grey);
            }
            if(i == 0){
                list = new ArrayList<>();
                haveChose.setText("添加标签优先审核");
                logosign.setImageResource(R.drawable.tag_icon_grey);
            }else{
                haveChose.setText("已选"+i+"个标签");
                logosign.setImageResource(R.drawable.tag_icon_red_l);
            }
        }
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            image = Matisse.obtainResult(data);
            Glide.with(this).load(image.get(0)).into(Video);
        }
    }

    public String getVideoImage(Uri uri){
        String videoPath = getRealPathFromURI(this,uri);
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);
        Bitmap bitmap = media.getFrameAtTime();
        String base64 = PicToBase64.bitmapToBase64(bitmap);
        Log.e("33",base64);
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
