package com.mymusic.music.DataBean;

/**
 * Create By MR.D
 * 2019/7/26
 * USE:
 **/
public class ShareFriend {

    /**
     * data : {"list":"http://www.baidu.com"}
     * stauts : 1
     * referer :
     * state : 0
     */

    private DataBean data;
    private String stauts;
    private String referer;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
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
         * list : http://www.baidu.com
         */

        private String list;

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }
}
