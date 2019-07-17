package com.mymusic.music.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;
import com.mymusic.music.Util.PhotoShowDialog;
import com.mymusic.music.base.BaseActivity;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/7 23:11
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomeGridAdapter extends BaseAdapter {
    //自定义Gridview适配器

    private LayoutInflater inflater;
    private Context context;
    private List<String> data;


    public HomeGridAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data != null ? data.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return data != null ? i : 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if(view == null){
            v = inflater.inflate(R.layout.home_grid_layout, viewGroup,false);
        }else{
            v = view;
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoShowDialog dialog = new PhotoShowDialog(context, data, i);
                dialog.show();
            }
        });
        ImageView typeImage = v.findViewById(R.id.grid_image);

        Glide.with(context).load(data.get(i)).placeholder(R.drawable.fq_bottom_transparent).into(typeImage);
        return v;
    }


}
