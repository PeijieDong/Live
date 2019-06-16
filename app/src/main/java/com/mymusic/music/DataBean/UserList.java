package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/16 21:34
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserList {

    /**
     * data : {"list":[{"uid":"19847","touid":"2","user_nick":"手机用户6590","avatar":"http://live.shuiqiao.net/default_thumb.jpg","signature":"这家伙很懒，什么都没留下","guanzhu":"关注"},{"uid":"19848","touid":"2","user_nick":"手机用户5706","avatar":"http://live.shuiqiao.net/default_thumb.jpg","signature":"这家伙很懒，什么都没留下","guanzhu":"关注"}]}
     * status : 1
     * referer :
     * state : 1
     */

    private DataBean data;
    private int status;
    private String referer;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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
             * uid : 19847
             * touid : 2
             * user_nick : 手机用户6590
             * avatar : http://live.shuiqiao.net/default_thumb.jpg
             * signature : 这家伙很懒，什么都没留下
             * guanzhu : 关注
             */

            private String uid;
            private String touid;
            private String user_nick;
            private String avatar;
            private String signature;
            private String guanzhu;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getTouid() {
                return touid;
            }

            public void setTouid(String touid) {
                this.touid = touid;
            }

            public String getUser_nick() {
                return user_nick;
            }

            public void setUser_nick(String user_nick) {
                this.user_nick = user_nick;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getGuanzhu() {
                return guanzhu;
            }

            public void setGuanzhu(String guanzhu) {
                this.guanzhu = guanzhu;
            }
        }
    }
}
