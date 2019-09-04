package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 19:28
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoItem {


    /**
     * data : {"list":[{"title":"内地女歌手","type":"typeTwo","pid":"13","list":[{"id":"l42","title":"天天 Amber-邱比 CHIU PI,天天 AmberMV免费下载,高清MV在线观看 _千千音乐-听见世界","playtime":"0","image":""},{"id":"l38","title":"","playtime":"0","image":""},{"id":"l37","title":"我愿意平凡的陪在你身旁-王七七","playtime":"0","image":"http://live.shuiqiao.net/data/upload/20190826/5d6361cb2bd83.jpg"}],"image":"http://live.shuiqiao.net/data/upload/20190830/5d68d092d8f7f.jpg","link":"http://www.baidu.com"},{"title":"内地男歌手","type":"typeTwo","pid":"12","list":[{"id":"l35","title":"天份-薛之谦","playtime":"0","image":"http://live.shuiqiao.net/data/upload/20190826/5d6357df2819d.jpg"}]},{"title":"广告","type":"广告","image":"http://live.shuiqiao.net/data/upload/20190830/5d68d092d8f7f.jpg","link":"http://www.baidu.com"},{"title":"中国新说唱","type":"typeTwo","pid":"21"},{"title":"一起乐队吧","type":"typeTwo","pid":"22"}],"ad":[{"url":"http://www.baidu.com","img":"image.jpg"},{"url":"http://www.baidu.com","img":"image.jpg"}]}
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
        private List<ListBeanX> list;
        private List<AdBean> ad;

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public List<AdBean> getAd() {
            return ad;
        }

        public void setAd(List<AdBean> ad) {
            this.ad = ad;
        }

        public static class ListBeanX {
            /**
             * title : 内地女歌手
             * type : typeTwo
             * pid : 13
             * list : [{"id":"l42","title":"天天 Amber-邱比 CHIU PI,天天 AmberMV免费下载,高清MV在线观看 _千千音乐-听见世界","playtime":"0","image":""},{"id":"l38","title":"","playtime":"0","image":""},{"id":"l37","title":"我愿意平凡的陪在你身旁-王七七","playtime":"0","image":"http://live.shuiqiao.net/data/upload/20190826/5d6361cb2bd83.jpg"}]
             * image : http://live.shuiqiao.net/data/upload/20190830/5d68d092d8f7f.jpg
             * link : http://www.baidu.com
             */

            private String title;
            private String type;
            private String pid;
            private String image;
            private String link;
            private List<ListBean> list;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : l42
                 * title : 天天 Amber-邱比 CHIU PI,天天 AmberMV免费下载,高清MV在线观看 _千千音乐-听见世界
                 * playtime : 0
                 * image :
                 */

                private String id;
                private String title;
                private String playtime;
                private String image;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPlaytime() {
                    return playtime;
                }

                public void setPlaytime(String playtime) {
                    this.playtime = playtime;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }
            }
        }

        public static class AdBean {
            /**
             * url : http://www.baidu.com
             * img : image.jpg
             */

            private String url;
            private String img;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
