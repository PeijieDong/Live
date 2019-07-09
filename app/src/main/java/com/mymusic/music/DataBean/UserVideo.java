package com.mymusic.music.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Create By mr.mao in 2019/6/30 0:57
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserVideo implements Serializable {

    /**
     * data : {"list":[{"vid":"15","uid":"9999","content":"度屠夫卡通","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561563152","filepath":"/data/upload/video/5d139010631df.mp4","tag":"白领,护士,原创,自拍,打电话,ktv,"},{"vid":"14","uid":"9999","content":"了了噜噜噜啊","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561563109","filepath":"/data/upload/video/5d138fe5b8409.mp4","tag":"白领,护士,原创,"},{"vid":"13","uid":"9999","content":"了了噜噜噜啊","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561563107","filepath":"/data/upload/video/5d138fe3cdcab.mp4","tag":"白领,护士,原创,"},{"vid":"12","uid":"9999","content":"了了噜噜噜啊","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561563105","filepath":"/data/upload/video/5d138fe1567ff.mp4","tag":"白领,护士,原创,"},{"vid":"11","uid":"9999","content":"了了噜噜噜啊","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561563099","filepath":"/data/upload/video/5d138fdbd7486.mp4","tag":"白领,护士,原创,"},{"vid":"10","uid":"9999","content":"","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561338164","filepath":"/data/upload/video/5d10213468557.mp4","tag":"agt"},{"vid":"9","uid":"9999","content":"","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561336327","filepath":"/data/upload/video/5d101a0796c12.mp4","tag":"agt"},{"vid":"8","uid":"9999","content":"","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561336201","filepath":"/data/upload/","tag":"agt"},{"vid":"7","uid":"9999","content":"","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561336171","filepath":"/data/upload/","tag":"agt"},{"vid":"6","uid":"9999","content":"123456","zan":"0","comment":"0","share":"0","status":"1","createtime":"1561305034","filepath":"","tag":"agt"}]}
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

    public static class DataBean implements Serializable{
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * vid : 15
             * uid : 9999
             * content : 度屠夫卡通
             * zan : 0
             * comment : 0
             * share : 0
             * status : 1
             * createtime : 1561563152
             * filepath : /data/upload/video/5d139010631df.mp4
             * tag : 白领,护士,原创,自拍,打电话,ktv,
             */

            private String vid;
            private String uid;
            private String content;
            private String zan;
            private String comment;
            private String share;
            private String status;
            private String createtime;
            private String filepath;
            private String tag;

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getZan() {
                return zan;
            }

            public void setZan(String zan) {
                this.zan = zan;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getShare() {
                return share;
            }

            public void setShare(String share) {
                this.share = share;
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

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }
}
