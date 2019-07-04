package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.UserBean;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

public class MyexchangeActivity extends BaseActivity {

    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.exchange_Code)
    EditText code;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myexchange);
    }

    @Override
    protected void LoadData() {
        UserBean user = Live.getInstance().getUser(this);
        Glide.with(this).load(user.getData().getAvatar()).into(head);
        name.setText(user.getData().getUser_nicename());
    }
    @OnClick({R.id.exchange,R.id.back})
    public void Click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.exchange:
                if(code.getText().toString().equals("")){
                    Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
                initChange();
                break;
        }
    }

    private void initChange() {
        checkLogin();
        HashMap<String, String> map = new HashMap<>();
        map.put("code",code.getText().toString());
        NetRequest.postFormHeadRequest(UrlManager.Exchange, map, Live.getInstance().getToken(MyexchangeActivity.this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(MyexchangeActivity.this,"兑换成功",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e("33",e.getMessage());
                Toast.makeText(MyexchangeActivity.this,"兑换失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
