package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/14 21:23
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Focus {

    /**
     * data : {"list":[{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"9999","icon":null,"title":null,"description":null,"cid":"9999","guanzhu":"已关注"},{"uid":"100001","touid":"36297","icon":null,"title":null,"description":null,"cid":"36297","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"}],"total":"16"}
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
         * list : [{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"9999","icon":null,"title":null,"description":null,"cid":"9999","guanzhu":"已关注"},{"uid":"100001","touid":"36297","icon":null,"title":null,"description":null,"cid":"36297","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"},{"uid":"100001","touid":"1","icon":"http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png","title":"测试分类33","description":"这里是描述","cid":"1","guanzhu":"已关注"}]
         * total : 16
         */

        private String total;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * uid : 100001
             * touid : 1
             * icon : http://live.shuiqiao.net/data/upload/20190711/5d26c23415e3d.png
             * title : 测试分类33
             * description : 这里是描述
             * cid : 1
             * guanzhu : 已关注
             */

            private String uid;
            private String touid;
            private String icon;
            private String title;
            private String description;
            private String cid;
            private String guanzhu;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getTouid() {
                return touid;
            }

            public void setTouid(String touid) {
                this.touid = touid;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getGuanzhu() {
                return guanzhu;
            }

            public void setGuanzhu(String guanzhu) {
                this.guanzhu = guanzhu;
            }
        }
    }
}
