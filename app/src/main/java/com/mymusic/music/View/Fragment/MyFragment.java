package com.mymusic.music.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.MainActivity;
import com.mymusic.music.View.Activity.MyActivity.MyLiveActivity;
import com.mymusic.music.View.Activity.MyActivity.MywalletActivity;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;

import butterknife.OnClick;

/**
 * Create By mr.mao in 2019/5/29 21:31
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MyFragment extends BaseFragment {

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

    }

    @OnClick({R.id.my_wallet,R.id.my_live})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.my_wallet:
                Intent intent = new Intent(getContext(), MywalletActivity.class);
                startActivity(intent);
                break;
            case R.id.my_live:
                Intent intent1 = new Intent(getContext(), MyLiveActivity.class);
                startActivity(intent1);
                break;
        }
    }

}
