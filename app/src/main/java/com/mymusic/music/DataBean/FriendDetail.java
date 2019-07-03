package com.mymusic.music.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Create By mr.mao in 2019/6/9 21:58
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendDetail implements Serializable {

    /**
     * data : {"list":[{"id":"24","uid":"9999","cate":"1","type":"视频","content":"sdfdsafsafasdf","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"1","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"14","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"22","uid":"9999","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561335406","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"2","isgonggao":"0","title":"这里是标题","tag":"agt","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"21","uid":"9999","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561335217","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"1","isgonggao":"0","title":"这里是标题","tag":"agt","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"20","uid":"9999","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561305159","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"1","isgonggao":"0","title":"这里是标题","tag":"agt","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"19","uid":"100000","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561277403","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"1，1","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"手机用户2610","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"11","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"8","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"2","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"}],"total":"8"}
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

    public static class DataBean implements Serializable{
        /**
         * list : [{"id":"24","uid":"9999","cate":"1","type":"视频","content":"sdfdsafsafasdf","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"1","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"14","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"22","uid":"9999","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561335406","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"2","isgonggao":"0","title":"这里是标题","tag":"agt","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"21","uid":"9999","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561335217","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"1","isgonggao":"0","title":"这里是标题","tag":"agt","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"20","uid":"9999","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561305159","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"1","isgonggao":"0","title":"这里是标题","tag":"agt","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"不独特TOTO","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"19","uid":"100000","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"1561277403","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"1，1","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"手机用户2610","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"11","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"8","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"},{"id":"2","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","collect":"0","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg","catename":"交友1s"}]
         * total : 8
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

        public static class ListBean implements Serializable{
            /**
             * id : 24
             * uid : 9999
             * cate : 1
             * type : 视频
             * content : sdfdsafsafasdf
             * share : 0
             * comment : 0
             * zan : 0
             * createtime : 0
             * status : 1
             * zhiding : 1
             * tui : 0
             * image : http://live.shuiqiao.net/default.jpg
             * click : 14
             * isgonggao : 0
             * title : 这里是标题
             * tag :
             * images : null
             * playtime : null
             * videourl : http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4
             * collect : 0
             * username : 不独特TOTO
             * avatar : http://live.shuiqiao.net/default_thumb.jpg
             * catename : 交友1s
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
            private Object images;
            private Object playtime;
            private String videourl;
            private String collect;
            private String username;
            private String avatar;
            private String catename;

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

            public Object getImages() {
                return images;
            }

            public void setImages(Object images) {
                this.images = images;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }
        }
    }
}
