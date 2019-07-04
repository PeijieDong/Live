package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/4 21:49
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MoneyDetail {

    /**
     * data : {"list":[{"id":"5","type":"1","num":"5532","createtime":"1970-05-23 22:39:24","uid":"9999","content":"dfasfdsa"},{"id":"4","type":"2","num":"322","createtime":"1970-01-04 22:28:41","uid":"9999","content":"56456safsdf"},{"id":"2","type":"3","num":"100","createtime":"1970-01-01 08:35:31","uid":"9999","content":"3215246"},{"id":"1","type":"1","num":"1","createtime":"1970-01-01 08:01:51","uid":"9999","content":""}]}
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
             * id : 5
             * type : 1
             * num : 5532
             * createtime : 1970-05-23 22:39:24
             * uid : 9999
             * content : dfasfdsa
             */

            private String id;
            private String type;
            private String num;
            private String createtime;
            private String uid;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
