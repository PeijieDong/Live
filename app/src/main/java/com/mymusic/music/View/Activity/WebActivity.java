package com.mymusic.music.View.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mymusic.music.R;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyshareActivity;
import com.mymusic.music.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {

    @BindView(R.id.web)
    WebView webView;
    @BindView(R.id.share)
    LinearLayout share;
    @BindView(R.id.copy)
    TextView copy;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.share_cdeo)
    LinearLayout shareCode;
    @BindView(R.id.ewCode)
    ImageView ewCode;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.web_title)
    TextView tv_title;
    String url;
    Boolean isshare = false;
    String title = "";

    @Override
    protected void initVariables(Intent intent) {
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        isshare = intent.getBooleanExtra("share",false);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void LoadData() {
        tv_title.setText(title);
        ewCode.setImageBitmap(setCode(url,600,600));
        if(isshare){
            share.setVisibility(View.VISIBLE);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCode.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                saveQrcodeToGallery();
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) WebActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(null, url);
                clipboard.setPrimaryClip(clipData);
                ToastUtil.show(WebActivity.this,"复制成功，快去分享吧",Toast.LENGTH_SHORT);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCode.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(url);
    }


    public Bitmap setCode(String contents,int width,int height){

        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");    //指定字符编码为“utf-8”
        //hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  //二维码纠错等级为中级
        hints.put(EncodeHintType.MARGIN, 1);    //设置图片的边距
        QRCodeWriter qrCodeWriter = new QRCodeWriter();//获得一个写二维码的对象
        try {
            //定义一个矩阵,接收生成的二维码,这里根据传进来的宽(width)高(height)和内容(contents)来生成二维码
            BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE,width,height,hints);
            //定义一个大小为二维码宽高的数组，画出其中每一位的颜色（画二维码）
            int[] arr = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (bitMatrix.get(j, i)) {
                        arr[i * width + j] = 0x00000000;
                    } else {
                        arr[i * width + j] = 0xffffffff;
                    }
                }
            }
            //使用Bitmap的createBitmap方法将arr数组创建为一个位图
            return Bitmap.createBitmap(arr,0,width,width,height,Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    //生成页面并保存至图库
    private void saveQrcodeToGallery() {
        //创建视图
        View qrcodeView = getLayoutInflater().inflate(R.layout.share_code, null, false);
        ((ImageView)qrcodeView.findViewById(R.id.ewCode)).setImageDrawable(ewCode.getDrawable());
        //计算视图大小
        DisplayMetrics displayMetrics = getWindowDisplayMetrics(this);
        final int width = displayMetrics.widthPixels;
        final int height = displayMetrics.heightPixels - getStatusBarHeight(this) - getActionBarHeight(this) - getResources().getDimensionPixelSize(R.dimen.dp_10);

        //将视图生成图片
        Bitmap image = generateImageFromView(qrcodeView, width, height);

        //将图片保存到系统相册
        boolean isSuccess = saveImageToGallery(this, image);
        image.recycle();
        if (isSuccess) {
            ToastUtil.show(this, "已保存到系统相册！", Toast.LENGTH_SHORT);
        } else {
            ToastUtil.show(this, "保存到系统相册失败！", Toast.LENGTH_SHORT);
        }
    }
    public static int getActionBarHeight(Context context) {
        int[] attrs = {android.R.attr.actionBarSize};
        TypedArray values = context.getTheme().obtainStyledAttributes(attrs);
        int actionBarHeight = values.getDimensionPixelSize(0, 0);
        values.recycle();

        if (actionBarHeight <= 0) {
            actionBarHeight = context.getResources().getDimensionPixelSize(R.dimen.dp_10);
        }

        return actionBarHeight;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        } else {
            result = context.getResources().getDimensionPixelSize(R.dimen.dp_10);
        }
        return result;
    }

    public static DisplayMetrics getWindowDisplayMetrics(Context context) {
        WindowManager wm = (android.view.WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static Bitmap generateImageFromView(View view, int width, int height) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        view.layout(0, 0, width, height);
        Bitmap image = Bitmap.createBitmap(view.getDrawingCache());
        view.destroyDrawingCache();

        return image;
    }

    /**
     * 将图片保存到系统相册
     *
     * @param context
     * @param bmp
     * @return
     */
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {

        String galleryPath = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_PICTURES;
        File galleryDir = new File(galleryPath);
        if (!galleryDir.exists()) {
            galleryDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(galleryPath, fileName);

        FileOutputStream fos = null;
        boolean isSuccess = false;

        try {
            fos = new FileOutputStream(file);
            isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return isSuccess;
    }
    @OnClick({R.id.my_share,R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.my_share:
                Intent intent = new Intent(this, MyshareActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
