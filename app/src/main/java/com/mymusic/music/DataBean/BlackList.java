package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/28 2:12
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class BlackList {


    /**
     * list : [{"id":"100021","avatar_thumb":"/default_thumb.jpg","user_nicename":"手机用户2531","signature":"这家伙很懒，什么都没留下","avatar":"http://live.shuiqiao.net/default_thumb.jpg"}]
     * status : 1
     * referer :
     * state : 1
     */

    private int status;
    private String referer;
    private String state;
    private List<ListBean> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 100021
         * avatar_thumb : /default_thumb.jpg
         * user_nicename : 手机用户2531
         * signature : 这家伙很懒，什么都没留下
         * avatar : http://live.shuiqiao.net/default_thumb.jpg
         */

        private String id;
        private String avatar_thumb;
        private String user_nicename;
        private String signature;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar_thumb() {
            return avatar_thumb;
        }

        public void setAvatar_thumb(String avatar_thumb) {
            this.avatar_thumb = avatar_thumb;
        }

        public String getUser_nicename() {
            return user_nicename;
        }

        public void setUser_nicename(String user_nicename) {
            this.user_nicename = user_nicename;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
