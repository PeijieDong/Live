package com.mymusic.music.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.VideoPindaoActivity;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 20:59
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class GridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<String> list;
    private int select = -1;
    private ItemListener listener;

    public GridAdapter(Context mContext, List<String> list) {
        this.context = mContext;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        ImageView hook = v.findViewById(R.id.hook);
        LinearLayout back = v.findViewById(R.id.back);
        title.setText(list.get(i));
        Log.d("33","GR："+"select="+select);
        if(select == i){
            Log.d("33","GR："+"i="+i);
            if(hook.getVisibility() == View.GONE){
                if(listener != null){
                    Log.d("33","GR："+list.get(i));
                    hook.setVisibility(View.VISIBLE);
                    back.setBackgroundResource(R.drawable.grid_press_back);
                    listener.click(list.get(select));
                }
            }else{
                hook.setVisibility(View.GONE);
                back.setBackgroundResource(R.drawable.grid_normal_back);
                listener.remove(list.get(select));
            }
        }
        return v;

    }

    public void setSelect(int position){
        this.select = position;
        notifyDataSetInvalidated();
    }
    public interface ItemListener{
        void click(String title);
        void remove(String title);
    }
    public void setListener(ItemListener listener){
        this.listener = listener;
    }
}
