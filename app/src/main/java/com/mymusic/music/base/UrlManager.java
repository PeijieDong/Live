package com.mymusic.music.base;

/**
 * Create By mr.mao in 2019/6/3 20:28
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UrlManager {

    private static final String BaseUrl = "http://live.shuiqiao.net/";
    //home页
    public static final String HOME_DATA = BaseUrl+"index/homepage";
    //home详情
    public static final String HOME_DETAILS = BaseUrl+"index/info";
    //圈子发现
    public static final String FRIEND_FIND = BaseUrl+"index/recovery";
    //圈子全部
    public static final String FRIEND_ALL = BaseUrl+"index/all";
    //圈子详情
    public static final String FRIEND_DETAILS = BaseUrl+"index/qinfo";

    public static final String FRIEND_DETAILS_LIST = BaseUrl+"index/qinfolist";
    //详情页评论获取
    public static final String Detail_Comment = BaseUrl+"index/getComment";
    public static final String Detail_Comment_Put = BaseUrl+"index/putComment";
    //详情页评论获取
    public static final String Focus_List = BaseUrl+"users/qzfollow";
    //视频列表
    public static final String Video_List = BaseUrl+"video/index";
    //活跃用户列表
    public static final String User_List = BaseUrl+"index/userlist";

    public static final String Like = BaseUrl+"index/zan";

    public static final String Login = BaseUrl+"index/login";

    public static final String UserInfo = BaseUrl+"index/getLoginUserInfo";

    public static final String Register =BaseUrl+"index/register" ;

    public static final String Forget = BaseUrl+"index/findPass" ;

    public static final String GetTag =  BaseUrl+"video/gettag";

    public static final String GetFans = BaseUrl+"users/fans";

    public static final String History = BaseUrl+"users/gethistory";

    public static final String MyLike = BaseUrl+"users/getzan";

    public static final String My_Comment = BaseUrl+"users/getComment";

    public static final String Post_Video = BaseUrl+"video/addArticle";

    public static final String Post_Art = BaseUrl+"video/addArticle";

    public static final String Get_Wallet = BaseUrl+"users/wallet";

    public static final String Money_Detail = BaseUrl+"users/moneylog";

    public static final String Collection = BaseUrl+"users/fav";

    public static final String Put_Video = BaseUrl+"video/addmVideo";

    public static final String Friend_Focus = BaseUrl+"index/qz_attention";

    public static final String Collection_Home = BaseUrl+"index/collect";

    public static final String PingBI = BaseUrl+"index/pingbi";

    public static final String JuBao = BaseUrl+"video/newsreport";

    public static final String Video_Zan = BaseUrl+"video/zan";

    public static final String Vide_Collection = BaseUrl+"video/collect";

    public static final String GetUserInfo = BaseUrl+"users/getLoginUserInfo";

    public static final String Video_Comment = BaseUrl+"index/getComment";

    public static final String Video_CommentLike = BaseUrl+"index/pzan";

    public static final String Jubao_Video = BaseUrl+"video/videoreport";

    public static final String User_Detail = BaseUrl+"index/getUserinfo";

    public static final String User_Activity = BaseUrl+"index/getUsernews";

    public static final String User_Video = BaseUrl+"index/getvideo";

    public static final String User_Info = BaseUrl+"index/getUserinfo";

    public static final String Focus_Person =BaseUrl+"users/follow" ;

    public static final String Focus_User = BaseUrl+"index/follow_add";

    public static final String NoFocus_User =BaseUrl+"index/follow_cancel" ;

    public static final String Focus_Friend =BaseUrl + "index/qz_attention";

    public static final String NoFocus_Friend =BaseUrl + "index/qz_attention_qx";

    public static final String Change_Info =BaseUrl + "users/upUserinfo";

    public static final String Comment_Video =BaseUrl + "video/putComment";

    public static final String GetComment_Video =BaseUrl + "video/getComment";

    public static final String Clear_History =BaseUrl + "users/cleanhistory";

    public static final String Feedback_wallet =BaseUrl + "users/feedback";

    public static final String Friend_User =BaseUrl + "index/userlist";

    public static final String Focus_Cencel = BaseUrl+"index/follow_cancel";

    public static final String Comment_List = BaseUrl+"index/plinfo";

    public static final String Exchange = BaseUrl+"users/duihuan";

    public static final String TopUp = BaseUrl+"users/wallet";

    public static final String Publish = BaseUrl+"users/mypublish";

    public static final String Friend_Find = BaseUrl+"index/qzsearch";

    public static final String Home_Find = BaseUrl+"index/search";

    public static final String Delete = BaseUrl+"users/delcomment";

    public static final String CommentLike = BaseUrl+"index/pzan";

    public static final String Money = BaseUrl+"users/toorder";

    public static final String Task = BaseUrl+"users/task";

    public static final String Level = BaseUrl+"users/levels";
    //赚取金币
    public static final String Get_Score = BaseUrl+ "users/scorelist";

    public static final String GetExp = BaseUrl+ "users/jingyanlist";

    public static final String FeedBack = BaseUrl+"users/guestbook";

    public static final String History_FeedBack = BaseUrl+"users/gblist";

    public static final String Play_Num = BaseUrl+"video/LimitWatchNum";

    public static final String GoMoney = BaseUrl+"users/scoretovideo";

    public static final String ADV =BaseUrl+"ad/startad";

    public static final String AddVideoNum = BaseUrl +"video/addvisit";

    public static final String Get_Message = BaseUrl +"users/noticeset";

    public static final String Notice_Mes = BaseUrl +"users/notice";

    public static final String GET_CODE = BaseUrl+"index/sendSms";

    public static final String CHECK_CODE = BaseUrl+"";

    public static final String ShareFriend = BaseUrl+"index/website";

    public static final String MY_SHARE = BaseUrl + "users/myinvite";

    public static final String CHECK_PHONE = BaseUrl + "users/bindphontstep1";

    public static final String CHANGE_PHONE =BaseUrl+ "/users/bindphonestep2";

    public static final String BLACK_LIST = BaseUrl + "users/blacklist";

    public static final String JIECHU = BaseUrl+"users/userblack";

    public static final String APP_UPDATE = BaseUrl + "index/checkupdate";

    public static final String GET_PINDAO =BaseUrl+ "video/cate";

    public static final String GET_PINDAO_DETAIL = BaseUrl+"video/jingxuan";

    public static final String GET_TOP = BaseUrl + "video/topcate";

    public static final String GET_FIND = BaseUrl + "video/search";

    public static final String GET_TITLE = BaseUrl+"video/videolist";

    public static final String GET_VIDEO = BaseUrl+"video/vlist";

    public static final String HOME_DETAILS_TWO = BaseUrl + "video/vinfo";

    public static final String GET_FIND_HOT = BaseUrl+"video/hotsearch";

    public static final String YAOQING = BaseUrl +"users/invite";

    public static final String TOPUP_VIP = BaseUrl+"users/vip";

    public static final String HOME_URL = BaseUrl+"index/thirddown";

    public static final String HEAD = BaseUrl + "users/upavatar";

    public static final String ABOUT = BaseUrl + "index/customerService";

    public static final String SIGNIN = BaseUrl + "users/record";

    public static final String VIP_LIST = BaseUrl+"home/user/scorelist";

    public static final String VIP_TIME =BaseUrl + "home/user/scoretotime";
}
