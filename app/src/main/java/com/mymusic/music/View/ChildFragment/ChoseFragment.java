package com.mymusic.music.View.ChildFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mymusic.music.Callback.TagFlowListener;
import com.mymusic.music.DataBean.SignTag;
import com.mymusic.music.R;
import com.mymusic.music.base.BaseFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/6/22 10:34
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ChoseFragment extends BaseFragment implements TagFlowListener{

    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    int position;
    SignTag sign;
    TagFlowListener listener;
    List<String> list;
    List<String> list2 = new ArrayList<>();
    private boolean have = false;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.chose_fragmnet_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        position = bundle.getInt("position");
        sign = (SignTag) bundle.getSerializable("sign");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        list = new ArrayList<>();
        List<SignTag.ListBeanX.ListBean> beans = sign.getList().get(position).getList();
        for (int i=0;i<beans.size();i++){
            list.add(beans.get(i).getTitle());
        }
        flowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.search_page_flowlayout_tv3,null);
                textView.setText(s);
                return textView;
            }
        });
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout parent) {
                TextView text = view.findViewById(R.id.flowText);
                for (int p = 0; p<list2.size();p++){
                    if(list.get(i).equals(list2.get(p))){
                        list2.remove(p);
                        have = true;
                        listener.AcClick(list.get(i));
                        text.setBackgroundResource(R.drawable.tv_bc2);
                        text.setTextColor(ContextCompat.getColor(getContext(),R.color.text_light_gray));
                    }
                }
                if(!have){
                    text.setBackgroundResource(R.drawable.flow_select);
                    text.setTextColor(ContextCompat.getColor(getContext(), R.color.navi_title_color));
                    list2.add(list.get(i));
                    listener.Click(list.get(i));
                }else{
                    have = false;
                }
                return false;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TagFlowListener)
        {
            listener = (TagFlowListener)context;
        }
        else{
            throw new IllegalArgumentException("Activity must implements FragmentListener");
        }

    }

    @Override
    public void Click(String list2) {

    }

    @Override
    public void AcClick(String s) {

    }
}
