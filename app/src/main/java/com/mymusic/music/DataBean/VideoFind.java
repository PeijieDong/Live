package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/11 20:01
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoFind {

    /**
     * data : {"list":[{"id":"20","uid":"100024","cate":"9","type":"视频","content":"","share":"0","comment":"0","zan":"0","createtime":"1564569504","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/data/upload/video/156456950483.jpg","click":"4","isgonggao":"0","title":"还是不对不对","tag":"","images":[],"playtime":"00:00:11","videourl":"http://live.shuiqiao.net/data/upload/video/5d416fa017958.mp4","collect":"0","isguanzhu":"关注","catename":"测试分类33","username":"手机用户4797","avatar":"http://live.shuiqiao.net/default.jpg"},{"id":"13","uid":"100023","cate":"8","type":"视频","content":"","share":"0","comment":"0","zan":"7","createtime":"1564066683","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/data/upload/video/156406668333.jpg","click":"38","isgonggao":"0","title":"视频","tag":"","images":[],"playtime":"00:01:05","videourl":"http://live.shuiqiao.net/data/upload/video/5d39c37be80a3.mp4","collect":"2","isguanzhu":"关注","catename":"测试分类33","username":"手机用户2511","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"5","uid":"100020","cate":"6","type":"视频","content":"","share":"0","comment":"0","zan":"0","createtime":"1563903948","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/data/upload/video/156390394814.jpg","click":"13","isgonggao":"0","title":"","tag":"","images":[],"playtime":"00:01:05","videourl":"http://live.shuiqiao.net/data/upload/video/5d3747ccd6888.mp4","collect":"0","isguanzhu":"关注","catename":"测试分类33","username":"手机用户2536","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"4","uid":"100020","cate":"5","type":"视频","content":"","share":"0","comment":"0","zan":"1","createtime":"1563901953","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/data/upload/video/156390195328.jpg","click":"11","isgonggao":"0","title":"","tag":"","images":[],"playtime":"00:01:05","videourl":"http://live.shuiqiao.net/data/upload/video/5d3740011cafb.mp4","collect":"0","isguanzhu":"关注","catename":"测试分类33","username":"手机用户2536","avatar":"http://live.shuiqiao.net/default_thumb.jpg"}],"total":"4"}
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
        /**
         * list : [{"id":"20","uid":"100024","cate":"9","type":"视频","content":"","share":"0","comment":"0","zan":"0","createtime":"1564569504","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/data/upload/video/156456950483.jpg","click":"4","isgonggao":"0","title":"还是不对不对","tag":"","images":[],"playtime":"00:00:11","videourl":"http://live.shuiqiao.net/data/upload/video/5d416fa017958.mp4","collect":"0","isguanzhu":"关注","catename":"测试分类33","username":"手机用户4797","avatar":"http://live.shuiqiao.net/default.jpg"},{"id":"13","uid":"100023","cate":"8","type":"视频","content":"","share":"0","comment":"0","zan":"7","createtime":"1564066683","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/data/upload/video/156406668333.jpg","click":"38","isgonggao":"0","title":"视频","tag":"","images":[],"playtime":"00:01:05","videourl":"http://live.shuiqiao.net/data/upload/video/5d39c37be80a3.mp4","collect":"2","isguanzhu":"关注","catename":"测试分类33","username":"手机用户2511","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"5","uid":"100020","cate":"6","type":"视频","content":"","share":"0","comment":"0","zan":"0","createtime":"1563903948","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/data/upload/video/156390394814.jpg","click":"13","isgonggao":"0","title":"","tag":"","images":[],"playtime":"00:01:05","videourl":"http://live.shuiqiao.net/data/upload/video/5d3747ccd6888.mp4","collect":"0","isguanzhu":"关注","catename":"测试分类33","username":"手机用户2536","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"4","uid":"100020","cate":"5","type":"视频","content":"","share":"0","comment":"0","zan":"1","createtime":"1563901953","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/data/upload/video/156390195328.jpg","click":"11","isgonggao":"0","title":"","tag":"","images":[],"playtime":"00:01:05","videourl":"http://live.shuiqiao.net/data/upload/video/5d3740011cafb.mp4","collect":"0","isguanzhu":"关注","catename":"测试分类33","username":"手机用户2536","avatar":"http://live.shuiqiao.net/default_thumb.jpg"}]
         * total : 4
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
             * id : 20
             * uid : 100024
             * cate : 9
             * type : 视频
             * content :
             * share : 0
             * comment : 0
             * zan : 0
             * createtime : 1564569504
             * status : 1
             * zhiding : 0
             * tui : 0
             * image : http://live.shuiqiao.net/data/upload/video/156456950483.jpg
             * click : 4
             * isgonggao : 0
             * title : 还是不对不对
             * tag :
             * images : []
             * playtime : 00:00:11
             * videourl : http://live.shuiqiao.net/data/upload/video/5d416fa017958.mp4
             * collect : 0
             * isguanzhu : 关注
             * catename : 测试分类33
             * username : 手机用户4797
             * avatar : http://live.shuiqiao.net/default.jpg
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
            private String playtime;
            private String videourl;
            private String collect;
            private String isguanzhu;
            private String catename;
            private String username;
            private String avatar;
            private List<?> images;

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

            public String getPlaytime() {
                return playtime;
            }

            public void setPlaytime(String playtime) {
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

            public String getIsguanzhu() {
                return isguanzhu;
            }

            public void setIsguanzhu(String isguanzhu) {
                this.isguanzhu = isguanzhu;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }
    }
}
