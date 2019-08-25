package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/11 22:42
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Title {

    /**
     * data : {"list":[{"title":"最新发布","list":[{"did":"10001","title":"最新发布"},{"did":"10002","title":"最火视频"}]},{"title":"全部时长","list":[{"cid":"10003","title":"30"},{"cid":"10004","title":"60"},{"cid":"10005","id":"90"}]},{"title":null,"list":[{"id":"-1","title":"全部"},{"id":"1","title":"内地"},{"id":"2","title":"港台"},{"id":"3","title":"欧美"},{"id":"4","title":"日韩"}]}]}
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
             * title : 最新发布
             * list : [{"did":"10001","title":"最新发布"},{"did":"10002","title":"最火视频"}]
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
                 * did : 10001
                 * title : 最新发布
                 */

                private String did;
                private String cid;
                private String id;
                private String title;

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getDid() {
                    return did;
                }

                public void setDid(String did) {
                    this.did = did;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
