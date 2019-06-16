package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/16 20:20
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoData {

    /**
     * data : {"list":[{"vid":"1","uid":"20187","content":"ewrwer","zan":"1","comment":"2","share":"3","status":"1","createtime":"0","filepath":"http://www.shuiqiao.net/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","tag":"","id":"20187","avatar_thumb":"http://live.shuiqiao.net/default_thumb.jpg","user_nicename":"手机用户9867","guanzhu":0,"sharecontent":"分享内容"}],"status":1}
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
         * list : [{"vid":"1","uid":"20187","content":"ewrwer","zan":"1","comment":"2","share":"3","status":"1","createtime":"0","filepath":"http://www.shuiqiao.net/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","tag":"","id":"20187","avatar_thumb":"http://live.shuiqiao.net/default_thumb.jpg","user_nicename":"手机用户9867","guanzhu":0,"sharecontent":"分享内容"}]
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
             * vid : 1
             * uid : 20187
             * content : ewrwer
             * zan : 1
             * comment : 2
             * share : 3
             * status : 1
             * createtime : 0
             * filepath : http://www.shuiqiao.net/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4
             * tag :
             * id : 20187
             * avatar_thumb : http://live.shuiqiao.net/default_thumb.jpg
             * user_nicename : 手机用户9867
             * guanzhu : 0
             * sharecontent : 分享内容
             */

            private String vid;
            private String uid;
            private String content;
            private String zan;
            private String comment;
            private String share;
            private String status;
            private String createtime;
            private String filepath;
            private String tag;
            private String id;
            private String avatar_thumb;
            private String user_nicename;
            private int guanzhu;
            private String sharecontent;

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

            public String getZan() {
                return zan;
            }

            public void setZan(String zan) {
                this.zan = zan;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getShare() {
                return share;
            }

            public void setShare(String share) {
                this.share = share;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

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

            public int getGuanzhu() {
                return guanzhu;
            }

            public void setGuanzhu(int guanzhu) {
                this.guanzhu = guanzhu;
            }

            public String getSharecontent() {
                return sharecontent;
            }

            public void setSharecontent(String sharecontent) {
                this.sharecontent = sharecontent;
            }
        }
    }
}
