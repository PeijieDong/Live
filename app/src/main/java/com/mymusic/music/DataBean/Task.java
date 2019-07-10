package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By MR.D
 * 2019/7/10
 * USE:
 **/
public class Task {
    /**
     * data : {"list":[{"title":"推广任务","list":[{"id":"1","title":"邀请用户，下载并登陆","desc":"积分+10 经验+4 完成0/2","status":"0"},{"id":"2","title":"分享帖子或啪啪","desc":"积分+10 经验+5 完成0/5","status":"0"}]},{"title":"新手任务","list":[{"id":"3","title":"点赞帖子或评论","desc":"积分+10 经验+4 完成0/2","status":"0"},{"id":"4","title":"完成账号注册","desc":"积分+10 经验+5 完成0/1","status":"0"},{"id":"5","title":"修改昵称，完善个人资料","desc":"积分+10 经验+5 完成0/1","status":"0"},{"id":"46","title":"浏览帖子","desc":"积分+10 经验+5 完成0/5","status":"0"}]}]}
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
        private List<ListBeanX> list;

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * title : 推广任务
             * list : [{"id":"1","title":"邀请用户，下载并登陆","desc":"积分+10 经验+4 完成0/2","status":"0"},{"id":"2","title":"分享帖子或啪啪","desc":"积分+10 经验+5 完成0/5","status":"0"}]
             */

            private String title;
            private List<ListBean> list;

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
                 * desc : 积分+10 经验+4 完成0/2
                 * status : 0
                 */

                private String id;
                private String title;
                private String desc;
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

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
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
