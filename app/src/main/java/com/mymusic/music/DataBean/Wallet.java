package com.mymusic.music.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Create By mr.mao in 2019/6/23 19:56
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Wallet implements Serializable {

    /**
     * money :
     * agent : [{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}]
     * online : [{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}]
     * paylist : [{"paytype":"alipay","name":"支付宝"},{"paytype":"wxpay","name":"微信支付"}]
     * data : {"list":{"money":"","agent":[{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}],"online":[{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}],"paylist":[{"paytype":"alipay","name":"支付宝"},{"paytype":"wxpay","name":"微信支付"}]}}
     * stauts : 1
     * referer :
     * state : 0
     */

    private String money;
    private DataBean data;
    private int stauts;
    private String referer;
    private String state;
    private List<AgentBeanX> agent;
    private List<OnlineBeanX> online;
    private List<PaylistBeanX> paylist;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStauts() {
        return stauts;
    }

    public void setStauts(int stauts) {
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

    public List<AgentBeanX> getAgent() {
        return agent;
    }

    public void setAgent(List<AgentBeanX> agent) {
        this.agent = agent;
    }

    public List<OnlineBeanX> getOnline() {
        return online;
    }

    public void setOnline(List<OnlineBeanX> online) {
        this.online = online;
    }

    public List<PaylistBeanX> getPaylist() {
        return paylist;
    }

    public void setPaylist(List<PaylistBeanX> paylist) {
        this.paylist = paylist;
    }

    public static class DataBean implements Serializable{
        /**
         * list : {"money":"","agent":[{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}],"online":[{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}],"paylist":[{"paytype":"alipay","name":"支付宝"},{"paytype":"wxpay","name":"微信支付"}]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * money :
             * agent : [{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}]
             * online : [{"money":"50","coin":"385个"},{"money":"300","coin":"2765个"},{"money":"500","coin":"4889个"},{"money":"2000","coin":"20000个"},{"money":"3000","coin":"30000个"},{"money":"10000","coin":"10000个"}]
             * paylist : [{"paytype":"alipay","name":"支付宝"},{"paytype":"wxpay","name":"微信支付"}]
             */

            private String money;
            private List<AgentBean> agent;
            private List<OnlineBean> online;
            private List<PaylistBean> paylist;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public List<AgentBean> getAgent() {
                return agent;
            }

            public void setAgent(List<AgentBean> agent) {
                this.agent = agent;
            }

            public List<OnlineBean> getOnline() {
                return online;
            }

            public void setOnline(List<OnlineBean> online) {
                this.online = online;
            }

            public List<PaylistBean> getPaylist() {
                return paylist;
            }

            public void setPaylist(List<PaylistBean> paylist) {
                this.paylist = paylist;
            }

            public static class AgentBean implements Serializable{
                /**
                 * money : 50
                 * coin : 385个
                 */

                private String money;
                private String coin;

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getCoin() {
                    return coin;
                }

                public void setCoin(String coin) {
                    this.coin = coin;
                }
            }

            public static class OnlineBean implements Serializable{
                /**
                 * money : 50
                 * coin : 385个
                 */

                private String money;
                private String coin;

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getCoin() {
                    return coin;
                }

                public void setCoin(String coin) {
                    this.coin = coin;
                }
            }

            public static class PaylistBean implements Serializable{
                /**
                 * paytype : alipay
                 * name : 支付宝
                 */

                private String paytype;
                private String name;

                public String getPaytype() {
                    return paytype;
                }

                public void setPaytype(String paytype) {
                    this.paytype = paytype;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }

    public static class AgentBeanX implements Serializable{
        /**
         * money : 50
         * coin : 385个
         */

        private String money;
        private String coin;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }
    }

    public static class OnlineBeanX implements Serializable{
        /**
         * money : 50
         * coin : 385个
         */

        private String money;
        private String coin;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }
    }

    public static class PaylistBeanX implements Serializable{
        /**
         * paytype : alipay
         * name : 支付宝
         */

        private String paytype;
        private String name;

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
