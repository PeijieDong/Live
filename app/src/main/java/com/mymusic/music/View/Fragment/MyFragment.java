package com.mymusic.music.View.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.ShareFriend;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.DataBean.UserBean;
import com.mymusic.music.Live;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.Util.ToastUtil;
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
import com.mymusic.music.View.Activity.ShareActivity;
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
    @BindView(R.id.fansNum)
    TextView fansNum;
    @BindView(R.id.fabuNum)
    TextView fabuNum;
    @BindView(R.id.collectionNum)
    TextView collectionNum;
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
    @BindView(R.id.my_totalNumber)
    TextView totalNumber;
    @BindView(R.id.my_number)
    TextView number;
    @BindView(R.id.logo_level)
    ImageView logo_level;
    @BindView(R.id.level_back)
    LinearLayout levelBack;
    private User user;
    private UserBean bean;
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
        if(!Live.getInstance().getToken(getContext()).equals("")){
            initUserInfo();
            cl3.setVisibility(View.GONE);
            cl4.setVisibility(View.VISIBLE);
            user = Live.getInstance().get(getContext());
        }else{
            cl3.setVisibility(View.VISIBLE);
            cl4.setVisibility(View.GONE);
            focusNum.setText("0");
            fansNum.setText("0");
            fabuNum.setText("0");
            collectionNum.setText("0");
            goLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void initUserInfo() {
        loading();
        NetRequest.postFormHeadRequest(UrlManager.GetUserInfo, null,Live.getInstance().get(getContext()).getList().getToken(), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                bean = GsonUtil.GsonToBean(result, UserBean.class);
                Live.getInstance().putUser(getContext(),result);
                focusNum.setText(bean.getData().getFollows());
                fansNum.setText(bean.getData().getFans());
                fabuNum.setText(bean.getData().getFabu());
                collectionNum.setText(bean.getData().getShoucang());
                level.setText("LV"+bean.getData().getLevel()+"经验值"+bean.getData().getConsumption()+" >");
                name.setText(bean.getData().getUser_nicename());
                Glide.with(getContext()).load(bean.getData().getAvatar()).into(head);
                String guanying = bean.getData().getGuanying();
                String[] split = guanying.split("/");
                number.setText(split[0]);
                totalNumber.setText("/"+split[1]);
                if(Integer.parseInt(bean.getData().getLevel()) > 5 && Integer.parseInt(bean.getData().getLevel()) < 11){
                    level.setTextColor(ContextCompat.getColor(getContext(),R.color.text_level_10));
                    levelBack.setBackgroundResource(R.drawable.level_back_10);
                    logo_level.setImageResource(R.drawable.icon_user_level_10);
                }
                if(Integer.parseInt(bean.getData().getLevel()) > 10 && Integer.parseInt(bean.getData().getLevel()) < 16){
                    level.setTextColor(ContextCompat.getColor(getContext(),R.color.text_level_15));
                    levelBack.setBackgroundResource(R.drawable.level_back_15);
                    logo_level.setImageResource(R.drawable.icon_user_level_15);
                }
                if(Integer.parseInt(bean.getData().getLevel()) > 15){
                    level.setTextColor(ContextCompat.getColor(getContext(),R.color.text_level_20));
                    levelBack.setBackgroundResource(R.drawable.level_back_20);
                    logo_level.setImageResource(R.drawable.icon_user_level_20);
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
        hideloading();
    }

    @OnClick({R.id.my_setting,R.id.my_level,R.id.my_user_head,
            R.id.my_foucus,R.id.my_fans,R.id.my_publish,R.id.my_collection,R.id.my_cl_task,
            R.id.my_wallet,R.id.my_live,R.id.my_exchange,R.id.my_message,R.id.my_comment,
            R.id.my_like,R.id.my_history,R.id.my_feedback,R.id.my_about,R.id.my_community,R.id.my_share})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.my_setting:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MysettingActivity.class);
                }
                break;
            case R.id.my_level:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MylevelActivity.class);
                }
                break;
            case R.id.my_user_head:
                if(Live.getInstance().getUser(getContext()) != null){
                    Intent intent = new Intent(getContext(), UserDetailActivity.class);
                    intent.putExtra("UserId",Live.getInstance().getUser(getContext()).getData().getId());
                    startActivity(intent);
                }
                break;
            case R.id.my_foucus:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MyfocusActivity.class);
                }
                break;
            case R.id.my_fans:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MyfansActivity.class);
                }
                break;
            case R.id.my_publish:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MypubliskActivity.class);
                }
                break;
            case R.id.my_collection:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MycollectionActivity.class);
                }
                break;
            case R.id.my_cl_task:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MytaskActivity.class);
                }
                break;
            case R.id.my_wallet:
                ToastUtil.show(getContext(),"敬请期待",1);
//                if(Live.getInstance().getUser(getContext()) != null){
//                    goActivity(MywalletActivity.class);
//                }
                break;
            case R.id.my_live:
                if(Live.getInstance().getUser(getContext()) != null){
                    ToastUtil.show(getContext(),"该功能暂未开放",1);
//                    goActivity(MyLiveActivity.class);
                }
                break;
            case R.id.my_exchange:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MyexchangeActivity.class);
                }
                break;
            case R.id.my_message:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MymessageActivity.class);
                }
                break;
            case R.id.my_comment:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MycommentActivity.class);
                }
                break;
            case R.id.my_like:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MylikeActivity.class);
                }
                break;
            case R.id.my_history:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MyhistoryActivity.class);
                }
                break;
            case R.id.my_feedback:
                if(Live.getInstance().getUser(getContext()) != null){
                    goActivity(MyfeedbackActivity.class);
                }
                break;
            case R.id.my_about:
                goActivity(MyaboutActivity.class);
                break;
            case R.id.my_community:
                loading();
                getShare();
                hideloading();
                break;
            case R.id.my_share:
                Intent intent = new Intent(getContext(), ShareActivity.class);
                startActivity(intent);
//                Intent intent1 = new Intent(getContext(), WebActivity.class);
//                intent1.putExtra("url","http://live.shuiqiao.net/users/share");
//                intent1.putExtra("share",true);
//                intent1.putExtra("title","推广分享");
//                startActivity(intent1);
                break;
        }
    }

    private void getShare() {
        NetRequest.postFormRequest(UrlManager.ShareFriend, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                ShareFriend bean = GsonUtil.GsonToBean(result, ShareFriend.class);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse(bean.getData().getList());
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {
                ToastUtil.show(getContext(),"网络请求失败",1);
            }
        });
    }

    /**
     * 统一跳转
     * @param clazz
     */
    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(getContext(), clazz);
        startActivity(intent);
    }


    //判断是否展示—与RadioGroup等连用，进行点击切换
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            initLogin();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        initLogin();
    }
}
