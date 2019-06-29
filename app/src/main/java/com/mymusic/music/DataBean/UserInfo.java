package com.mymusic.music.DataBean;

/**
 * Create By mr.mao in 2019/6/30 1:24
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserInfo {

    /**
     * data : {"list":{"id":"9999","user_login":"123123123","user_nicename":"11","avatar":"http://live.shuiqiao.net/default.jpg","avatar_thumb":"http://live.shuiqiao.net/default_thumb.jpg","sex":"保密","signature":"2","consumption":"0","votestotal":"0","province":"","city":"","coin":"0","score":"0","votes":"0.00","token":"fc8f962765f992f610c96a1d2f10caa6","birthday":"2","issuper":"0","create_time":"2019-06-14 17:52:36","is_vip":"0","lighttime":"0","light":0,"level":"1","level_anchor":"1","shenfen":"普通用户","sexf":"未知","hunlian":"未知","yixiang":"未知","status":"账号异常","vip":{"type":"0"},"liang":{"name":"0"},"follows":"3","fans":"8","guanzhu":"关注"}}
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
         * list : {"id":"9999","user_login":"123123123","user_nicename":"11","avatar":"http://live.shuiqiao.net/default.jpg","avatar_thumb":"http://live.shuiqiao.net/default_thumb.jpg","sex":"保密","signature":"2","consumption":"0","votestotal":"0","province":"","city":"","coin":"0","score":"0","votes":"0.00","token":"fc8f962765f992f610c96a1d2f10caa6","birthday":"2","issuper":"0","create_time":"2019-06-14 17:52:36","is_vip":"0","lighttime":"0","light":0,"level":"1","level_anchor":"1","shenfen":"普通用户","sexf":"未知","hunlian":"未知","yixiang":"未知","status":"账号异常","vip":{"type":"0"},"liang":{"name":"0"},"follows":"3","fans":"8","guanzhu":"关注"}
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
             * id : 9999
             * user_login : 123123123
             * user_nicename : 11
             * avatar : http://live.shuiqiao.net/default.jpg
             * avatar_thumb : http://live.shuiqiao.net/default_thumb.jpg
             * sex : 保密
             * signature : 2
             * consumption : 0
             * votestotal : 0
             * province :
             * city :
             * coin : 0
             * score : 0
             * votes : 0.00
             * token : fc8f962765f992f610c96a1d2f10caa6
             * birthday : 2
             * issuper : 0
             * create_time : 2019-06-14 17:52:36
             * is_vip : 0
             * lighttime : 0
             * light : 0
             * level : 1
             * level_anchor : 1
             * shenfen : 普通用户
             * sexf : 未知
             * hunlian : 未知
             * yixiang : 未知
             * status : 账号异常
             * vip : {"type":"0"}
             * liang : {"name":"0"}
             * follows : 3
             * fans : 8
             * guanzhu : 关注
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
            private String score;
            private String votes;
            private String token;
            private String birthday;
            private String issuper;
            private String create_time;
            private String is_vip;
            private String lighttime;
            private int light;
            private String level;
            private String level_anchor;
            private String shenfen;
            private String sexf;
            private String hunlian;
            private String yixiang;
            private String status;
            private VipBean vip;
            private LiangBean liang;
            private String follows;
            private String fans;
            private String guanzhu;

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

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(String is_vip) {
                this.is_vip = is_vip;
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

            public String getShenfen() {
                return shenfen;
            }

            public void setShenfen(String shenfen) {
                this.shenfen = shenfen;
            }

            public String getSexf() {
                return sexf;
            }

            public void setSexf(String sexf) {
                this.sexf = sexf;
            }

            public String getHunlian() {
                return hunlian;
            }

            public void setHunlian(String hunlian) {
                this.hunlian = hunlian;
            }

            public String getYixiang() {
                return yixiang;
            }

            public void setYixiang(String yixiang) {
                this.yixiang = yixiang;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getGuanzhu() {
                return guanzhu;
            }

            public void setGuanzhu(String guanzhu) {
                this.guanzhu = guanzhu;
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
}
