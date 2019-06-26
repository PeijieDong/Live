package com.mymusic.music.View.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.Live;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
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
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;
import com.mymusic.music.base.UrlManager;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/5/29 21:31
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MyFragment extends BaseFragment {

    private String token;
    @BindView(R.id.my_user_head)
    CircleImageView head;
    @BindView(R.id.my_user_name)
    TextView name;
    @BindView(R.id.focus_num)
    TextView focusNum;
    @BindView(R.id.my_level)
    TextView level;
    @BindView(R.id.cl1)
    ConstraintLayout cll;
    @BindView(R.id.cl3)
    ConstraintLayout cl3;
    @BindView(R.id.cl4)
    ConstraintLayout cl4;
    @BindView(R.id.go_login)
    Button goLogin;
    private User user;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        initLogin();
    }

    private void initLogin() {
        if(Live.getInstance().get(getContext()) != null){
            cl3.setVisibility(View.GONE);
            cl4.setVisibility(View.VISIBLE);
            user = Live.getInstance().get(getContext());
            Glide.with(this).load(user.getList().getAvatar()).into(head);
            name.setText(user.getList().getUser_nicename());
//            focusNum.setText(user.getList().get);
            level.setText("LV"+user.getList().getLevel()+"经验值 40 >");
        }else{
            cl3.setVisibility(View.VISIBLE);
            cl4.setVisibility(View.GONE);
            goLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @OnClick({R.id.my_setting,R.id.my_level,R.id.my_user_head,
            R.id.my_foucus,R.id.my_fans,R.id.my_publish,R.id.my_collection,R.id.my_cl_task,
            R.id.my_wallet,R.id.my_live,R.id.my_exchange,R.id.my_message,R.id.my_comment,
            R.id.my_like,R.id.my_history,R.id.my_feedback,R.id.my_about,R.id.my_community,R.id.my_share})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.my_setting:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MysettingActivity.class);
                }
                break;
            case R.id.my_level:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MylevelActivity.class);
                }
                break;
            case R.id.my_user_head:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(UserDetailActivity.class);
                }
                break;
            case R.id.my_foucus:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MyfocusActivity.class);
                }
                break;
            case R.id.my_fans:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MyfansActivity.class);
                }
                break;
            case R.id.my_publish:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MypubliskActivity.class);
                }
                break;
            case R.id.my_collection:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MycollectionActivity.class);
                }
                break;
            case R.id.my_cl_task:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MytaskActivity.class);
                }
                break;
            case R.id.my_wallet:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MywalletActivity.class);
                }
                break;
            case R.id.my_live:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MyLiveActivity.class);
                }
                break;
            case R.id.my_exchange:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MyexchangeActivity.class);
                }
                break;
            case R.id.my_message:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MymessageActivity.class);
                }
                break;
            case R.id.my_comment:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MycommentActivity.class);
                }
                break;
            case R.id.my_like:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MylikeActivity.class);
                }
                break;
            case R.id.my_history:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MyhistoryActivity.class);
                }
                break;
            case R.id.my_feedback:
                if(Live.getInstance().get(getContext()) != null){
                    goActivity(MyfeedbackActivity.class);
                }
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
                Intent intent1 = new Intent(getContext(), WebActivity.class);
                intent1.putExtra("url","http://live.shuiqiao.net/users/share");
                startActivity(intent1);
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

    @Override
    public void onResume() {
        super.onResume();
        initLogin();
    }
}
