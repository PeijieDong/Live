package com.mymusic.music.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Create By mr.mao in 2019/7/13 15:48
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class NoticeMess implements Serializable {

    /**
     * data : {"list":[{"id":"1","type":"5","touid":"0","content":"维护公告内容","status":"0","createtime":"0","title":"维护公告","newsid":"0"},{"id":"2","type":"5","touid":"0","content":"打发第三方","status":"0","createtime":"0","title":"紧急通知","newsid":"0"}]}
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

    public static class DataBean implements Serializable {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * id : 1
             * type : 5
             * touid : 0
             * content : 维护公告内容
             * status : 0
             * createtime : 0
             * title : 维护公告
             * newsid : 0
             */

            private String id;
            private String type;
            private String touid;
            private String content;
            private String status;
            private String createtime;
            private String title;
            private String newsid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTouid() {
                return touid;
            }

            public void setTouid(String touid) {
                this.touid = touid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNewsid() {
                return newsid;
            }

            public void setNewsid(String newsid) {
                this.newsid = newsid;
            }
        }
    }
}
