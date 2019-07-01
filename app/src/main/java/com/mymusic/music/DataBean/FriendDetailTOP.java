package com.mymusic.music.DataBean;

import java.io.Serializable;

/**
 * Create By mr.mao in 2019/6/9 21:17
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendDetailTOP implements Serializable {

    /**
     * data : {"list":{"cid":"1","title":"交友1s","icon":"http://live.shuiqiao.net/default.jpg","description":"这里是描述","tui":"1","name":"交友","official":"0","gnum":"2","tiezi":"16","zhiding":{"id":"24","title":"这里是标题","content":"sdfdsafsafasdf"},"gonggao":{"id":"25","title":"这里是标题","content":"afsfewrwerweqr"},"ulist":{"head1":"","head2":"","head3":"","head4":""}}}
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
        /**
         * list : {"cid":"1","title":"交友1s","icon":"http://live.shuiqiao.net/default.jpg","description":"这里是描述","tui":"1","name":"交友","official":"0","gnum":"2","tiezi":"16","zhiding":{"id":"24","title":"这里是标题","content":"sdfdsafsafasdf"},"gonggao":{"id":"25","title":"这里是标题","content":"afsfewrwerweqr"},"ulist":{"head1":"","head2":"","head3":"","head4":""}}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * cid : 1
             * title : 交友1s
             * icon : http://live.shuiqiao.net/default.jpg
             * description : 这里是描述
             * tui : 1
             * name : 交友
             * official : 0
             * gnum : 2
             * tiezi : 16
             * zhiding : {"id":"24","title":"这里是标题","content":"sdfdsafsafasdf"}
             * gonggao : {"id":"25","title":"这里是标题","content":"afsfewrwerweqr"}
             * ulist : {"head1":"","head2":"","head3":"","head4":""}
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
            private ZhidingBean zhiding;
            private GonggaoBean gonggao;
            private UlistBean ulist;

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

            public ZhidingBean getZhiding() {
                return zhiding;
            }

            public void setZhiding(ZhidingBean zhiding) {
                this.zhiding = zhiding;
            }

            public GonggaoBean getGonggao() {
                return gonggao;
            }

            public void setGonggao(GonggaoBean gonggao) {
                this.gonggao = gonggao;
            }

            public UlistBean getUlist() {
                return ulist;
            }

            public void setUlist(UlistBean ulist) {
                this.ulist = ulist;
            }

            public static class ZhidingBean implements Serializable{
                /**
                 * id : 24
                 * title : 这里是标题
                 * content : sdfdsafsafasdf
                 */

                private String id;
                private String title;
                private String content;

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

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class GonggaoBean implements Serializable{
                /**
                 * id : 25
                 * title : 这里是标题
                 * content : afsfewrwerweqr
                 */

                private String id;
                private String title;
                private String content;

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

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class UlistBean implements Serializable{
                /**
                 * head1 :
                 * head2 :
                 * head3 :
                 * head4 :
                 */

                private String head1;
                private String head2;
                private String head3;
                private String head4;

                public String getHead1() {
                    return head1;
                }

                public void setHead1(String head1) {
                    this.head1 = head1;
                }

                public String getHead2() {
                    return head2;
                }

                public void setHead2(String head2) {
                    this.head2 = head2;
                }

                public String getHead3() {
                    return head3;
                }

                public void setHead3(String head3) {
                    this.head3 = head3;
                }

                public String getHead4() {
                    return head4;
                }

                public void setHead4(String head4) {
                    this.head4 = head4;
                }
            }
        }
    }
}
