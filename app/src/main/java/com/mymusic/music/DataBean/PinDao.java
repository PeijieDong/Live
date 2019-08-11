package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 13:50
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class PinDao {

    /**
     * data : {"list":[{"title":"精选","pid":"1","list":[{"id":"5","pid":"1","title":"分类一","otype":"typeTwo"},{"id":"6","pid":"1","title":"分类二","otype":"typeThree"}]},{"title":"男歌手","pid":"2","list":[{"id":"7","pid":"2","title":"typeTwo","otype":"分类三"},{"id":"8","pid":"2","title":"分类四","otype":"typeThree"}]},{"title":"女歌手","pid":"3","list":[{"id":"9","pid":"3","title":"分类五","otype":"typeTwo"},{"id":"10","pid":"3","title":"分类六","otype":"typeThree"}]},{"title":"内地歌手","pid":"4"}]}
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
             * title : 精选
             * pid : 1
             * list : [{"id":"5","pid":"1","title":"分类一","otype":"typeTwo"},{"id":"6","pid":"1","title":"分类二","otype":"typeThree"}]
             */

            private String title;
            private String pid;
            private List<ListBean> list;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 5
                 * pid : 1
                 * title : 分类一
                 * otype : typeTwo
                 */

                private String id;
                private String pid;
                private String title;
                private String otype;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getOtype() {
                    return otype;
                }

                public void setOtype(String otype) {
                    this.otype = otype;
                }
            }
        }
    }
}
