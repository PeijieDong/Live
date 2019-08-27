package com.mymusic.music.View.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mymusic.music.R;

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
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("33","刷新一次");
        ViewHolder holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.grid_layout_2, viewGroup,false);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.title);
            holder.hook = view.findViewById(R.id.hook);
            holder.back = view.findViewById(R.id.back);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(list.get(i));
        ViewHolder finalHolder = holder;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(finalHolder.hook.getVisibility() == View.GONE){
//                    if(listener != null && listener.click(list.get(i))){
//                        finalHolder.hook.setVisibility(View.VISIBLE);
//                        finalHolder.back.setBackgroundResource(R.drawable.grid_press_back);
//                    }
//                }else{
//                    finalHolder.hook.setVisibility(View.GONE);
//                    finalHolder.back.setBackgroundResource(R.drawable.grid_normal_back_2);
//                    listener.remove(list.get(i));
//                }
//                notifyDataSetChanged();
                listener.ClickEvent(list.get(i));
            }
        });
        return view;

    }

    class ViewHolder{
        TextView title;
        ImageView hook;
        LinearLayout back;
    }

    public void setSelect(int position){
        Log.d("33","调用刷新一次");
        this.select = position;
    }
    public interface ItemListener{
        boolean click(String title);
        void remove(String title);

        void ClickEvent(String title);
    }
    public void setListener(ItemListener listener){
        this.listener = listener;
    }
}
