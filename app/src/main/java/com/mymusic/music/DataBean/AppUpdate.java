package com.mymusic.music.DataBean;

/**
 * Create By mr.mao in 2019/7/28 23:15
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class AppUpdate {

    /**
     * isupdate : 1
     * url : https://fir.im/myylapp1
     * content : 有新版本，是否更新
     * mastupdate : 1
     * status : 1
     * referer : https://fir.im/myylapp1
     * state : 1
     */

    private String isupdate;
    private String url;
    private String content;
    private String mastupdate;
    private String status;
    private String referer;
    private String state;

    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMastupdate() {
        return mastupdate;
    }

    public void setMastupdate(String mastupdate) {
        this.mastupdate = mastupdate;
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
}
