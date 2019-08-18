package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Task;
import com.mymusic.music.Live;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.View.Activity.message.SettingActivity;
import com.mymusic.music.View.Activity.user.ExpActivity;
import com.mymusic.music.View.Activity.user.IntegalActivity;
import com.mymusic.music.View.Activity.user.UserActivity;
import com.mymusic.music.View.Adapter.MyTaskRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MytaskActivity extends BaseActivity {

    @BindView(R.id.score)
    TextView Score;
    @BindView(R.id.exp)
    TextView Exp;
    @BindView(R.id.level_exp)
    TextView levelExp;
    @BindView(R.id.Rc1)
    RecyclerView Rc1;
    @BindView(R.id.Rc2)
    RecyclerView Rc2;
    Task bean;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mytask);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.Task, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                bean = GsonUtil.GsonToBean(result, Task.class);
                Rc1.setLayoutManager(new LinearLayoutManager(MytaskActivity.this));
                MyTaskRcAdapter taskAdapter = new MyTaskRcAdapter(R.layout.my_task_layout,bean.getData().getList().get(1).getList());
                taskAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.doit:
                                if (bean.getData().getList().get(1).getList().get(position).getType().equals("1")){
                                    Intent intent1 = new Intent(MytaskActivity.this, WebActivity.class);
                                    intent1.putExtra("url","http://live.shuiqiao.net/users/share");
                                    intent1.putExtra("title","推广分享");
                                    intent1.putExtra("share",true);
                                    startActivity(intent1);
                                }else if(bean.getData().getList().get(1).getList().get(position).getId().equals("2")){
                                    Intent intent = new Intent(MytaskActivity.this, MainActivity.class);
                                    intent.putExtra("position",1);
                                    startActivity(intent);
                                }
                                break;
                        }

                    }
                });
                Rc1.setAdapter(taskAdapter);
                Score.setText(bean.getData().getList().get(0).getScore());
                Exp.setText(bean.getData().getList().get(0).getConsumption());
                Rc2.setLayoutManager(new LinearLayoutManager(MytaskActivity.this));
                MyTaskRcAdapter taskAdapter2 = new MyTaskRcAdapter(R.layout.my_task_layout,bean.getData().getList().get(2).getList());
                taskAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.doit:
                                if(bean.getData().getList().get(2).getList().get(position).getType().equals("8")){
                                    Intent intent = new Intent(MytaskActivity.this, UserActivity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(MytaskActivity.this, MainActivity.class);
                                    intent.putExtra("position", 0);
                                    startActivity(intent);
                                }
                                break;
                        }
                    }
                });
                Rc2.setAdapter(taskAdapter2);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }
    @OnClick({R.id.back,R.id.expScore,R.id.intergal})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.intergal:
                Intent intent = new Intent(this, IntegalActivity.class);
                if(bean != null && bean.getData().getList() != null){
                    intent.putExtra("integal",bean.getData().getList().get(0).getScore());
                }
                startActivity(intent);
                break;
            case R.id.expScore:
                Intent intent2 = new Intent(this, ExpActivity.class);
                if(bean != null && bean.getData().getList() != null){
                    intent2.putExtra("exp",bean.getData().getList().get(0).getConsumption());
                }
                startActivity(intent2);
                break;
        }
    }
}
