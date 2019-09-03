package com.mymusic.music.View.Activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mymusic.music.DataBean.LevelDate;
import com.mymusic.music.DataBean.Scroe;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.View.Adapter.ScroeRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class ExpActivity extends BaseActivity {

    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.Rc)
    RecyclerView rc;
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.tol_exp)
    TextView tolExp;
    @BindView(R.id.curr_exp)
    TextView currExp;
    @BindView(R.id.instructions)
    TextView instructions;
    String exp;

    @Override
    protected void initVariables(Intent intent) {
        exp = intent.getStringExtra("exp");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_exp);
    }

    @Override
    protected void LoadData() {
        score.setText(exp);
        initLevel();

    }

    private void initLevel() {
        NetRequest.postFormHeadRequest(UrlManager.Level, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                LevelDate bean = GsonUtil.GsonToBean(result, LevelDate.class);
                des.setText("LV"+bean.getData().getList().getNowlevel());
                level.setText("LV"+bean.getData().getList().getNextlevel());
                tolExp.setText("/"+bean.getData().getList().getNextscore());
                currExp.setText(bean.getData().getList().getNowscore()+"");
                progress.setProgress(Integer.valueOf(bean.getData().getList().getNowscore()));
                progress.setMax(Integer.valueOf(bean.getData().getList().getNextscore()));
                String instructionsTv = "<font color='#FFFFFF'>温馨提示：还差</font><font color='#FFE400'>"+bean.getData().getList().getTonext()+"点</font><font color='#FFFFFF'>经验值将</font><font color='#FFE400'>升级</font>" +
                        "<font color='#FFFFFF'>到更高等级</font>";
                instructions.setText(Html.fromHtml(instructionsTv));
                initNet();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(ExpActivity.this);
                dialog.Show();
            }
        });
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","0");
        map.put("page","1");
        NetRequest.postFormHeadRequest(UrlManager.GetExp, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Scroe bean = GsonUtil.GsonToBean(result, Scroe.class);
                rc.setLayoutManager(new LinearLayoutManager(ExpActivity.this));
                rc.setAdapter(new ScroeRcAdapter(R.layout.get_scroe_item, bean.getData().getList()));
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {
                new LoginDialog(ExpActivity.this).Show();
            }
        });
    }

    @OnClick({R.id.back,R.id.get_exp})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.get_exp:
                Intent intent = new Intent(this,WebActivity.class);
                intent.putExtra("url","http://live.shuiqiao.net/users/level");
                intent.putExtra("title","等级说明");
                startActivity(intent);
                break;
        }

    }
}
