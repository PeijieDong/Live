package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/7 23:39
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFindData {

    /**
     * data : {"list":[{"cid":"2","title":"测试推荐","icon":"http://live.shuiqiao.net/default.jpg","description":"这里是描述","tui":"1","name":"美图","list":[{"id":"6","uid":"1","cate":"2","type":"图片","content":"http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg,http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg,http://a.hiphotos.baidu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg,http://g.hiphotos.baidu.com/image/h%3D300/sign=b5e4c905865494ee982209191df4e0e1/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0"},{"id":"5","uid":"1","cate":"2","type":"视频","content":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0"},{"id":"4","uid":"1","cate":"2","type":"文字","content":"dsfdsaf","share":"1","comment":"2","zan":"3","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net","click":"0"}],"isguanzhu":0,"ulist":[]}]}
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
             * list : [{"id":"6","uid":"1","cate":"2","type":"图片","content":"http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg,http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg,http://a.hiphotos.baidu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg,http://g.hiphotos.baidu.com/image/h%3D300/sign=b5e4c905865494ee982209191df4e0e1/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0"},{"id":"5","uid":"1","cate":"2","type":"视频","content":"http://v6.starapp.cc/uploads/video/20181111/4c4592068038796518887b2070124b93.mp4","share":"0","comment":"0","zan":"0","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net/default.jpg","click":"0"},{"id":"4","uid":"1","cate":"2","type":"文字","content":"dsfdsaf","share":"1","comment":"2","zan":"3","createtime":"0","status":"1","zhiding":"0","tui":"0","image":"http://live.shuiqiao.net","click":"0"}]
             * isguanzhu : 0
             * ulist : []
             */

            private String cid;
            private String title;
            private String icon;
            private String description;
            private String tui;
            private String name;
            private int isguanzhu;
            private List<ListBean> list;
            private List<?> ulist;

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

            public int getIsguanzhu() {
                return isguanzhu;
            }

            public void setIsguanzhu(int isguanzhu) {
                this.isguanzhu = isguanzhu;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<?> getUlist() {
                return ulist;
            }

            public void setUlist(List<?> ulist) {
                this.ulist = ulist;
            }

            public static class ListBean {
                /**
                 * id : 6
                 * uid : 1
                 * cate : 2
                 * type : 图片
                 * content : http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg,http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg,http://a.hiphotos.baidu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg,http://g.hiphotos.baidu.com/image/h%3D300/sign=b5e4c905865494ee982209191df4e0e1/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg
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
}
