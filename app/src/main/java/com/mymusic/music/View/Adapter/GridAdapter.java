package com.mymusic.music.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 20:59
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class GridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<PinDao.DataBean.ListBeanX.ListBean> list;

    public GridAdapter(Context mContext, List<PinDao.DataBean.ListBeanX.ListBean> list) {
        this.context = mContext;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if(view == null){
            v = inflater.inflate(R.layout.grid_layout, viewGroup,false);
        }else{
            v = view;
        }
        TextView title = v.findViewById(R.id.title);
        title.setText(list.get(i).getTitle());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;

    }
}
