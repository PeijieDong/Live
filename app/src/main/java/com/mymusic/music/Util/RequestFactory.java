package com.mymusic.music.Util;

/**
 * 
 */
public class RequestFactory {
	
	private RequestFactory() {
	}


	/**
	 * 校验版本是否需要更新
	 */
	public static  final int  VERSION_CHECK = 22;
	
	
	// 网络请求模块
	public static final int REQUEST_OK = 99;
	public static final int REQUEST_DATAFAIL = 98;
	public static final int REQUEST_FAIL = 97;
	

	// 公共模块
	/**
	 *  获取验证码
	 */
	public static final int PUBLIC_VERIFICATION_CODE_GET = 100;
	

	/**
	 *  校验验证码，找回密码处
	 */
	public static final int PUBLIC_VERIFICATION_CODE_CHECK = 105;

	/**
	 * 启动广告
	 */
	public static final int PUBLIC_ADV = 106;

	/**
	 * 启动广告下载成功
	 */
	public static final int PUBLIC_ADV_DOWNLOAD_SUCCESS = 107;

	/**
	 * 启动广告下载失败
	 */
	public static final int PUBLIC_ADV_DOWNLOAD_FAILED = 108;

	/**
	 * 注册是否需要验证码
	 */
	public static final int CODE_IS_NEED = 109;


	// 登录模块
	/**
	 * 用户登录账户手机号
	 */
	public static final int LOGIN_USER = 300;

	/**
	 *退出登录
	 */
	public static final int USER_ACCOUNT_LOGIN_OUT= 306;
	

	/**
	 * 重置密码  手机号找回密码
	 */
	public static final int RESET_PW = 307;
	
	
	/**
	 * 修改密码 密码
	 */
	public static final int UPDATE_PW = 308;

	/**
	 * 用户注册
	 */
	public static final int USER_REGISTER = 312;


	// 直播模块

	/**
	 * 直播banner list获取
	 */
	public static final int BANNER_LIST = 500;


	/**
	 * 直播直播平台list
	 */
	public static final int LIVE_P_LIST = 501;

	/**
	 * 直播房间list获取
	 */
	public static final int LIVE_ROOM_LIST = 502;

	/**
	 * 加入直播间
	 */
	public static final int LIVE_JOIN = 503;

	/**
	 * 直播广告获取
	 */
	public static final int AD_MSG = 504;

	    // 用户模块
		/**
		 * 用户信息查询
		 */
		public static final int USER_INFORMATION_QUERY = 700;
	/**
	 * 获取客服联系方式
	 */
	public static final int USER_CUSTOMER = 701;

	/**
	 * 邀请好友
	 */
	public static final int USER_FRIEND = 702;


	/**
	 * vip续费
	 */
	public static final int USER_VIP_ADD = 703;

	/**
	 * 分享
	 */
	public static final int USER_SHARE = 704;
	/**
	 * 分享成功加时间
	 */
	public static final int USER_SHAREOK = 705;
	/**
	 * 推广
	 */
	public static final int USER_PUBLICITY = 706;

    //云播模块
	/**
	 * 视频列表查询
	 */
	public static final int CLOUD_VIDEOES_QUERY = 801;

	/**
	 * 小说分类查询
	 */
	public static final int CLOUD_XS_LAB_QUERY = 803;

	/**
	 * 小说列表查询
	 */
	public final static  int CLOUD_XS_LIST_QUERY= 802;

	/**
	 * 小说详情查询
	 */
	public static final int CLOUD_XS_CONTENT_QUERY = 804;

	/**
	 * 图片分类查询
	 */
	public static final int CLOUD_TP_LAB_QUERY = 805;

	/**
	 * 图片列表查询
	 */
	public final static  int CLOUD_TP_LIST_QUERY= 806;

	/**
	 * 图片详情查询
	 */
	public static final int CLOUD_TP_CONTENT_QUERY = 807;



	/**
	 * 影视分类查询
	 */
	public static final int CLOUD_YS_LAB_QUERY = 815;

	/**
	 * 影视列表查询
	 */
	public final static  int CLOUD_YS_LIST_QUERY= 816;

	/**
	 * 影视详情查询
	 */
	public static final int CLOUD_YS_CONTENT_QUERY = 817;
	//影视模块

	/**
	 * 卫视直播url查询
	 */
	public static final int VIDEO_WSLIVE_QUERY = 901;
	/**
	 * vip影院url查询
	 */
	public static final int VIDEO_MOVIE_QUERY = 902;

	/**
	 * 我的合作查询
	 */
	public static final int MINE_JOINT_WORK = 909;

	/**
	 * 首页
	 */
	public static final int HOME_LIST = 999;

	/**
	 * 首页搜索
	 */
	public static final int SEARCH_LIST = 998;

	/**
	 * 影院广告
	 */
	public static final int VIDEO_ADV = 997;
}
