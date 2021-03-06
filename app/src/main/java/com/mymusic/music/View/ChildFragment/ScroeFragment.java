package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.DataBean.Scroe;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.ScroeRcAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/7/10 23:55
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ScroeFragment extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView Rc;
    String position;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.score_get_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        position = bundle.getString("position");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type",position);
        map.put("page","1");
        NetRequest.postFormHeadRequest(UrlManager.Get_Score, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Scroe bean = GsonUtil.GsonToBean(result, Scroe.class);
                Rc.setLayoutManager(new LinearLayoutManager(getContext()));
                ScroeRcAdapter adapter = new ScroeRcAdapter(R.layout.get_scroe_item, bean.getData().getList());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.empty_layout, null);
                adapter.setEmptyView(view);
                Rc.setAdapter(adapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getContext());
                dialog.Show();
            }
        });
    }
}
