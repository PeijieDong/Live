package com.mymusic.music.View.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.Live;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyLiveActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyaboutActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MycollectionActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MycommentActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyexchangeActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyfansActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyfeedbackActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyfocusActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyhistoryActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyhomeActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MylevelActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MylikeActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MymessageActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MypubliskActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MysettingActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyshareActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MytaskActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MywalletActivity;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.OnClick;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/5/29 21:31
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MyFragment extends BaseFragment {

    private String token;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        token = Live.getInstance().getToken(getContext());
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.UserInfo,null,Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });

    }

    @OnClick({R.id.my_setting,R.id.my_level,R.id.my_user_head,
            R.id.my_foucus,R.id.my_fans,R.id.my_publish,R.id.my_collection,R.id.my_cl_task,
            R.id.my_wallet,R.id.my_live,R.id.my_exchange,R.id.my_message,R.id.my_comment,
            R.id.my_like,R.id.my_history,R.id.my_feedback,R.id.my_about,R.id.my_community,R.id.my_share})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.my_setting:
                goActivity(MysettingActivity.class);
                break;
            case R.id.my_level:
                goActivity(MylevelActivity.class);
                break;
            case R.id.my_user_head:
                goActivity(UserDetailActivity.class);
                break;
            case R.id.my_foucus:
                goActivity(MyfocusActivity.class);
                break;
            case R.id.my_fans:
                goActivity(MyfansActivity.class);
                break;
            case R.id.my_publish:
                goActivity(MypubliskActivity.class);
                break;
            case R.id.my_collection:
                goActivity(MycollectionActivity.class);
                break;
            case R.id.my_cl_task:
                goActivity(MytaskActivity.class);
                break;
            case R.id.my_wallet:
                goActivity(MywalletActivity.class);
                break;
            case R.id.my_live:
                goActivity(MyLiveActivity.class);
                break;
            case R.id.my_exchange:
                goActivity(MyexchangeActivity.class);
                break;
            case R.id.my_message:
                goActivity(MymessageActivity.class);
                break;
            case R.id.my_comment:
                goActivity(MycommentActivity.class);
                break;
            case R.id.my_like:
                goActivity(MylikeActivity.class);
                break;
            case R.id.my_history:
                goActivity(MyhistoryActivity.class);
                break;
            case R.id.my_feedback:
                goActivity(MyfeedbackActivity.class);
                break;
            case R.id.my_about:
                goActivity(MyaboutActivity.class);
                break;
            case R.id.my_community:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse("http://www.baidu.com");
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.my_share:
                goActivity(MyshareActivity.class);
                break;
        }
    }

    /**
     * 统一跳转
     * @param clazz
     */
    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(getContext(), clazz);
        startActivity(intent);
    }

}
