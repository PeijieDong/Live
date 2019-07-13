package com.mymusic.music.DataBean;

/**
 * Create By mr.mao in 2019/7/13 15:00
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class SettingMes {

    /**
     * data : {"list":{"getnotice":"1","sysnotice":"1","feednotice":"1","tongzhinotice":"1"},"stauts":1}
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
         * list : {"getnotice":"1","sysnotice":"1","feednotice":"1","tongzhinotice":"1"}
         * stauts : 1
         */

        private ListBean list;
        private int stauts;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public int getStauts() {
            return stauts;
        }

        public void setStauts(int stauts) {
            this.stauts = stauts;
        }

        public static class ListBean {
            /**
             * getnotice : 1
             * sysnotice : 1
             * feednotice : 1
             * tongzhinotice : 1
             */

            private String getnotice;
            private String sysnotice;
            private String feednotice;
            private String tongzhinotice;

            public String getGetnotice() {
                return getnotice;
            }

            public void setGetnotice(String getnotice) {
                this.getnotice = getnotice;
            }

            public String getSysnotice() {
                return sysnotice;
            }

            public void setSysnotice(String sysnotice) {
                this.sysnotice = sysnotice;
            }

            public String getFeednotice() {
                return feednotice;
            }

            public void setFeednotice(String feednotice) {
                this.feednotice = feednotice;
            }

            public String getTongzhinotice() {
                return tongzhinotice;
            }

            public void setTongzhinotice(String tongzhinotice) {
                this.tongzhinotice = tongzhinotice;
            }
        }
    }
}
