package com.mymusic.music.Util;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.ResponseEntity;
import com.mymusic.music.View.Activity.Login.LoginActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求类
 *
 */
public class HttpManager {
	private static HttpManager  httpManager;
	private static OkHttpClient okHttpClient;
	/**
	 * 设置密码专用
	 */
	private String token="access_token";
	/*public String getToken() {
		   return token;
		   }*/
	private Context mContext;
	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}
     /**
      * 更新内存token
      * @param token
      */
	public  void setToken(String token) {
//		/if(null==token)return;
		this.token = token;
	}
	/**
	 * 请求回调接口
	 */
//private OnRequestCallBack   onRequestCallBack=null;
	public static Gson gson;

	private static   final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

//	public void setOnRequestCallBack(OnRequestCallBack onRequestCallBack) {
//		this.onRequestCallBack = onRequestCallBack;
//	}
	/**
	 * 网络请求类
	 *
	 * 请求回调接口
	 */
	//此类可以被new，但是无法避免反射
	private HttpManager() {
	}
    public static HttpManager getInstance(){
        if (httpManager == null)
        {
            synchronized (HttpManager.class)
            {
               httpManager = new HttpManager();
               okHttpClient=new OkHttpClient.Builder()
               .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
               .writeTimeout(120, TimeUnit.SECONDS)//设置写的超时时间
               .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
               .build();  //设置各种超时时间;
               gson=new Gson();

            }
        }
        return httpManager;
    }
    public static final String GET="GET";
    public static final String POST="POST";
    public static final String PUT="PUT";
    public static final String DELETE="DELETE";

    private int rId=-100;
    /**
	 * okhttp框架请求网络
	 *
	 * @param isCancleOldRequest  是否取消同reqId请求  true是 取消
	 * @param reqId
	 *                            请求id用于判断哪次请求,必填
	 * @param url
	 *                            请求的url地址,必填
	 * @param str_Or_jsonStr
	 *        GET                 传"strA=a & strB=b" get请求拼参规则  没有参数就传  null,
	 *        POST PUT DELETE     请求参数json          没有参数就传     null
	 *
	 * @param requestMethod
	 *         必填
	 *         GET  POST PUT DELETE
	 *
	 */
	public  <T> void callRequest(final boolean isCancleOldRequest , final int reqId, final String url, final String str_Or_jsonStr, String requestMethod, final Class<T> clazz, final Handler handler) {
		if(isCancleOldRequest){
			cancleRequestCall(reqId);
		}
		rId=reqId;
		//okHttpClient.dispatcher().
		RequestBody body=null;
		String url_get=null;
		if(GET.equals(requestMethod)){
	        url_get =str_Or_jsonStr==null? url: String.format("%s?%s",url,str_Or_jsonStr);
		}else{
			body = RequestBody.create(JSON,str_Or_jsonStr==null?"":str_Or_jsonStr);
		}
		Request request = new Request.Builder()
				.url(url)
				.method(requestMethod, GET.equals(requestMethod)?null:body)
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response result) throws IOException {
				if(null!=handler){
					 //call.request().tag();
					//System.out.println(onRequestCallBack.getClass().getName());
					  String resultStr = result.body().string();
					  System.out.println("reqId："+reqId);
				      System.out.println("返回参数："+resultStr);
					  try {
						  if(resultStr!=null&&!"".equals(resultStr)){
						    Message message=new Message();
						    message.arg1=RequestFactory.REQUEST_OK;
						    message.arg2=reqId;
						    message.obj=gson.fromJson(resultStr,clazz);
						    handler.sendMessage(message);

							String code = new JSONObject(resultStr).optString("code");
							if(code.equals("-997")){
								//token过期
								Intent intent = new Intent(mContext, LoginActivity.class);
								mContext.startActivity(intent);
							}
						    }else{
						    	onFailure(call, null);
						    }
//						}else{
//						}
					  } catch (Exception e) {
						  //json解析错误
						  System.out.println("onResponsejson解析错误-------");
						  onFailure(call, null);
						  e.printStackTrace();
					  }
				}
			}
			@Override
			public void onFailure(Call call, IOException ioException) {
				try {
				if(null!=handler){
					if(call.isCanceled())return;
					Thread.sleep(500);
					Message message=new Message();
				    message.arg1=RequestFactory.REQUEST_FAIL;
				    message.arg2=reqId;
				    handler.sendMessage(message);
				}

				 } catch (Exception e) {
					  //json解析错误
					  //e.printStackTrace();
				  }
			}
		});
	}


	/**
	 * 取消请求
	 * @param tag
	 */
	public void cancleRequestCall(Object tag){
	    Dispatcher dispatcher = okHttpClient.dispatcher();
	    synchronized (dispatcher){
	        for (Call call : dispatcher.queuedCalls()) {
	            if (tag.equals(call.request().tag())) {
	                call.cancel();
	                System.out.println("取消了队列操作");
	            }
	        }
	        for (Call call : dispatcher.runningCalls()) {
	            if (tag.equals(call.request().tag())) {
	                call.cancel();
	                System.out.println("取消了正在执行操作");
	            }
	        }
	    }
	}



	public boolean isSuccessFul(ResponseEntity entity){
		if(null==entity)return false;
		return "1".equals(entity.status);
	}

}