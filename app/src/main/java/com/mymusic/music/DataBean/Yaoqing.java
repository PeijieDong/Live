package com.mymusic.music.DataBean;

public class Yaoqing {

    /**
     * data : {"qrcodeimg":"http://live.shuiqiao.net/wxpay/pay/qrcode.php?data=http://live.shuiqiao.net/index/index?invite=6TICN1","invitecontent":"卡哇伊直播，老平台、观众多。链接如被微信QQ拼比，请复制链接到浏览器打开。下载网址：http://live.shuiqiao.net/index/index?invite=6TICN1,下载备用:http://live.shuiqiao5.net/index/index?invite=6TICN1"}
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
         * qrcodeimg : http://live.shuiqiao.net/wxpay/pay/qrcode.php?data=http://live.shuiqiao.net/index/index?invite=6TICN1
         * invitecontent : 卡哇伊直播，老平台、观众多。链接如被微信QQ拼比，请复制链接到浏览器打开。下载网址：http://live.shuiqiao.net/index/index?invite=6TICN1,下载备用:http://live.shuiqiao5.net/index/index?invite=6TICN1
         */

        private String qrcodeimg;
        private String invitecontent;

        public String getQrcodeimg() {
            return qrcodeimg;
        }

        public void setQrcodeimg(String qrcodeimg) {
            this.qrcodeimg = qrcodeimg;
        }

        public String getInvitecontent() {
            return invitecontent;
        }

        public void setInvitecontent(String invitecontent) {
            this.invitecontent = invitecontent;
        }
    }
}
