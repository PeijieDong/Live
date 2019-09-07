package com.mymusic.music.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.NewVideo;
import com.mymusic.music.DataBean.Title;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.BottomNavigation;
import com.mymusic.music.Util.DpPxUtils;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.HorizontalListView;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.TabNavigation;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.Util.TopNavigation;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Adapter.RcAdapterVideo2;
import com.mymusic.music.View.Adapter.RcAdpaterVideo;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Request;

public class VideoPindaoActivity extends BaseActivity {

    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.tab_Collection)
    ListView tab_Collection;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    private static String title1 ="";
    private static String title2 ="";
    private static String title3 ="";
    private static String title4 ="";
    private static String title5 ="";
    private String id;
    String title;
    private int pager=0;

    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_pindao);
    }

    @Override
    protected void LoadData() {
        titleTv.setText(title);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pager = 0;
                initRc();
            }
        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                pager ++ ;
//                initRc();
//            }
//        });
        initTitle();
    }

    private void initTitle() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pid",id);
        NetRequest.getFormRequest(UrlManager.GET_TITLE, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33","title:"+result);
                Title bean = GsonUtil.GsonToBean(result, Title.class);
                tab_Collection.setAdapter(new TabListAdapter(VideoPindaoActivity.this,bean.getData().getList()));
                title1 = bean.getData().getList().get(0).getList().get(0).getDid();
                title2 = bean.getData().getList().get(1).getList().get(0).getCid();
                title3 = bean.getData().getList().get(2).getList().get(0).getId();
                initRc();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    private void initRc() {
        loading();
        HashMap<String, String> map = new HashMap<>();
        map.put("cid",title1);
        map.put("did",title2);
        map.put("pid",title3);
//        map.put("cate4",title4);
//        map.put("cate5",title5);
        map.put("page",pager+"");
        Log.d("33","title1"+title3+"title2"+title3+"title3"+title3);
        NetRequest.getFormRequest(UrlManager.GET_VIDEO, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                hideloading();
                NewVideo bean = GsonUtil.GsonToBean(result, NewVideo.class);
                RcAdapterVideo2 videoAdapter = null;
                List<NewVideo.DataBean.ListBean> data = new ArrayList<>();
                if(pager == 0){
                    refreshLayout.finishRefresh();
                    data = bean.getData().getList();
                    rc.setLayoutManager(new GridLayoutManager(VideoPindaoActivity.this,3));
                    videoAdapter = new RcAdapterVideo2(R.layout.gridtype2_layout,data);
                    videoAdapter.setEmptyView(LayoutInflater.from(VideoPindaoActivity.this).inflate(R.layout.empty_layout,null));
                    videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(VideoPindaoActivity.this, DetailsActivity.class);
                            intent.putExtra("id",bean.getData().getList().get(position).getId());
                            intent.putExtra("new",true);
                            startActivity(intent);
                        }
                    });
                    rc.setAdapter(videoAdapter);
                }
//                else{
//                    if(bean.getData().getList() == null || bean.getData().getList().size()==0){
//                        ToastUtil.show(VideoPindaoActivity.this,"没有更多数据了",1);
//                        return;
//                    }
//                    for (NewVideo.DataBean.ListBean databean : bean.getData().getList()){
//                        data.add(databean);
//                    }
//                    videoAdapter.setNewData(data);
//                    videoAdapter.notifyDataSetChanged();
//                    refreshLayout.finishLoadMore();
//                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                refreshLayout.finishRefresh();
                hideloading();
            }

            @Override
            public void TokenFail() {
                refreshLayout.finishRefresh();
                hideloading();
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }


    private class videoListAdapter extends BaseAdapter {
        Context context;
        List<Title.DataBean.ListBeanX.ListBean> data ;
        private int i = 0;
        public videoListAdapter(Context context, List<Title.DataBean.ListBeanX.ListBean> data) {
            this.context=context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data == null ? 0 : data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.tabnavigation_layout, parent, false);
                holder.text = convertView.findViewById(R.id.btText);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.text.setText(data.get(position).getTitle());
            if(i == position){
                holder.text.setBackgroundResource(R.drawable.wallet_back_press);
            }else{
                holder.text.setBackgroundResource(R.drawable.wallet_back_normal);
            }
            return convertView;
        }

        public void setCurrPosition(int position){
            this.i = position;
            notifyDataSetChanged();
        }

    }

    class ViewHolder{
        TextView text;
    }
    class ViewHolder2{
        HorizontalListView tab;
    }
    private class TabListAdapter extends BaseAdapter {
        Context context;
        List<Title.DataBean.ListBeanX> data;
        public TabListAdapter(Context context, List<Title.DataBean.ListBeanX> data) {
            this.context = context;
            this.data = data;
        }
        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data == null ? 0 : data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder2 holder = null;
            if(convertView == null) {
                holder = new ViewHolder2();
                convertView = LayoutInflater.from(context).inflate(R.layout.tab_navigation_layout, parent, false);
                holder.tab = convertView.findViewById(R.id.tab);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder2) convertView.getTag();
            }

            videoListAdapter adapter = new videoListAdapter(VideoPindaoActivity.this, data.get(position).getList());
            holder.tab.setAdapter(adapter);
            holder.tab.setCurrtPosition(position);

            if(position == 0){
                title1 = data.get(position).getList().get(0).getDid();
            }
            if(position == 1){
                title2 = data.get(position).getList().get(0).getCid();
            }
            if(position == 2){
                title3 = data.get(position).getList().get(0).getId();
            }
            ViewHolder2 finalHolder = holder;
            holder.tab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.setCurrPosition(position);
                    int finalI = finalHolder.tab.getCurrtPosition();
                    if(finalI == 0){
                        title1 = data.get(finalI).getList().get(position).getDid();
                    }
                    if(finalI == 1){
                        title2 = data.get(finalI).getList().get(position).getCid();
                    }
                    if(finalI == 2){
                        title3 = data.get(finalI).getList().get(position).getId();
                    }
                    initRc();
                }
            });

            return convertView;
        }
    }
}
