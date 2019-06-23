package com.mymusic.music.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Create By mr.mao in 2019/6/22 10:29
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class SignTag implements Serializable {
    /**
     * list : [{"name":"角色","list":[{"id":"1","title":"白领","ncate":"角色","status":"0"},{"id":"2","title":"护士","ncate":"角色","status":"0"}]},{"name":"来源","list":[{"id":"3","title":"原创","ncate":"来源","status":"0"},{"id":"4","title":"自拍","ncate":"来源","status":"0"}]},{"name":"剧情","list":[{"id":"5","title":"ktv","ncate":"剧情","status":"0"},{"id":"6","title":"打电话","ncate":"剧情","status":"0"}]},{"name":"身材","list":[{"id":"7","title":"肌肉男","ncate":"身材","status":"0"},{"id":"8","title":"肌肉男","ncate":"身材","status":"0"}]},{"name":"玩法","list":[{"id":"9","title":"玩具","ncate":"玩法","status":"0"}]},{"name":"题材","list":[{"id":"10","title":"电影","ncate":"题材","status":"0"}]}]
     * status : 1
     * referer :
     * state : 1
     */

    private int status;
    private String referer;
    private String state;
    private List<ListBeanX> list;

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

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX implements Serializable {
        /**
         * name : 角色
         * list : [{"id":"1","title":"白领","ncate":"角色","status":"0"},{"id":"2","title":"护士","ncate":"角色","status":"0"}]
         */

        private String name;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * id : 1
             * title : 白领
             * ncate : 角色
             * status : 0
             */

            private String id;
            private String title;
            private String ncate;
            private String status;

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

            public String getNcate() {
                return ncate;
            }

            public void setNcate(String ncate) {
                this.ncate = ncate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
