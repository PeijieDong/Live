package com.mymusic.music.DataBean;

/**
 * Create By MR.D
 * 2019/7/10
 * USE:
 **/
public class LevelDate {

    /**
     * data : {"list":{"nowlevel":"1","nowscore":0,"nextlevel":"2","nextscore":"15000","tonext":15000,"view":1,"quanzi":1,"comment":0,"fatie":0,"down":0}}
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
        /**
         * list : {"nowlevel":"1","nowscore":0,"nextlevel":"2","nextscore":"15000","tonext":15000,"view":1,"quanzi":1,"comment":0,"fatie":0,"down":0}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * nowlevel : 1
             * nowscore : 0
             * nextlevel : 2
             * nextscore : 15000
             * tonext : 15000
             * view : 1
             * quanzi : 1
             * comment : 0
             * fatie : 0
             * down : 0
             */

            private String nowlevel;
            private int nowscore;
            private String nextlevel;
            private String nextscore;
            private int tonext;
            private int view;
            private int quanzi;
            private int comment;
            private int fatie;
            private int down;

            public String getNowlevel() {
                return nowlevel;
            }

            public void setNowlevel(String nowlevel) {
                this.nowlevel = nowlevel;
            }

            public int getNowscore() {
                return nowscore;
            }

            public void setNowscore(int nowscore) {
                this.nowscore = nowscore;
            }

            public String getNextlevel() {
                return nextlevel;
            }

            public void setNextlevel(String nextlevel) {
                this.nextlevel = nextlevel;
            }

            public String getNextscore() {
                return nextscore;
            }

            public void setNextscore(String nextscore) {
                this.nextscore = nextscore;
            }

            public int getTonext() {
                return tonext;
            }

            public void setTonext(int tonext) {
                this.tonext = tonext;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public int getQuanzi() {
                return quanzi;
            }

            public void setQuanzi(int quanzi) {
                this.quanzi = quanzi;
            }

            public int getComment() {
                return comment;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public int getFatie() {
                return fatie;
            }

            public void setFatie(int fatie) {
                this.fatie = fatie;
            }

            public int getDown() {
                return down;
            }

            public void setDown(int down) {
                this.down = down;
            }
        }
    }
}
