package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/22 22:26
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class History {

    /**
     * data : {"list":[{"hid":"6","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"5","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"4","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"3","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"2","vid":"2","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"1","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""}],"status":1}
     * referer :
     * state : 0
     */

    private DataBean data;
    private String referer;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
         * list : [{"hid":"6","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"5","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"4","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"3","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"2","vid":"2","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""},{"hid":"1","vid":"1","type":"1","createtime":"1970-01-01 08:00:00","uid":"9999","title":""}]
         * status : 1
         */

        private int status;
        private List<ListBean> list;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * hid : 6
             * vid : 1
             * type : 1
             * createtime : 1970-01-01 08:00:00
             * uid : 9999
             * title :
             */

            private String hid;
            private String vid;
            private String type;
            private String createtime;
            private String uid;
            private String title;

            public String getHid() {
                return hid;
            }

            public void setHid(String hid) {
                this.hid = hid;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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
