package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/11 21:45
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Audit {


    /**
     * data : {"list":[{"id":"4","type":"1","touid":"100020","content":"您发布的圈子由于违反社区公约，已被驳回，请修改后再次提交","status":"0","createtime":"2019-07-26 17:09:59","title":"测试审核中","newsid":"11","ntype":"文字","isvideo":"0","images":"","shenhe":"0"},{"id":"5","type":"1","touid":"100020","content":"您发布的圈子由于违反社区公约，已被驳回，请修改后再次提交","status":"0","createtime":"2019-07-26 17:11:46","title":"这是要被拒绝的","newsid":"10","ntype":"文字","isvideo":"0","images":"","shenhe":"0"},{"id":"6","type":"1","touid":"100020","content":"您发布的圈子已通过审核","status":"0","createtime":"2019-07-26 17:27:58","title":"这是要被拒绝的","newsid":"10","ntype":"文字","isvideo":"0","images":"","shenhe":"0"},{"id":"7","type":"1","touid":"100020","content":"您发布的圈子已通过审核","status":"0","createtime":"2019-07-26 17:28:53","title":"","newsid":"4","ntype":"视频","isvideo":"0","images":"http://live.shuiqiao.net/data/upload/video/156390195328.jpg","shenhe":"0"},{"id":"8","type":"1","touid":"100020","content":"您发布的圈子由于违反社区公约，已被驳回，请修改后再次提交","status":"0","createtime":"2019-07-26 17:28:59","title":"","newsid":"4","ntype":"视频","isvideo":"0","images":"http://live.shuiqiao.net/data/upload/video/156390195328.jpg","shenhe":"0"},{"id":"9","type":"1","touid":"100020","content":"您发布的圈子已通过审核","status":"0","createtime":"2019-07-26 17:29:05","title":"","newsid":"4","ntype":"视频","isvideo":"0","images":"http://live.shuiqiao.net/data/upload/video/156390195328.jpg","shenhe":"0"},{"id":"10","type":"1","touid":"100020","content":"您发布的圈子已通过审核","status":"0","createtime":"2019-07-26 17:29:19","title":"","newsid":"5","ntype":"视频","isvideo":"0","images":"http://live.shuiqiao.net/data/upload/video/156390394814.jpg","shenhe":"0"}]}
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
             * id : 4
             * type : 1
             * touid : 100020
             * content : 您发布的圈子由于违反社区公约，已被驳回，请修改后再次提交
             * status : 0
             * createtime : 2019-07-26 17:09:59
             * title : 测试审核中
             * newsid : 11
             * ntype : 文字
             * isvideo : 0
             * images :
             * shenhe : 0
             */

            private String id;
            private String type;
            private String touid;
            private String content;
            private String status;
            private String createtime;
            private String title;
            private String newsid;
            private String ntype;
            private String isvideo;
            private String images;
            private String shenhe;

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

            public String getNtype() {
                return ntype;
            }

            public void setNtype(String ntype) {
                this.ntype = ntype;
            }

            public String getIsvideo() {
                return isvideo;
            }

            public void setIsvideo(String isvideo) {
                this.isvideo = isvideo;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getShenhe() {
                return shenhe;
            }

            public void setShenhe(String shenhe) {
                this.shenhe = shenhe;
            }
        }
    }
}
