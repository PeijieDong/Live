package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/8 9:51
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendAllData {

    /**
     * data : {"list":[{"cid":"2","title":"测试推荐","icon":"http://live.shuiqiao.net/default.jpg","description":"这里是描述","tui":"1","name":"美图","official":"0","list":[{"id":"18","uid":"1","cate":"2","type":"图片","content":"这里是文字","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":["这里是文字"],"playtime":null,"videourl":""},{"id":"17","uid":"1","cate":"2","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4"},{"id":"16","uid":"1","cate":"2","type":"文字","content":"dsfdsaf","share":"1","comment":"2","zan":"3","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":""}],"isguanzhu":"关注","gnum":"2","tiezi":"9"}]}
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
        private List<ListBeanX> list;

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * cid : 2
             * title : 测试推荐
             * icon : http://live.shuiqiao.net/default.jpg
             * description : 这里是描述
             * tui : 1
             * name : 美图
             * official : 0
             * list : [{"id":"18","uid":"1","cate":"2","type":"图片","content":"这里是文字","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":["这里是文字"],"playtime":null,"videourl":""},{"id":"17","uid":"1","cate":"2","type":"视频","content":"这里是内容","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4"},{"id":"16","uid":"1","cate":"2","type":"文字","content":"dsfdsaf","share":"1","comment":"2","zan":"3","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net","click":"0","isgonggao":"0","title":"这里是标题","tag":"","images":null,"playtime":null,"videourl":""}]
             * isguanzhu : 关注
             * gnum : 2
             * tiezi : 9
             */

            private String cid;
            private String title;
            private String icon;
            private String description;
            private String tui;
            private String name;
            private String official;
            private String isguanzhu;
            private String gnum;
            private String tiezi;
            private List<ListBean> list;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTui() {
                return tui;
            }

            public void setTui(String tui) {
                this.tui = tui;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOfficial() {
                return official;
            }

            public void setOfficial(String official) {
                this.official = official;
            }

            public String getIsguanzhu() {
                return isguanzhu;
            }

            public void setIsguanzhu(String isguanzhu) {
                this.isguanzhu = isguanzhu;
            }

            public String getGnum() {
                return gnum;
            }

            public void setGnum(String gnum) {
                this.gnum = gnum;
            }

            public String getTiezi() {
                return tiezi;
            }

            public void setTiezi(String tiezi) {
                this.tiezi = tiezi;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 18
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
                 * tui : 0
                 * image : http://live.shuiqiao.net/default.jpg
                 * click : 0
                 * isgonggao : 0
                 * title : 这里是标题
                 * tag :
                 * images : ["这里是文字"]
                 * playtime : null
                 * videourl :
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

                public List<String> getImages() {
                    return images;
                }

                public void setImages(List<String> images) {
                    this.images = images;
                }
            }
        }
    }
}
