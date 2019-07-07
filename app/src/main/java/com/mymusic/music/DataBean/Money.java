package com.mymusic.music.DataBean;

/**
 * Create By mr.mao in 2019/7/7 15:57
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Money {

    /**
     * data : {"msg":"订单提交成功","url":"http://live.shuiqiao.net/Home/Pay/topay?id=7120"}
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
        /**
         * msg : 订单提交成功
         * url : http://live.shuiqiao.net/Home/Pay/topay?id=7120
         */

        private String msg;
        private String url;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
