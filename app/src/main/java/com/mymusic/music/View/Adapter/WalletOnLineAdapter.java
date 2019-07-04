package com.mymusic.music.View.Adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mymusic.music.DataBean.Wallet;
import com.mymusic.music.R;

/**
 * Create By mr.mao in 2019/7/2 21:08
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class WalletOnLineAdapter extends BaseAdapter {

    Context context;
    Wallet bean;
    int i;

    public WalletOnLineAdapter(Context context, Wallet bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().getList().getOnline().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getList().getOnline().get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_wallet_layout, parent, false);
            holder.money = convertView.findViewById(R.id.money);
            holder.back = convertView.findViewById(R.id.back);
            holder.num = convertView.findViewById(R.id.num);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.money.setText("￥"+bean.getData().getList().getOnline().get(position).getMoney());
        holder.num.setText(bean.getData().getList().getOnline().get(position).getCoin());
        if(i == position){
            holder.back.setBackgroundResource(R.drawable.wallet_back_press);
        }else{
            holder.back.setBackgroundResource(R.drawable.wallet_back_normal);
        }
        return convertView;
    }

    public void setPosition(int position){
        this.i = position;
    }
    class ViewHolder{
        private TextView money;
        private ConstraintLayout back;
        private TextView num;
    }
}