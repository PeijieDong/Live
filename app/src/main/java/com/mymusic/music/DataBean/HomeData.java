package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/5 20:24
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomeData {


    /**
     * data : {"list":[{"id":"15","uid":"1","cate":"2","type":"图片","content":"这里是文字","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"","tag":"","images":["http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg","http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg","http:"],"playtime":null,"videourl":"http://live.shuiqiao.net","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"14","uid":"1","cate":"2","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"13","uid":"1","cate":"2","type":"文字","content":"dsfdsaf","share":"1","comment":"2","zan":"3","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net","click":"0","isgonggao":"0","title":"","tag":"","images":null,"playtime":null,"videourl":"http://live.shuiqiao.net","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"9","uid":"1","cate":"1","type":"图片","content":"这里是文字","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"","tag":"","images":["http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg","http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg","http:"],"playtime":null,"videourl":"http://live.shuiqiao.net","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"8","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"7","uid":"1","cate":"1","type":"文字","content":"dsfdsaf","share":"1","comment":"2","zan":"3","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net","click":"0","isgonggao":"0","title":"","tag":"","images":null,"playtime":null,"videourl":"http://live.shuiqiao.net","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"3","uid":"1","cate":"1","type":"图片","content":"这里是文字","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"","tag":"","images":["http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg","http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg","http:"],"playtime":null,"videourl":"http://live.shuiqiao.net","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"},{"id":"2","uid":"1","cate":"1","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"1","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","catename":"交友1s","username":"admin","avatar":"http://live.shuiqiao.net/default_thumb.jpg"}]}
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
             * id : 15
             * uid : 1
             * cate : 2
             * type : 图片
             * content : 这里是文字
             * share : 0
             * comment : 0
             * zan : 0
             * createtime : 0
             * status : 1
             * zhiding : 0
             * tui : 1
             * image : http://live.shuiqiao.net/default.jpg
             * click : 0
             * isgonggao : 0
             * title :
             * tag :
             * images : ["http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg","http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg","http:"]
             * playtime : null
             * videourl : http://live.shuiqiao.net
             * catename : 交友1s
             * username : admin
             * avatar : http://live.shuiqiao.net/default_thumb.jpg
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
            private String catename;
            private String username;
            private String avatar;
            private String avatar_thumb;
            private String signature;
            private String sex;
            private String user_nicename;
            private List<String> images;

            public String getUser_nicename() {
                return user_nicename;
            }

            public void setUser_nicename(String user_nicename) {
                this.user_nicename = user_nicename;
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

            public String getAvatar_thumb() {
                return avatar_thumb;
            }

            public void setAvatar_thumb(String avatar_thumb) {
                this.avatar_thumb = avatar_thumb;
            }

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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
