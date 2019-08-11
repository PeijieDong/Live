package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/9 21:59
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class TopType {

    /**
     * data : {"list":[{"id":"1","title":"精选"},{"id":"2","title":"男歌手"},{"id":"3","title":"女歌手"},{"id":"4","title":"内地歌手"},{"id":"5","title":"分类一"},{"id":"6","title":"分类二"},{"id":"7","title":"typeTwo"},{"id":"8","title":"分类四"},{"id":"9","title":"分类五"},{"id":"10","title":"分类六"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * title : 精选
             */

            private String id;
            private String title;

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
        }
    }
}
