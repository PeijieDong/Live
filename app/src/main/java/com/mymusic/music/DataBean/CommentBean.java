package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/23 0:46
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class CommentBean {

    /**
     * data : {"list":[{"cid":"1","vid":"1","uid":"20187","content":"sdafsdf","num":"0","createtime":"1970-01-01","pid":"0","son":[],"avatar":"http://live.shuiqiao.net/default.jpg","user_nicename":"手机用户9867"}],"status":1}
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
         * list : [{"cid":"1","vid":"1","uid":"20187","content":"sdafsdf","num":"0","createtime":"1970-01-01","pid":"0","son":[],"avatar":"http://live.shuiqiao.net/default.jpg","user_nicename":"手机用户9867"}]
         * status : 1
         */

        private int status;
        private List<ListBean> list;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cid : 1
             * vid : 1
             * uid : 20187
             * content : sdafsdf
             * num : 0
             * createtime : 1970-01-01
             * pid : 0
             * son : []
             * avatar : http://live.shuiqiao.net/default.jpg
             * user_nicename : 手机用户9867
             */

            private String cid;
            private String vid;
            private String uid;
            private String content;
            private String num;
            private String createtime;
            private String pid;
            private String avatar;
            private String user_nicename;
            private List<?> son;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

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

            public List<?> getSon() {
                return son;
            }

            public void setSon(List<?> son) {
                this.son = son;
            }
        }
    }
}
