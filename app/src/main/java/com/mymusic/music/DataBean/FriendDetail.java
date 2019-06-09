package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/9 21:58
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendDetail {

    /**
     * data : {"list":[{"id":"17","uid":"1","cate":"2","type":"5","content":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0"},{"id":"14","uid":"1","cate":"2","type":"5","content":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0"},{"id":"5","uid":"1","cate":"2","type":"5","content":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0"}]}
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
             * id : 17
             * uid : 1
             * cate : 2
             * type : 5
             * content : http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4
             * share : 0
             * comment : 0
             * zan : 0
             * createtime : 0
             * status : 1
             * zhiding : 0
             * tui : 0
             * image : http://live.shuiqiao.net/default.jpg
             * click : 0
             */

            private String id;
            private String uid;
            private String cate;
            private String type;
            private String content;
            private String share;
            private String comment;
            private String zan;
            private String createtime;
            private String status;
            private String zhiding;
            private String tui;
            private String image;
            private String click;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getCate() {
                return cate;
            }

            public void setCate(String cate) {
                this.cate = cate;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getShare() {
                return share;
            }

            public void setShare(String share) {
                this.share = share;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getZan() {
                return zan;
            }

            public void setZan(String zan) {
                this.zan = zan;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getZhiding() {
                return zhiding;
            }

            public void setZhiding(String zhiding) {
                this.zhiding = zhiding;
            }

            public String getTui() {
                return tui;
            }

            public void setTui(String tui) {
                this.tui = tui;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getClick() {
                return click;
            }

            public void setClick(String click) {
                this.click = click;
            }
        }
    }
}
