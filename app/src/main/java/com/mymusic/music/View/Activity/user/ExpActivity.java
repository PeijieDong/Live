package com.mymusic.music.View.Activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.ScroeRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class ExpActivity extends BaseActivity {

    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.Rc)
    RecyclerView rc;
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.level)
    TextView level;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_exp);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.GetExp, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                rc.setLayoutManager(new LinearLayoutManager(ExpActivity.this));
                rc.setAdapter(new ScroeRcAdapter(R.layout.get_scroe_item, null));
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    @OnClick({R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }

    }
}
