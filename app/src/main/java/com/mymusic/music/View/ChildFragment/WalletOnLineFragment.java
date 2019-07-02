package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mymusic.music.DataBean.Wallet;
import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.WalletAdapter;
import com.mymusic.music.View.Adapter.WalletOnLineAdapter;
import com.mymusic.music.base.BaseFragment;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/6/23 20:06
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class WalletOnLineFragment extends BaseFragment {

    @BindView(R.id.grid)
    GridView gridView;
    private Wallet bean;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_wallet_online_layout,container,false);
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
        WalletOnLineAdapter adapter = new WalletOnLineAdapter(getContext(),bean);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setPosition(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
