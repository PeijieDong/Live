package com.mymusic.music.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Create By mr.mao in 2019/6/9 21:17
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendDetailTOP implements Serializable {

    /**
     * data : {"list":{"cid":"2","title":"测试推荐","icon":"http://live.shuiqiao.net/default.jpg","description":"这里是描述","tui":"1","name":"美图","official":"0","gnum":"2","tiezi":"9","ulist":["http://live.shuiqiao.net/default_thumb.jpg","http://live.shuiqiao.net/default_thumb.jpg"]}}
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

    public static class DataBean implements Serializable {
        /**
         * list : {"cid":"2","title":"测试推荐","icon":"http://live.shuiqiao.net/default.jpg","description":"这里是描述","tui":"1","name":"美图","official":"0","gnum":"2","tiezi":"9","ulist":["http://live.shuiqiao.net/default_thumb.jpg","http://live.shuiqiao.net/default_thumb.jpg"]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * cid : 2
             * title : 测试推荐
             * icon : http://live.shuiqiao.net/default.jpg
             * description : 这里是描述
             * tui : 1
             * name : 美图
             * official : 0
             * gnum : 2
             * tiezi : 9
             * ulist : ["http://live.shuiqiao.net/default_thumb.jpg","http://live.shuiqiao.net/default_thumb.jpg"]
             */

            private String cid;
            private String title;
            private String icon;
            private String description;
            private String tui;
            private String name;
            private String official;
            private String gnum;
            private String tiezi;
            private List<String> ulist;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTui() {
                return tui;
            }

            public void setTui(String tui) {
                this.tui = tui;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOfficial() {
                return official;
            }

            public void setOfficial(String official) {
                this.official = official;
            }

            public String getGnum() {
                return gnum;
            }

            public void setGnum(String gnum) {
                this.gnum = gnum;
            }

            public String getTiezi() {
                return tiezi;
            }

            public void setTiezi(String tiezi) {
                this.tiezi = tiezi;
            }

            public List<String> getUlist() {
                return ulist;
            }

            public void setUlist(List<String> ulist) {
                this.ulist = ulist;
            }
        }
    }
}
