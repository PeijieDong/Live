package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By MR.D
 * 2019/7/10
 * USE:
 **/
public class Task {

    /**
     * data : {"list":[{"score":"0","consumption":"0","title":"推广任务","list":[{"id":"1","title":"邀请用户，下载并登陆","icon":"","integral":"10","experience":"4","type":"1","desc":"积分+10 经验+ ","completeValue":"0","totalValue":"2","status":"1"},{"id":"2","title":"分享帖子或啪啪","icon":"","integral":"10","experience":"5","type":"2","desc":"积分+10 经验+ ","completeValue":"0","totalValue":"5","status":"1"}]},{"title":"推广任务","list":[{"id":"1","title":"邀请用户，下载并登陆","icon":"","integral":"10","experience":"4","type":"1","desc":"积分+10 经验+ ","completeValue":"0","totalValue":"2","status":"1"},{"id":"2","title":"分享帖子或啪啪","icon":"","integral":"10","experience":"5","type":"2","desc":"积分+10 经验+ ","completeValue":"0","totalValue":"5","status":"1"}]},{"title":"新手任务","list":[{"id":"3","title":"浏览帖子","desc":"积分+10 经验+ ","icon":"","integral":"10","experience":"5","completeValue":"0","totalValue":"5","type":"3","status":"1"},{"id":"4","title":"观看啪啪视频","desc":"积分+10 经验+ ","icon":"","integral":"10","experience":"4","completeValue":"0","totalValue":"2","type":"4","status":"1"},{"id":"5","title":"成功发表帖子","desc":"积分+10 经验+ ","icon":"","integral":"10","experience":"5","completeValue":"0","totalValue":"1","type":"5","status":"1"},{"id":"6","title":"收到其他用户评论","desc":"积分+10 经验+ ","icon":"","integral":"10","experience":"5","completeValue":"0","totalValue":"1","type":"6","status":"1"},{"id":"7","title":"收到点赞","desc":"积分+10 经验+ ","icon":"","integral":"10","experience":"5","completeValue":"0","totalValue":"1","type":"7","status":"1"},{"id":"8","title":"修改个人资料","desc":"积分+10 经验+ ","icon":"","integral":"10","experience":"5","completeValue":"0","totalValue":"1","type":"8","status":"1"}]}]}
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
        private List<ListBeanX> list;

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * score : 0
             * consumption : 0
             * title : 推广任务
             * list : [{"id":"1","title":"邀请用户，下载并登陆","icon":"","integral":"10","experience":"4","type":"1","desc":"积分+10 经验+ ","completeValue":"0","totalValue":"2","status":"1"},{"id":"2","title":"分享帖子或啪啪","icon":"","integral":"10","experience":"5","type":"2","desc":"积分+10 经验+ ","completeValue":"0","totalValue":"5","status":"1"}]
             */

            private String score;
            private String consumption;
            private String title;
            private List<ListBean> list;

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getConsumption() {
                return consumption;
            }

            public void setConsumption(String consumption) {
                this.consumption = consumption;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 1
                 * title : 邀请用户，下载并登陆
                 * icon :
                 * integral : 10
                 * experience : 4
                 * type : 1
                 * desc : 积分+10 经验+
                 * completeValue : 0
                 * totalValue : 2
                 * status : 1
                 */

                private String id;
                private String title;
                private String icon;
                private String integral;
                private String experience;
                private String type;
                private String desc;
                private String completeValue;
                private String totalValue;
                private String status;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getIntegral() {
                    return integral;
                }

                public void setIntegral(String integral) {
                    this.integral = integral;
                }

                public String getExperience() {
                    return experience;
                }

                public void setExperience(String experience) {
                    this.experience = experience;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getCompleteValue() {
                    return completeValue;
                }

                public void setCompleteValue(String completeValue) {
                    this.completeValue = completeValue;
                }

                public String getTotalValue() {
                    return totalValue;
                }

                public void setTotalValue(String totalValue) {
                    this.totalValue = totalValue;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}