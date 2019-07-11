package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/11 23:03
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Scroe {

    /**
     * data : {"list":[{"id":"3","uid":"9999","score":"-2","type":"邀请用户","addtime":"2024-12-27 09:17:34","ntype":"1","isadd":"0"},{"id":"5","uid":"9999","score":"-3","type":"邀请用户","addtime":"2024-12-27 09:17:34","ntype":"1","isadd":"0"},{"id":"8","uid":"9999","score":"-1","type":"邀请用户","addtime":"1975-07-03 01:03:45","ntype":"1","isadd":"0"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 3
             * uid : 9999
             * score : -2
             * type : 邀请用户
             * addtime : 2024-12-27 09:17:34
             * ntype : 1
             * isadd : 0
             */

            private String id;
            private String uid;
            private String score;
            private String type;
            private String addtime;
            private String ntype;
            private String isadd;

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

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getNtype() {
                return ntype;
            }

            public void setNtype(String ntype) {
                this.ntype = ntype;
            }

            public String getIsadd() {
                return isadd;
            }

            public void setIsadd(String isadd) {
                this.isadd = isadd;
            }
        }
    }
}
