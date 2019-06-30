package com.mymusic.music.View.Activity.user;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.DataBean.UserBean;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

public class UserActivity extends BaseActivity {

    UserBean user;
//    @BindView(R.id.head)
//    CircleImageView head;
    @BindView(R.id.name)
    TextView nametv;
    @BindView(R.id.sign)
    TextView signtv;
    @BindView(R.id.sex)
    TextView sextv;
    @BindView(R.id.birthday)
    TextView birthdaytv;
    private String [] item = {"男","女"};


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user);
    }

    @Override
    protected void LoadData() {
        initView();
    }

    private void initView() {
        user = Live.getInstance().getUser(this);
//        Glide.with(this).load(user.getList().getAvatar()).into(head);
        nametv.setText(user.getData().getUser_nicename());
        sextv.setText(user.getData().getSex());
        birthdaytv.setText(user.getData().getBirthday());
    }

    @OnClick({R.id.change_name,R.id.change_sign,R.id.change_sex,R.id.change_birthday})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.change_name:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请输入新昵称");
                EditText et = new EditText(this);
                builder.setView(et);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(et.getText().toString().equals("")){
                            Toast.makeText(UserActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        initNet(et.getText().toString(), signtv.getText().toString(), sextv.getText().toString(),  birthdaytv.getText().toString());
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.change_sign:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("请输入新签名");
                EditText et2 = new EditText(this);
                builder2.setView(et2);
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(et2.getText().toString().equals("")){
                            Toast.makeText(UserActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        initNet(nametv.getText().toString(), et2.getText().toString(), sextv.getText().toString(),  birthdaytv.getText().toString());
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
                break;
            case R.id.change_sex:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setTitle("请选择你的性别");
                builder3.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initNet(nametv.getText().toString(), signtv.getText().toString(), item[which], birthdaytv.getText().toString());
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog3 = builder3.create();
                dialog3.show();
                break;
            case R.id.change_birthday:
                Calendar calendar  = Calendar.getInstance();
                final DatePickerDialog dialog4 = new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog4.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog4.setButton(DialogInterface.BUTTON_NEGATIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        int year = dialog4.getDatePicker().getYear();
                        int month = dialog4.getDatePicker().getMonth()+1;
                        int day = dialog4.getDatePicker().getDayOfMonth();
                        String dateStr = year + "年" + month + "月" + day+"日";
                        initNet(nametv.getText().toString(),signtv.getText().toString(),sextv.getText().toString(),dateStr);
                        dialog1.dismiss();
                    }
                });
                dialog4.show();
                break;
        }
    }

    private void initNet(String name, String sign, String sex, String birthday) {
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("nickname",name);
        map.put("signature",sign);
        map.put("sex",sex);
        map.put("birthday",birthday);
        NetRequest.postFormHeadRequest(UrlManager.Change_Info, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                nametv.setText(name);
                signtv.setText(sign);
                sextv.setText(sex);
                birthdaytv.setText(birthday);
                Toast.makeText(UserActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(UserActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
