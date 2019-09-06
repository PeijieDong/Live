package com.mymusic.music.View.Fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mymusic.music.DataBean.AppUpdate;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.FoundActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.post.putContentActivity;
import com.mymusic.music.View.ChildFragment.HomePagerFragment;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create By mr.mao in 2019/5/29 21:29
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomeFragment extends BaseFragment {

    private Disposable downDisposable;
    private ProgressBar progressBar;
    private TextView textView4;
    private Button upgrade;
    private long downloadLength=0;
    private long contentLength=0;
    @BindView(R.id.home_tab)
    TabLayout tabLayout;
    @BindView(R.id.home_vp)
    ViewPager viewPager;
    String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE ,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_PHONE_STATE};
    private static final int PERMISSIONS = 100;

    private List<String> title;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
//        test();
        title = new ArrayList<>();
        title.add("推荐"); title.add("关注"); title.add("文字"); title.add("图片"); title.add("视频");
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class HomePagerAdapter extends FragmentStatePagerAdapter {


        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            HomePagerFragment fragment = new HomePagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position",i);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return title.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }

    @OnClick({R.id.home_find,R.id.put_content})
    public void OnclickEvent(View view){
        switch (view.getId()){
            case R.id.home_find:
                Intent intent = new Intent(getContext(), FoundActivity.class);
                startActivity(intent);
                break;
            case R.id.put_content:
                if(Live.getInstance().getUser(getContext()) == null ){
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    return;
                }
                if(Integer.parseInt(Live.getInstance().getUser(getContext()).getData().getLevel())<3){
                    ToastUtil.show(getContext(),"只有3级以上用户可以使用",Toast.LENGTH_SHORT);
                    return ;
                }
                startActivity(new Intent(getContext(),putContentActivity.class));
                break;
        }
    }



    private void test(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                HashMap<String, String> map = new HashMap<>();
                map.put("type","android");
                map.put("version",getContext().getPackageManager().
                        getPackageInfo(getContext().getPackageName(), 0).versionCode+"");
                NetRequest.postFormRequest(UrlManager.APP_UPDATE, map, new NetRequest.DataCallBack() {
                    @Override
                    public void requestSuccess(String result) throws Exception {
                        emitter.onNext(result);
                    }

                    @Override
                    public void requestFailure(Request request, IOException e) {
                        ToastUtil.show(getContext(),"版本检查失败",1);
                    }

                    @Override
                    public void TokenFail() {

                    }
                });
            }
        }).subscribeOn(Schedulers.io())// 将被观察者切换到子线程
                .observeOn(AndroidSchedulers.mainThread())// 将观察者切换到主线程
                .subscribe(new Observer<String>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }
                    @Override
                    public void onNext(String result) {
                        if (result.isEmpty()){
                            return;
                        }
                        AppUpdate bean = GsonUtil.GsonToBean(result, AppUpdate.class);
                        //2.判断版本是否最新，如果不是最新版本则更新
                        String downloadUrl=bean.getUrl();
                        String title=bean.getContent();
                        String size="新版本大小：未知";
                        String msg=bean.getReferer();
                        int versionCode=20000;
                        try {
                            int version = getContext().getPackageManager().
                                    getPackageInfo(getContext().getPackageName(), 0).versionCode;
                            if (versionCode>version){
                                LayoutInflater inflater = LayoutInflater.from(getContext());
                                View view = inflater.inflate(R.layout.layout_dialog, null);
                                Dialog mDialog = new Dialog(getContext(),R.style.Translucent_NoTitle);
                                mDialog.setContentView(view);
                                mDialog.setCancelable(true);
                                mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                    @Override
                                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                        return keyCode == KeyEvent.KEYCODE_BACK;
                                    }
                                });
                                upgrade= view.findViewById(R.id.button);
                                TextView textView1= view.findViewById(R.id.textView1);
                                TextView textView2= view.findViewById(R.id.textView2);
                                TextView textView3= view.findViewById(R.id.textView3);
                                textView4= view.findViewById(R.id.textView4);
                                ImageView iv_close= view.findViewById(R.id.iv_close);
                                progressBar= view.findViewById(R.id.progressBar);
                                progressBar.setMax(100);
                                textView1.setText(title);
                                textView2.setText(size);
                                textView3.setText(msg);
                                upgrade.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //动态询问是否授权
                                        int permission = ActivityCompat.checkSelfPermission(getActivity().getApplication(),
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE);
                                        if (permission != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(getActivity(), perms,
                                                    PERMISSIONS);
                                        }else {
                                            upgrade.setVisibility(View.INVISIBLE);
                                            down(downloadUrl);
                                        }
                                    }
                                });
                                iv_close.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mDialog.dismiss();
                                    }
                                });
                                mDialog.show();
                            }else {

                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        mDisposable.dispose();
                    }
                    @Override
                    public void onError(Throwable e) {
                        test();
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void down(String downloadUrl){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                downApk(downloadUrl,emitter);
            }
        }).subscribeOn(Schedulers.io())// 将被观察者切换到子线程
                .observeOn(AndroidSchedulers.mainThread())// 将观察者切换到主线程
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        downDisposable = d;
                    }
                    @Override
                    public void onNext(Integer result) {
                        //设置ProgressDialog 进度条进度
                        progressBar.setProgress(result);
                        textView4.setText(result+"%");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),"网络异常！请重新下载！",Toast.LENGTH_SHORT).show();
                        upgrade.setEnabled(true);
                    }
                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(),"服务器异常！请重新下载！",Toast.LENGTH_SHORT).show();
                        upgrade.setEnabled(true);
                    }
                });
    }

    private void downApk(String downloadUrl,ObservableEmitter<Integer> emitter){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //下载失败
                breakpoint(downloadUrl,emitter);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    //下载失败
                    breakpoint(downloadUrl,emitter);
                    return;
                }
                InputStream is = null;
                FileOutputStream fos = null;
                byte[] buff = new byte[2048];
                int len;
                try {
                    is = response.body().byteStream();
                    File file = createFile();
                    fos = new FileOutputStream(file);
                    long total = response.body().contentLength();
                    contentLength=total;
                    long sum = 0;
                    while ((len = is.read(buff)) != -1) {
                        fos.write(buff,0,len);
                        sum+=len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中，更新下载进度
                        emitter.onNext(progress);
                        downloadLength=sum;
                    }
                    fos.flush();
                    //4.下载完成，安装apk
                    installApk(getContext(),file);
                } catch (Exception e) {
                    e.printStackTrace();
                    breakpoint(downloadUrl,emitter);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                        if (fos != null)
                            fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void breakpoint(String downloadUrl,ObservableEmitter<Integer> emitter){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //下载失败
                breakpoint(downloadUrl,emitter);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    //下载失败
                    breakpoint(downloadUrl,emitter);
                    return;
                }
                InputStream is = null;
                RandomAccessFile randomFile = null;
                byte[] buff = new byte[2048];
                int len;
                try {
                    is = response.body().byteStream();
                    String root = Environment.getExternalStorageDirectory().getPath();
                    File file = new File(root,"updateDemo.apk");
                    randomFile = new RandomAccessFile(file, "rwd");
                    randomFile.seek(downloadLength);
                    long total = contentLength;
                    long sum = downloadLength;
                    while ((len = is.read(buff)) != -1) {
                        randomFile.write(buff,0,len);
                        sum+=len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中，更新下载进度
                        emitter.onNext(progress);
                        downloadLength=sum;
                    }
                    //4.下载完成，安装apk
                    installApk(getContext(),file);
                } catch (Exception e) {
                    e.printStackTrace();
                    breakpoint(downloadUrl,emitter);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                        if (randomFile != null)
                            randomFile.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private File createFile() {
        String root = Environment.getExternalStorageDirectory().getPath();
        File file = new File(root,"updateDemo.apk");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public void installApk(Context context, File file) {
        if (context == null) {
            return;
        }
        String authority = getContext().getApplicationContext().getPackageName() + ".fileProvider";
        Uri apkUri = FileProvider.getUriForFile(context, authority, file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }

        context.startActivity(intent);
        //弹出安装窗口把原程序关闭。
        //避免安装完毕点击打开时没反应
        getActivity().finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(downDisposable != null){
            downDisposable.dispose();//取消订阅
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.navi_title_color));
        }else{
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }
}
