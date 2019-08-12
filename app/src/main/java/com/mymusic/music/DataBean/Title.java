package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/11 22:42
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Title {

    /**
     * data : {"list":[{"title":"最新发布","list":["按时间排序","按播放排序"]},{"title":"最新歌曲","list":["内地歌手"]},{"title":"全部地区","list":["陕西"]},{"title":"全部时长","list":["1小时内"]},{"title":"全部发布","list":["发布排序一"]}]}
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
             * title : 最新发布
             * list : ["按时间排序","按播放排序"]
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
