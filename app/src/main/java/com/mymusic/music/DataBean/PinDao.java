package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 13:50
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class PinDao {

    /**
     * data : {"list":[{"title":"内地","pid":"1","list":[{"id":"13","pid":"1","title":"内地女歌手","otype":"typeTwo","istop":"1","sortby":"0"},{"id":"12","pid":"1","title":"内地男歌手","otype":"typeTwo","istop":"0","sortby":"0"}]},{"title":"港台","pid":"2","list":[{"id":"6","pid":"2","title":"精选1","otype":"typeTwo","istop":"0","sortby":"0"}]},{"title":"欧美","pid":"3","list":[{"id":"7","pid":"3","title":"精选1","otype":"typeTwo","istop":"0","sortby":"0"}]},{"title":"日韩","pid":"4","list":[{"id":"8","pid":"4","title":"精选1","otype":"typeTwo","istop":"0","sortby":"0"}]}]}
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
             * title : 内地
             * pid : 1
             * list : [{"id":"13","pid":"1","title":"内地女歌手","otype":"typeTwo","istop":"1","sortby":"0"},{"id":"12","pid":"1","title":"内地男歌手","otype":"typeTwo","istop":"0","sortby":"0"}]
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
                 * id : 13
                 * pid : 1
                 * title : 内地女歌手
                 * otype : typeTwo
                 * istop : 1
                 * sortby : 0
                 */

                private String id;
                private String pid;
                private String title;
                private String otype;
                private String istop;
                private String sortby;

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

                public String getIstop() {
                    return istop;
                }

                public void setIstop(String istop) {
                    this.istop = istop;
                }

                public String getSortby() {
                    return sortby;
                }

                public void setSortby(String sortby) {
                    this.sortby = sortby;
                }
            }
        }
    }
}
