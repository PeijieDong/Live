package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/12 21:59
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Find {

    /**
     * data : {"list":[{"title":"热门搜索","list":["国产","中文"]},{"title":"热门标签","list":["进阶","剧情"]},{"title":"热门标签","list":["国产","动漫"]},{"title":"进阶","list":["明星","韩国"]},{"title":"剧情","list":["催眠","原著"]},{"title":"玩法","list":["玩法1","玩法2"]}]}
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
             * title : 热门搜索
             * list : ["国产","中文"]
             */

            private String title;
            private List<String> list;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getList() {
                return list;
            }

            public void setList(List<String> list) {
                this.list = list;
            }
        }
    }
}
