package com.mymusic.music.View.Activity.post;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
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
import com.mymusic.music.Util.NetRequest;
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
    @BindView(R.id.sign)
    TagFlowLayout flowLayout;
    @BindView(R.id.et_content)
    EditText content;
    @BindView(R.id.have_chose)
    TextView haveChose;

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
                        .theme(R.style.Matisse_Zhihu | R.style.Matisse_Dracula)
                        .maxSelectable(1) // 图片选择的最多数量
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f) // 缩略图的比例
                        .imageEngine(new com.mymusic.music.Util.GlideEngine()) // 使用的图片加载引擎
                        .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
                break;
            case R.id.post:
                if(content.getText().toString().length()<4){
                    Toast.makeText(this,"标题不可以少于4个字符",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(image.size() != 1){
                    Toast.makeText(this,"请点击选择您要上传的短视频",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(list == null || list.size() == 0){
                    Toast.makeText(this,"请选择对应的标签",Toast.LENGTH_SHORT).show();
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
                Uri uri = image.get(0);
                String[] arr = {MediaStore.Images.Media.DATA};
                Cursor cursor = this.getContentResolver().query(uri, arr, null, null, null);
                int img_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String img_path = cursor.getString(img_index);
                File file = new File(img_path);
                NetRequest.postmoreRequest(UrlManager.Put_Video,this, map, file, new NetRequest.DataCallBack() {
                    @Override
                    public void requestSuccess(String result) throws Exception {
                        closeLoading();
                        Toast.makeText(PutVideoActivity.this,"提交成功，请等待管理员审核",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void requestFailure(Request request, IOException e) {

                    }
                });
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
            if(i == 0){

            }else{
                haveChose.setText("已选"+i+"个标签");
            }
        }
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            image = Matisse.obtainResult(data);
            Glide.with(this).load(image.get(0)).into(Video);
        }
    }
}
