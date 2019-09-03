package com.mymusic.music.DataBean;

import java.util.List;

public class SignIn {

    /**
     * status : 1
     * data : {"money":0.1,"totaldays":1,"list":[{"id":"3","uid":"100027","createtime":"1567440000","money":"0.1","times":"1","addtime":"2019-09-03 17:25:15"}]}
     * referer :
     * state : 1
     */

    private String status;
    private DataBean data;
    private String referer;
    private String state;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
         * money : 0.1
         * totaldays : 1
         * list : [{"id":"3","uid":"100027","createtime":"1567440000","money":"0.1","times":"1","addtime":"2019-09-03 17:25:15"}]
         */

        private double money;
        private int totaldays;
        private List<ListBean> list;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getTotaldays() {
            return totaldays;
        }

        public void setTotaldays(int totaldays) {
            this.totaldays = totaldays;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 3
             * uid : 100027
             * createtime : 1567440000
             * money : 0.1
             * times : 1
             * addtime : 2019-09-03 17:25:15
             */

            private String id;
            private String uid;
            private String createtime;
            private String money;
            private String times;
            private String addtime;

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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
