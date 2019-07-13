package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mymusic.music.DataBean.LevelDate;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MylevelActivity extends BaseActivity {

    @BindView(R.id.levelNum)
    TextView levelNum;
    @BindView(R.id.level_num)
    TextView level_num;
    @BindView(R.id.expNum)
    TextView expNum;
    @BindView(R.id.level_num2)
    TextView level_num2;
    @BindView(R.id.residue)
    TextView residue;
    @BindView(R.id.back1)
    LinearLayout back1;
    @BindView(R.id.back2)
    LinearLayout back2;
    @BindView(R.id.level_back1)
    ImageView levelBack1;
    @BindView(R.id.level_back2)
    ImageView levelBack2;
    @BindView(R.id.clock1)
    ImageView clock1;
    @BindView(R.id.clock2)
    ImageView clock2;
    @BindView(R.id.clock3)
    ImageView clock3;
    @BindView(R.id.clock4)
    ImageView clock4;
    @BindView(R.id.clock5)
    ImageView clock5;



    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mylevel);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.Level, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                LevelDate bean = GsonUtil.GsonToBean(result, LevelDate.class);
                levelNum.setText("LV "+bean.getData().getList().getNowlevel());
                level_num.setText("Lv."+bean.getData().getList().getNowlevel());
                level_num2.setText("Lv."+bean.getData().getList().getNextlevel());
                expNum.setText(bean.getData().getList().getNowscore()+"/"+bean.getData().getList().getTonext());
                residue.setText("还差"+bean.getData().getList().getTonext()+"点经验值升级到下一等级");
                if(bean.getData().getList().getView() == 1){
                    clock1.setVisibility(View.GONE);
                }
                if(bean.getData().getList().getQuanzi() == 1){
                    clock2.setVisibility(View.GONE);
                }
                if(bean.getData().getList().getComment() == 1){
                    clock3.setVisibility(View.GONE);
                }
                if(bean.getData().getList().getFatie() == 1){
                    clock4.setVisibility(View.GONE);
                }
                if(bean.getData().getList().getDown() == 1){
                    clock5.setVisibility(View.GONE);
                }

                if(Integer.parseInt(bean.getData().getList().getNowlevel()) > 5 && Integer.parseInt(bean.getData().getList().getNowlevel()) < 11){
                    back1.setBackgroundResource(R.drawable.level_back_10);
                    levelBack1.setImageResource(R.drawable.icon_user_level_10);
                    levelBack2.setImageResource(R.drawable.icon_user_level_10);
                }
                if(Integer.parseInt(bean.getData().getList().getNowlevel()) > 10 && Integer.parseInt(bean.getData().getList().getNowlevel()) < 16){
                    back1.setBackgroundResource(R.drawable.level_back_15);
                    levelBack1.setImageResource(R.drawable.icon_user_level_15);
                    levelBack2.setImageResource(R.drawable.icon_user_level_15);
                }
                if(Integer.parseInt(bean.getData().getList().getNowlevel()) > 15){
                    back1.setBackgroundResource(R.drawable.level_back_20);
                    levelBack1.setImageResource(R.drawable.icon_user_level_20);
                    levelBack2.setImageResource(R.drawable.icon_user_level_20);
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(MylevelActivity.this);
                dialog.Show();
            }
        });
    }
    @OnClick({R.id.levelDes,R.id.back})
    public void Clicke(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.levelDes:
                Intent intent = new Intent(this,WebActivity.class);
                intent.putExtra("url","http://live.shuiqiao.net/users/level");
                startActivity(intent);
                break;
        }
    }
}
