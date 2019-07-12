package com.mymusic.music.DataBean;

/**
 * Create By MR.D
 * 2019/7/12
 * USE:
 **/
public class AdvEntity {


    /**
     * data : {"list":{"imgurl":"http://app.poouo.com/gamephoto/logo.jpg","link":"https://down.yszweb.com/gamecenter-release-android-fuyao-6249-344ee45a5542f61cac8f700156a1d20a.apk","title":"开屏"}}
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
         * list : {"imgurl":"http://app.poouo.com/gamephoto/logo.jpg","link":"https://down.yszweb.com/gamecenter-release-android-fuyao-6249-344ee45a5542f61cac8f700156a1d20a.apk","title":"开屏"}
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
             * imgurl : http://app.poouo.com/gamephoto/logo.jpg
             * link : https://down.yszweb.com/gamecenter-release-android-fuyao-6249-344ee45a5542f61cac8f700156a1d20a.apk
             * title : 开屏
             */

            private String imgurl;
            private String link;
            private String title;

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
