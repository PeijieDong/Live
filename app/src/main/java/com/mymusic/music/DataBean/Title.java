package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/11 22:42
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Title {

    /**
     * data : {"list":{"最新发布":["按时间排序","按播放排序"],"最新歌曲":["内地歌手"],"全部地区":["陕西"],"全部时长":["1小时内"],"全部发布":["发布排序一"]}}
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
        /**
         * list : {"最新发布":["按时间排序","按播放排序"],"最新歌曲":["内地歌手"],"全部地区":["陕西"],"全部时长":["1小时内"],"全部发布":["发布排序一"]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            private List<String> 最新发布;
            private List<String> 最新歌曲;
            private List<String> 全部地区;
            private List<String> 全部时长;
            private List<String> 全部发布;

            public List<String> get最新发布() {
                return 最新发布;
            }

            public void set最新发布(List<String> 最新发布) {
                this.最新发布 = 最新发布;
            }

            public List<String> get最新歌曲() {
                return 最新歌曲;
            }

            public void set最新歌曲(List<String> 最新歌曲) {
                this.最新歌曲 = 最新歌曲;
            }

            public List<String> get全部地区() {
                return 全部地区;
            }

            public void set全部地区(List<String> 全部地区) {
                this.全部地区 = 全部地区;
            }

            public List<String> get全部时长() {
                return 全部时长;
            }

            public void set全部时长(List<String> 全部时长) {
                this.全部时长 = 全部时长;
            }

            public List<String> get全部发布() {
                return 全部发布;
            }

            public void set全部发布(List<String> 全部发布) {
                this.全部发布 = 全部发布;
            }
        }
    }
}
