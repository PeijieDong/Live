package com.mymusic.music.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.VideoPindaoActivity;

import java.util.List;


/**
 * Create By mr.mao in 2019/8/13 20:41
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class GridAdapter2 extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<PinDao.DataBean.ListBeanX.ListBean> list;
    private int select;
    private GridAdapter.ItemListener listener;

    public GridAdapter2(Context mContext, List<PinDao.DataBean.ListBeanX.ListBean> list) {
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
        title.setText(list.get(i).getTitle());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , VideoPindaoActivity.class);
                intent.putExtra("id",list.get(i).getId());
                intent.putExtra("title",list.get(i).getTitle());
                context.startActivity(intent);
            }
        });
        if(select == i){
            if(hook.getVisibility() == View.GONE){
                if(listener != null){
                    hook.setVisibility(View.VISIBLE);
                    title.setBackgroundResource(R.drawable.grid_press_back);
                }
            }else{
                listener.remove(list.get(i).getTitle());
                hook.setVisibility(View.GONE);
                title.setBackgroundResource(R.drawable.grid_normal_back);
            }
        }
        return v;

    }

    public void setSelect(int position){
        this.select = position;
        notifyDataSetChanged();
    }
    public interface ItemListener{
        boolean click(String title);
        void remove(String title);
    }
    public void setListener(GridAdapter.ItemListener listener){
        this.listener = listener;
    }
}
