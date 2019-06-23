package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/23 21:25
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Art {

    /**
     * data : {"list":[{"cid":"4","vid":"2","uid":"9999","content":"2132dsfad","num":"0","createtime":"1970-01-01","pid":"0","type":"1"},{"cid":"3","vid":"2","uid":"9999","content":"dsfasadfsd","num":"0","createtime":"1970-01-01","pid":"0","type":"1"},{"cid":"2","vid":"2","uid":"9999","content":"sdafasdf","num":"0","createtime":"1970-01-01","pid":"0","type":"1"},{"cid":"1","vid":"2","uid":"9999","content":"sdafsdf","num":"0","createtime":"1970-01-01","pid":"0","type":"1"}],"status":1}
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
         * list : [{"cid":"4","vid":"2","uid":"9999","content":"2132dsfad","num":"0","createtime":"1970-01-01","pid":"0","type":"1"},{"cid":"3","vid":"2","uid":"9999","content":"dsfasadfsd","num":"0","createtime":"1970-01-01","pid":"0","type":"1"},{"cid":"2","vid":"2","uid":"9999","content":"sdafasdf","num":"0","createtime":"1970-01-01","pid":"0","type":"1"},{"cid":"1","vid":"2","uid":"9999","content":"sdafsdf","num":"0","createtime":"1970-01-01","pid":"0","type":"1"}]
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
             * cid : 4
             * vid : 2
             * uid : 9999
             * content : 2132dsfad
             * num : 0
             * createtime : 1970-01-01
             * pid : 0
             * type : 1
             */

            private String cid;
            private String vid;
            private String uid;
            private String content;
            private String num;
            private String createtime;
            private String pid;
            private String type;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
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

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
