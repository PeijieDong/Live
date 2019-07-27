package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By MR.D
 * 2019/7/26
 * USE:
 **/
public class MyShare {

    /**
     * data : {"list":[{"avatar":"http://live.shuiqiao.net/default.jpg","user_nicename":"手机用户2511"},{"avatar":"http://live.shuiqiao.net/default.jpg","user_nicename":"手机用户6789"},{"avatar":"http://live.shuiqiao.net/default.jpg","user_nicename":"手机用户2531"},{"avatar":"http://live.shuiqiao.net/default.jpg","user_nicename":"滚滚滚"}]}
     * status : 1
     * referer :
     * state : 1
     */

    private DataBean data;
    private String status;
    private String referer;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * avatar : http://live.shuiqiao.net/default.jpg
             * user_nicename : 手机用户2511
             */

            private String avatar;
            private String user_nicename;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUser_nicename() {
                return user_nicename;
            }

            public void setUser_nicename(String user_nicename) {
                this.user_nicename = user_nicename;
            }
        }
    }
}
