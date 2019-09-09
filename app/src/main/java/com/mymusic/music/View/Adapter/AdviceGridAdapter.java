package com.mymusic.music.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mymusic.music.R;

import java.util.List;

public class AdviceGridAdapter extends BaseAdapter {

    Context context;
    List<String> list;
    private int lastPosition = 0;
    private OnItemClickListener listener;

    public AdviceGridAdapter(Context context, List<String> title) {
        this.context = context;
        this.list = title;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_advice, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.addPosition(position);
                }
            });
            holder.title = convertView.findViewById(R.id.titleTv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(lastPosition == position){
            holder.title.setBackgroundResource(R.drawable.navi_slide_press);
            holder.title.setTextColor(context.getResources().getColor(R.color.white));
        }else{
            holder.title.setBackgroundResource(R.drawable.navi_slide_normal);
            holder.title.setTextColor(context.getResources().getColor(R.color.blank));
        }
        holder.title.setText(list.get(position));
        return convertView;
    }

    public void setPosition(int position){
        this.lastPosition = position;
        notifyDataSetInvalidated();
    }

    public interface OnItemClickListener{
        void addPosition(int position);
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }

    class ViewHolder{
        TextView title;
    }

}
