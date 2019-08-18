package com.mymusic.music.DataBean;

import java.util.List;

public class Vip {

    /**
     * list : [{"icon":"http://live.shuiqiao.net/default.jpg","title":"限时返利50%","activityDesc":"【天卡】开通后当日内无线次数观看","activityValue":"50%","rechargeURL":"http://live.shuiqiao.net","price":"10"},{"icon":"http://live.shuiqiao.net/default.jpg","title":"限时返利50%","activityDesc":"【月卡】开通后30天内无限次数观看","activityValue":"50%","rechargeURL":"http://live.shuiqiao.net","price":"20"},{"icon":"http://live.shuiqiao.net/default.jpg","title":"限时返利50%","activityDesc":"【季度】开通后90天内无限次数观看","activityValue":"50%","rechargeURL":"http://live.shuiqiao.net","price":"30"},{"icon":"http://live.shuiqiao.net/default.jpg","title":"限时返利50%","activityDesc":"【年卡】开通后365天内无限次数观看","activityValue":"50%","rechargeURL":"http://live.shuiqiao.net","price":"40"}]
     * status : 1
     * referer :
     * state : 1
     */

    private String status;
    private String referer;
    private String state;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * icon : http://live.shuiqiao.net/default.jpg
         * title : 限时返利50%
         * activityDesc : 【天卡】开通后当日内无线次数观看
         * activityValue : 50%
         * rechargeURL : http://live.shuiqiao.net
         * price : 10
         */

        private String icon;
        private String title;
        private String activityDesc;
        private String activityValue;
        private String rechargeURL;
        private String price;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActivityDesc() {
            return activityDesc;
        }

        public void setActivityDesc(String activityDesc) {
            this.activityDesc = activityDesc;
        }

        public String getActivityValue() {
            return activityValue;
        }

        public void setActivityValue(String activityValue) {
            this.activityValue = activityValue;
        }

        public String getRechargeURL() {
            return rechargeURL;
        }

        public void setRechargeURL(String rechargeURL) {
            this.rechargeURL = rechargeURL;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
