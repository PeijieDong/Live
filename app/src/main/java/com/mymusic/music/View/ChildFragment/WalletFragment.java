package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mymusic.music.DataBean.Wallet;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.WalletAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/23 20:05
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class WalletFragment extends BaseFragment {

    @BindView(R.id.grid)
    GridView gridView;
    private Wallet bean;
    private String coin;
    private String money;


    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_wallet_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        bean = (Wallet) bundle.get("wallet");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        WalletAdapter adapter = new WalletAdapter(getContext(),bean);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setPosition(position);
                adapter.notifyDataSetChanged();
                money = bean.getData().getList().getAgent().get(position).getMoney();
                coin = bean.getData().getList().getAgent().get(position).getCoin();
            }
        });
    }

    private void inittopup() {
        checkLogin();
        HashMap<String, String> map = new HashMap<>();
        map.put("money",money);
        map.put("coin",coin);
        map.put("type","3");
        NetRequest.postFormHeadRequest(UrlManager.TopUp, null, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(getContext(),"下单成功",Toast.LENGTH_SHORT).show();
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
    }
}
