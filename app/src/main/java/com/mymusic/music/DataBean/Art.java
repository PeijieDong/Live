package com.mymusic.music.DataBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/23 21:25
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Art {

    /**
     * data : {"list":[{"cid":"34","vid":"15","uid":"9999","content":"hogogigicjcjcici","num":"0","createtime":"3天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"33","vid":"15","uid":"9999","content":"jcjcjcjcjckvkv","num":"0","createtime":"3天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"32","vid":"15","uid":"9999","content":"jfjcj","num":"0","createtime":"3天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"31","vid":"15","uid":"9999","content":"家里是夸我呢","num":"0","createtime":"3天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"30","vid":"15","uid":"9999","content":"嗯嗯","num":"0","createtime":"3天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"26","vid":"15","uid":"9999","content":"磨破MSN","num":"0","createtime":"6天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"25","vid":"15","uid":"9999","content":"ongoing","num":"0","createtime":"6天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"22","vid":"15","uid":"9999","content":"qqq","num":"0","createtime":"6天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"21","vid":"14","uid":"9999","content":"qq","num":"0","createtime":"6天前","pid":"0","type":"视频","id":"14","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"},{"cid":"17","vid":"15","uid":"9999","content":"嗯","num":"0","createtime":"7天前","pid":"0","type":"图片","id":"15","image":"http://live.shuiqiao.net/default.jpg","catename":"交友1s"}]}
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
             * cid : 34
             * vid : 15
             * uid : 9999
             * content : hogogigicjcjcici
             * num : 0
             * createtime : 3天前
             * pid : 0
             * type : 图片
             * id : 15
             * image : http://live.shuiqiao.net/default.jpg
             * catename : 交友1s
             */

            private String cid;
            private String vid;
            private String uid;
            private String content;
            private String num;
            private String createtime;
            private String pid;
            private String type;
            private String id;
            private String image;
            private String catename;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }
        }
    }
}
