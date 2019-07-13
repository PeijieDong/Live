package com.mymusic.music.View.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.mymusic.music.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;

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
    String url;
    String isshare = "";

    @Override
    protected void initVariables(Intent intent) {
        url = intent.getStringExtra("url");
        isshare = intent.getStringExtra("share");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
        ewCode.setImageBitmap(setCode(url,600,600));
    }

    @Override
    protected void LoadData() {
        if(isshare.equals("1")){
            share.setVisibility(View.VISIBLE);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCode.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                Toast.makeText(WebActivity.this,"保存成功，快去分享吧",Toast.LENGTH_SHORT).show();
                saveImageToGallery(WebActivity.this,createBitmap3(shareCode,800,800),"share_code");
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) WebActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(null, url);
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(WebActivity.this,"复制成功，快去分享吧",Toast.LENGTH_SHORT).show();
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

    /**
     * 保存图片到指定路径
     *
     * @param context
     * @param bitmap   要保存的图片
     * @param fileName 自定义图片名称
     * @return
     */
    public static boolean saveImageToGallery(Context context, Bitmap bitmap, String fileName) {
        // 保存图片至指定路径
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "qrcode";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片(80代表压缩20%)
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();

            //发送广播通知系统图库刷新数据
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Bitmap createBitmap3(View v, int width, int height) {
        //测量使得view指定大小
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        //调用layout方法布局后，可以得到view的尺寸大小
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
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

}
