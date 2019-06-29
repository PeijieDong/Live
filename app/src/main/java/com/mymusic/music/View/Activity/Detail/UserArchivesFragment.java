package com.mymusic.music.View.Activity.Detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mymusic.music.DataBean.UserInfo;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/7 18:07
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserArchivesFragment extends BaseFragment {
    @BindView(R.id.shenfen)
    TextView shenfen;
    @BindView(R.id.jingyan)
    TextView jingyan;
    @BindView(R.id.xingbie)
    TextView xingbie;
    @BindView(R.id.diqu)
    TextView diqu;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.shengri)
    TextView shengri;
    @BindView(R.id.xingquxiang)
    TextView xingquxiang;
    @BindView(R.id.hunlian)
    TextView hunlian;
    @BindView(R.id.jiaoyou)
    TextView jiaoyou;
    @BindView(R.id.zhanghao)
    TextView zhanghao;
    @BindView(R.id.qianming)
    TextView qianming;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_user_archives,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","9999");
        NetRequest.postFormRequest(UrlManager.User_Info, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                UserInfo bean = GsonUtil.GsonToBean(result, UserInfo.class);
                shenfen.setText("身份："+bean.getData().getList().getShenfen());
                jingyan.setText("经验值："+bean.getData().getList().getScore());
                xingbie.setText("性别："+bean.getData().getList().getSex());
                diqu.setText("地区："+bean.getData().getList().getCity());
                zhuce.setText("注册日期："+bean.getData().getList().getCreate_time());
                shengri.setText("生日："+bean.getData().getList().getBirthday());
                xingquxiang.setText("性取向："+bean.getData().getList().getSexf());
                hunlian.setText("婚恋："+bean.getData().getList().getHunlian());
                jiaoyou.setText("交友意向："+bean.getData().getList().getYixiang());
                zhanghao.setText(bean.getData().getList().getStatus());
                qianming.setText(bean.getData().getList().getSignature());
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    @Override
    protected void LoadData() {

    }
}
