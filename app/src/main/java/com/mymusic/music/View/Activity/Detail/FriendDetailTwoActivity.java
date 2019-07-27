package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.FriendDetailTOP;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.user.ListUserActivity;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendDetailTwoActivity extends BaseActivity {

    @BindView(R.id.detail_des)
    TextView describe;
    @BindView(R.id.detail_icon)
    ImageView icon;
    @BindView(R.id.detail_type)
    TextView type;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.head1)
    CircleImageView head1;
    @BindView(R.id.head2)
    CircleImageView head2;
    @BindView(R.id.head3)
    CircleImageView head3;
    @BindView(R.id.head4)
    CircleImageView head4;
    private FriendDetailTOP detail;
    String id;

    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("id");
        detail = (FriendDetailTOP) intent.getSerializableExtra("frienddetail");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_detail_two);
    }

    @Override
    protected void LoadData() {
        describe.setText(detail.getData().getList().getDescription());
        Glide.with(this).load(detail.getData().getList().getIcon()).into(icon);
        name.setText(detail.getData().getList().getTitle());
        type.setText(detail.getData().getList().getName());
        Glide.with(this).load(detail.getData().getList().getUlist().getHead1()).into(head1);
        Glide.with(this).load(detail.getData().getList().getUlist().getHead2()).into(head2);
        Glide.with(this).load(detail.getData().getList().getUlist().getHead3()).into(head3);
        Glide.with(this).load(detail.getData().getList().getUlist().getHead4()).into(head4);
    }
    @OnClick({R.id.friend_detail_user,R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.friend_detail_user:
                Intent intent = new Intent(FriendDetailTwoActivity.this,ListUserActivity.class);
                intent.putExtra("FriendId",id);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }


}
