package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/8 16:13
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class DetailData {

    /**
     * data : {"list":{"id":"15","uid":"1","cate":"2","type":"图片","content":"这里是文字","share":"0","comment":"3","zan":"8","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"6","isgonggao":"0","title":"这里是标题","tag":"","images":[],"playtime":null,"videourl":"http://live.shuiqiao.net","collect":"0","username":"admin","catename":"测试推荐","avatar":"http://live.shuiqiao.net/default_thumb.jpg","sex":"0"}}
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
        /**
         * list : {"id":"15","uid":"1","cate":"2","type":"图片","content":"这里是文字","share":"0","comment":"3","zan":"8","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"6","isgonggao":"0","title":"这里是标题","tag":"","images":[],"playtime":null,"videourl":"http://live.shuiqiao.net","collect":"0","username":"admin","catename":"测试推荐","avatar":"http://live.shuiqiao.net/default_thumb.jpg","sex":"0"}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 15
             * uid : 1
             * cate : 2
             * type : 图片
             * content : 这里是文字
             * share : 0
             * comment : 3
             * zan : 8
             * createtime : 0
             * status : 1
             * zhiding : 0
             * tui : 1
             * image : http://live.shuiqiao.net/default.jpg
             * click : 6
             * isgonggao : 0
             * title : 这里是标题
             * tag :
             * images : []
             * playtime : null
             * videourl : http://live.shuiqiao.net
             * collect : 0
             * username : admin
             * catename : 测试推荐
             * avatar : http://live.shuiqiao.net/default_thumb.jpg
             * sex : 0
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
            private String isgonggao;
            private String title;
            private String tag;
            private Object playtime;
            private String videourl;
            private String collect;
            private String username;
            private String catename;
            private String avatar;
            private String sex;
            private List<String> images;

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

            public String getIsgonggao() {
                return isgonggao;
            }

            public void setIsgonggao(String isgonggao) {
                this.isgonggao = isgonggao;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public Object getPlaytime() {
                return playtime;
            }

            public void setPlaytime(Object playtime) {
                this.playtime = playtime;
            }

            public String getVideourl() {
                return videourl;
            }

            public void setVideourl(String videourl) {
                this.videourl = videourl;
            }

            public String getCollect() {
                return collect;
            }

            public void setCollect(String collect) {
                this.collect = collect;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
