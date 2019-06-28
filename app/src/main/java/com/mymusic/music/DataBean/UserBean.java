package com.mymusic.music.DataBean;

/**
 * Create By mr.mao in 2019/6/28 22:19
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserBean {

    /**
     * data : {"id":"9999","user_login":"123123123","user_nicename":"11","avatar":"http://live.shuiqiao.net/default.jpg","avatar_thumb":"http://live.shuiqiao.net/default_thumb.jpg","sex":"0","signature":"2","consumption":"0","votestotal":"0","province":"","city":"","coin":"0","votes":"0.00","token":"c712f54da2e1f129ea4976e173696ed4","birthday":"2","issuper":"0","lighttime":"0","light":0,"level":"1","level_anchor":"1","vip":{"type":"0"},"liang":{"name":"0"},"follows":"3","fans":"8","fabu":"6","shoucang":"13","guanying":"999/999","notice":1}
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
         * id : 9999
         * user_login : 123123123
         * user_nicename : 11
         * avatar : http://live.shuiqiao.net/default.jpg
         * avatar_thumb : http://live.shuiqiao.net/default_thumb.jpg
         * sex : 0
         * signature : 2
         * consumption : 0
         * votestotal : 0
         * province :
         * city :
         * coin : 0
         * votes : 0.00
         * token : c712f54da2e1f129ea4976e173696ed4
         * birthday : 2
         * issuper : 0
         * lighttime : 0
         * light : 0
         * level : 1
         * level_anchor : 1
         * vip : {"type":"0"}
         * liang : {"name":"0"}
         * follows : 3
         * fans : 8
         * fabu : 6
         * shoucang : 13
         * guanying : 999/999
         * notice : 1
         */

        private String id;
        private String user_login;
        private String user_nicename;
        private String avatar;
        private String avatar_thumb;
        private String sex;
        private String signature;
        private String consumption;
        private String votestotal;
        private String province;
        private String city;
        private String coin;
        private String votes;
        private String token;
        private String birthday;
        private String issuper;
        private String lighttime;
        private int light;
        private String level;
        private String level_anchor;
        private VipBean vip;
        private LiangBean liang;
        private String follows;
        private String fans;
        private String fabu;
        private String shoucang;
        private String guanying;
        private int notice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getUser_nicename() {
            return user_nicename;
        }

        public void setUser_nicename(String user_nicename) {
            this.user_nicename = user_nicename;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar_thumb() {
            return avatar_thumb;
        }

        public void setAvatar_thumb(String avatar_thumb) {
            this.avatar_thumb = avatar_thumb;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getConsumption() {
            return consumption;
        }

        public void setConsumption(String consumption) {
            this.consumption = consumption;
        }

        public String getVotestotal() {
            return votestotal;
        }

        public void setVotestotal(String votestotal) {
            this.votestotal = votestotal;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getIssuper() {
            return issuper;
        }

        public void setIssuper(String issuper) {
            this.issuper = issuper;
        }

        public String getLighttime() {
            return lighttime;
        }

        public void setLighttime(String lighttime) {
            this.lighttime = lighttime;
        }

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel_anchor() {
            return level_anchor;
        }

        public void setLevel_anchor(String level_anchor) {
            this.level_anchor = level_anchor;
        }

        public VipBean getVip() {
            return vip;
        }

        public void setVip(VipBean vip) {
            this.vip = vip;
        }

        public LiangBean getLiang() {
            return liang;
        }

        public void setLiang(LiangBean liang) {
            this.liang = liang;
        }

        public String getFollows() {
            return follows;
        }

        public void setFollows(String follows) {
            this.follows = follows;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getFabu() {
            return fabu;
        }

        public void setFabu(String fabu) {
            this.fabu = fabu;
        }

        public String getShoucang() {
            return shoucang;
        }

        public void setShoucang(String shoucang) {
            this.shoucang = shoucang;
        }

        public String getGuanying() {
            return guanying;
        }

        public void setGuanying(String guanying) {
            this.guanying = guanying;
        }

        public int getNotice() {
            return notice;
        }

        public void setNotice(int notice) {
            this.notice = notice;
        }

        public static class VipBean {
            /**
             * type : 0
             */

            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class LiangBean {
            /**
             * name : 0
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
