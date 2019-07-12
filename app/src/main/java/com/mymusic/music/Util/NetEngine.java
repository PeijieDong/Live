package com.mymusic.music.Util;

/**
 * Created by 阿里聚合 on 2018/09/01.
 */

public class NetEngine {
	
	private static boolean servicerFlg=true;
	/**
	 * servicerFlg ==true 正式服务器 反之测试服务器
	 * @return string
	 */
	public static String getServicerUrl() {
		return servicerFlg?servicerUrl:servicerTestUrl;
	}

	public final static String servicerTestUrl = "http://live.shuiqiao.net/"; //授权域名
	public final static String servicerUrl = "http://live.shuiqiao.net/"; //通信域名




		private NetEngine() {
	    }
	   private static NetEngine netEngine;
	   public static NetEngine getInstance() {
	        if (netEngine == null) {
	            synchronized (NetEngine.class) {
	                if (netEngine == null) {
	                	netEngine = new NetEngine();
	                }
	            }
	       }
	     return netEngine;
	   }
	    
	//公用
	private PublicEngine     publicEngine;
    public PublicEngine getPublicEngine() {
        if (publicEngine == null) {
        	publicEngine = new PublicEngine();
        }
        return publicEngine;
    }
    

}
