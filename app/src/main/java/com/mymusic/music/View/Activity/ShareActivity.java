package com.mymusic.music.View.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mymusic.music.DataBean.Yaoqing;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.MyChildActivity.My.MyshareActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.code)
    ImageView code;
    @BindView(R.id.copy_text)
    TextView copyText;
    @BindView(R.id.share_earnings)
    TextView shareEarning;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_share);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        loading();
        NetRequest.postFormRequest(UrlManager.YAOQING, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                hideloading();
                Yaoqing bean = GsonUtil.GsonToBean(result, Yaoqing.class);
                shareEarning.setText(Html.fromHtml(bean.getData().getInvitdetails()));
                ViewTreeObserver vto2 = code.getViewTreeObserver();
                vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        code.setImageBitmap(setCode(bean.getData().getQrcodeimg(),code.getWidth(),code.getHeight()));
                    }
                });

                copyText.setText(bean.getData().getInvitecontent());
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                hideloading();
            }

            @Override
            public void TokenFail() {
                hideloading();
            }
        });
    }


    /**
     * 生成二维码
     * @param contents
     * @param width
     * @param height
     * @return
     */
    public Bitmap setCode(String contents, int width, int height){

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

    @OnClick({R.id.save,R.id.copy,R.id.back,R.id.my_share_tv})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.save:
                saveQrcodeToGallery();
                break;
            case R.id.copy:
                ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(null, copyText.getText().toString());
                clipboard.setPrimaryClip(clipData);
                ToastUtil.show(this,"复制成功，快去分享吧",Toast.LENGTH_SHORT);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, copyText.getText().toString());
                startActivity(Intent.createChooser(intent, copyText.getText().toString()));
                break;
            case R.id.my_share_tv:
                Intent intent1 = new Intent(this, MyshareActivity.class);
                startActivity(intent1);
                break;
        }
    }

    //生成页面并保存至图库
    private void saveQrcodeToGallery() {
        //创建视图
        View qrcodeView = getLayoutInflater().inflate(R.layout.share_code, null, false);
        ((ImageView)qrcodeView.findViewById(R.id.ewCode)).setImageDrawable(code.getDrawable());
        //计算视图大小
        DisplayMetrics displayMetrics = getWindowDisplayMetrics(this);
        final int width = displayMetrics.widthPixels;
        final int height = displayMetrics.heightPixels - getStatusBarHeight(this) - getActionBarHeight(this) - getResources().getDimensionPixelSize(R.dimen.dp_10);

        //将视图生成图片
        Bitmap image = generateImageFromView(qrcodeView, width, height);
        //将图片保存到系统相册
        boolean isSuccess = saveImageToGallery(this, image);
        if (isSuccess) {
            ToastUtil.show(this, "已保存到系统相册！", Toast.LENGTH_SHORT);
        } else {
            ToastUtil.show(this, "保存到系统相册失败！", Toast.LENGTH_SHORT);
        }


        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), image, null, null));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, AppUtil.getAppName(this)));
        image.recycle();
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
}
