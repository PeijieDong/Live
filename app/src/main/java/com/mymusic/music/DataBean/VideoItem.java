package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 19:28
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoItem {

    /**
     * data : {"list":[{"title":"分类一","type":"typeTwo","list":[{"id":"1","img":"img.jpg","title":"哈哈","playtime":"00:01:05"},{"id":"1","img":"img.jpg","title":"哈哈","playtime":"00:01:05"}]},{"title":"分类二","type":"typeThree","list":[{"id":"1","img":"img.jpg","title":"哈哈"},{"id":"1","img":"img.jpg","title":"哈哈","playtime":"00:01:05"}]}],"ad":[{"url":"http://www.baidu.com","img":"image.jpg"},{"url":"http://www.baidu.com","img":"image.jpg"}]}
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
             * title : 分类一
             * type : typeTwo
             * list : [{"id":"1","img":"img.jpg","title":"哈哈"},{"id":"1","img":"img.jpg","title":"哈哈","playtime":"00:01:05"}]
             */

            private String title;
            private String type;
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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 1
                 * img : img.jpg
                 * title : 哈哈
                 * playtime : 00:01:05
                 */

                private String id;
                private String img;
                private String title;
                private String playtime;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
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
