package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/30 1:57
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FocusPerson {

    /**
     * data : {"list":[{"uid":"9999","touid":"8201","avatar":null,"sex":null,"signature":null,"user_nicename":null,"id":"8201","guanzhu":"已关注"},{"uid":"9999","touid":"8201","avatar":null,"sex":null,"signature":null,"user_nicename":null,"id":"8201","guanzhu":"已关注"},{"uid":"9999","touid":"8208","avatar":null,"sex":null,"signature":null,"user_nicename":null,"id":"8208","guanzhu":"已关注"},{"uid":"9999","touid":"49829","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户0141","id":"49829","guanzhu":"已关注"},{"uid":"9999","touid":"49834","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户0149","id":"49834","guanzhu":"已关注"},{"uid":"9999","touid":"49836","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户8062","id":"49836","guanzhu":"已关注"},{"uid":"9999","touid":"49838","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户6834","id":"49838","guanzhu":"已关注"},{"uid":"9999","touid":"49841","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户0159","id":"49841","guanzhu":"已关注"},{"uid":"9999","touid":"49846","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户6931","id":"49846","guanzhu":"已关注"}],"total":"9"}
     * referer :
     * state : 0
     */

    private DataBean data;
    private String referer;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
        /**
         * list : [{"uid":"9999","touid":"8201","avatar":null,"sex":null,"signature":null,"user_nicename":null,"id":"8201","guanzhu":"已关注"},{"uid":"9999","touid":"8201","avatar":null,"sex":null,"signature":null,"user_nicename":null,"id":"8201","guanzhu":"已关注"},{"uid":"9999","touid":"8208","avatar":null,"sex":null,"signature":null,"user_nicename":null,"id":"8208","guanzhu":"已关注"},{"uid":"9999","touid":"49829","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户0141","id":"49829","guanzhu":"已关注"},{"uid":"9999","touid":"49834","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户0149","id":"49834","guanzhu":"已关注"},{"uid":"9999","touid":"49836","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户8062","id":"49836","guanzhu":"已关注"},{"uid":"9999","touid":"49838","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户6834","id":"49838","guanzhu":"已关注"},{"uid":"9999","touid":"49841","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户0159","id":"49841","guanzhu":"已关注"},{"uid":"9999","touid":"49846","avatar":"http://live.shuiqiao.net/default.jpg","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户6931","id":"49846","guanzhu":"已关注"}]
         * total : 9
         */

        private String total;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * uid : 9999
             * touid : 8201
             * avatar : null
             * sex : null
             * signature : null
             * user_nicename : null
             * id : 8201
             * guanzhu : 已关注
             */

            private String uid;
            private String touid;
            private String avatar;
            private String sex;
            private String signature;
            private String user_nicename;
            private String id;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getUser_nicename() {
                return user_nicename;
            }

            public void setUser_nicename(String user_nicename) {
                this.user_nicename = user_nicename;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
