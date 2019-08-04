package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Adapter.pindaoRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class FindActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.titleRc)
    RecyclerView rc;
    @BindView(R.id.Find)
    TextView findtv;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find);
    }

    @Override
    protected void LoadData() {
        initTitle();
    }

    private void initTitle() {
        NetRequest.getFormRequest(UrlManager.GET_PINDAO, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                PinDao bean = GsonUtil.GsonToBean(result, PinDao.class);
                for (int i = 0 ;i<bean.getData().getList().size();i++){
                    tabLayout.addTab(new TabLayout.Tab().setText(bean.getData().getList().get(i).getTitle()));
                }
                pindaoRcAdapter adapter = new pindaoRcAdapter(R.layout.find_item_layout,bean.getData().getList());
                rc.setAdapter(adapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    @OnClick({R.id.find,R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.find:
                if(findtv.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入搜索内容",1);
                    return;
                }
                Intent intent = new Intent(this, FindDetailActivity.class);
                intent.putExtra("find",findtv.getText().toString());
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
