package com.mymusic.music.View.Activity.message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mymusic.music.DataBean.SettingMes;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.DiyView.SwitchButton;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.accept_mess)
    SwitchButton accept_mess;
    @BindView(R.id.system_mess)
    SwitchButton system_mess;
    @BindView(R.id.interact_mess)
    SwitchButton interact_mess;
    @BindView(R.id.notice_mess)
    SwitchButton notice_mess;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void LoadData() {
        initNet();
        accept_mess.setOnClickListener(new SwitchButton.OnClickListener() {
            @Override
            public void Click(boolean isClick) {
                if(isClick){
                    initOpneClose("getnotice","1",accept_mess,false);
                }else{
                    initOpneClose("getnotice","0",accept_mess,true);
                }
            }
        });
        system_mess.setOnClickListener(new SwitchButton.OnClickListener() {
            @Override
            public void Click(boolean isClick) {
                if(isClick){
                    initOpneClose("sysnotice","1",system_mess,false);
                }else{
                    initOpneClose("sysnotice","0",system_mess,true);
                }
            }
        });
        interact_mess.setOnClickListener(new SwitchButton.OnClickListener() {
            @Override
            public void Click(boolean isClick) {
                if(isClick){
                    initOpneClose("feednotice","1",interact_mess,false);
                }else{
                    initOpneClose("feednotice","0",interact_mess,true);
                }
            }
        });
        notice_mess.setOnClickListener(new SwitchButton.OnClickListener() {
            @Override
            public void Click(boolean isClick) {
                if(isClick){
                    initOpneClose("tongzhinotice","1",notice_mess,false);
                }else{
                    initOpneClose("tongzhinotice","0",notice_mess,true);
                }
            }
        });
    }

    private void initOpneClose(String name, String s, SwitchButton button,boolean state) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("content",s);
        NetRequest.postFormHeadRequest(UrlManager.Get_Message, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(SettingActivity.this,"修改成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e("33",e.getMessage());
                ToastUtil.show(SettingActivity.this,"修改失败",Toast.LENGTH_SHORT);
                if(state){
                    button.toogleOn();
                }else{
                    button.toogleOff();
                }
            }

            @Override
            public void TokenFail() {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initNet() {
        NetRequest.getFormHeadRequest(UrlManager.Get_Message, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                SettingMes bean = GsonUtil.GsonToBean(result, SettingMes.class);
                if(bean.getData().getList().getFeednotice().equals("1")){
                    accept_mess.toogleOn();
                }else{
                    accept_mess.toogleOff();
                }
                if(bean.getData().getList().getFeednotice().equals("1")){
                    system_mess.toogleOn();
                }else{
                    system_mess.toogleOff();
                }
                if(bean.getData().getList().getFeednotice().equals("1")){
                    interact_mess.toogleOn();
                }else{
                    interact_mess.toogleOff();
                }
                if(bean.getData().getList().getFeednotice().equals("1")){
                    notice_mess.toogleOn();
                }else{
                    notice_mess.toogleOff();
                }

            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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
