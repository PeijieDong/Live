package com.mymusic.music.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Create By mr.mao in 2019/7/11 20:23
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FeedBack implements Serializable {

    /**
     * data : {"list":[{"id":"2","uid":"9999","content":"","contact":"jfjfjff","type":"1","addtime":"2019-07-11 20:22:43","images":"","replay":"","replaytime":"0","status":"0"},{"id":"3","uid":"9999","content":"","contact":"jfjfjff","type":"1","addtime":"2019-07-11 20:22:45","images":"","replay":"","replaytime":"0","status":"0"},{"id":"4","uid":"9999","content":"","contact":"jfjffu","type":"2","addtime":"2019-07-11 20:23:01","images":"","replay":"","replaytime":"0","status":"0"},{"id":"5","uid":"9999","content":"","contact":"jfjffu","type":"2","addtime":"2019-07-11 20:23:03","images":"","replay":"","replaytime":"0","status":"0"}]}
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
             * id : 2
             * uid : 9999
             * content :
             * contact : jfjfjff
             * type : 1
             * addtime : 2019-07-11 20:22:43
             * images :
             * replay :
             * replaytime : 0
             * status : 0
             */

            private String id;
            private String uid;
            private String content;
            private String contact;
            private String type;
            private String addtime;
            private String images;
            private String replay;
            private String replaytime;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getReplay() {
                return replay;
            }

            public void setReplay(String replay) {
                this.replay = replay;
            }

            public String getReplaytime() {
                return replaytime;
            }

            public void setReplaytime(String replaytime) {
                this.replaytime = replaytime;
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
