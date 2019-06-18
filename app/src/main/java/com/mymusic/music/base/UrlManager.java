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
    //详情页评论获取
    public static final String Detail_Comment = BaseUrl+"index/getComment";
    public static final String Detail_Comment_Put = BaseUrl+"index/putComment";
    //详情页评论获取
    public static final String Focus_List = BaseUrl+"index/userlist/id/2";
    //视频列表
    public static final String Video_List = BaseUrl+"video/index";
    //活跃用户列表
    public static final String User_List = BaseUrl+"index/userlist";

    public static final String Like = BaseUrl+"index/zan";

    public static final String Login = BaseUrl+"index/login";

    public static final String UserInfo = BaseUrl+"index/getLoginUserInfo";


}
