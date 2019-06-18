package com.mymusic.music.DataBean;

/**
 * Create By MR.D
 * 2019/6/18
 * USE:
 **/
public class User {
    /**
     * list : {"id":"1","user_login":"admin","user_nicename":"admin","user_email":"admin@qq.com","user_url":"http://livenew.yunbaozhibo.com","avatar":"","avatar_thumb":"/default_thumb.jpg","sex":"0","birthday":"","signature":"","last_login_ip":"117.22.216.203","last_login_time":"2019-06-17 08:27:14","create_time":"2016-03-07 08:47:52","user_activation_key":"","user_status":"1","score":"0","user_type":"1","coin":"0","mobile":"","token":"428fd9335015b676bb869218f449d5bd","expiretime":"1524440444","weixin":"","consumption":"0","votes":"0.00","votestotal":"0","province":"","city":"","isrecommend":"0","openid":"","login_type":"phone","iszombie":"0","isrecord":"0","iszombiep":"0","issuper":"0","ishot":"1","isauth":"0","bonus_day":"0","bonus_time":"0","contacts_name":"","contacts_mobile":"","code":"","divide_family":"-1","goodnum":"0","is_vip":"0","overtime":"0","money":"","getnotice":"1","sysnotice":"1","feednotice":"1","tongzhinotice":"1","level":"1","level_anchor":"1"}
     * msg : 注册成功
     * status : 1
     * referer :
     * state : 1
     */

    private ListBean list;
    private String msg;
    private int status;
    private String referer;
    private String state;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public static class ListBean {
        /**
         * id : 1
         * user_login : admin
         * user_nicename : admin
         * user_email : admin@qq.com
         * user_url : http://livenew.yunbaozhibo.com
         * avatar :
         * avatar_thumb : /default_thumb.jpg
         * sex : 0
         * birthday :
         * signature :
         * last_login_ip : 117.22.216.203
         * last_login_time : 2019-06-17 08:27:14
         * create_time : 2016-03-07 08:47:52
         * user_activation_key :
         * user_status : 1
         * score : 0
         * user_type : 1
         * coin : 0
         * mobile :
         * token : 428fd9335015b676bb869218f449d5bd
         * expiretime : 1524440444
         * weixin :
         * consumption : 0
         * votes : 0.00
         * votestotal : 0
         * province :
         * city :
         * isrecommend : 0
         * openid :
         * login_type : phone
         * iszombie : 0
         * isrecord : 0
         * iszombiep : 0
         * issuper : 0
         * ishot : 1
         * isauth : 0
         * bonus_day : 0
         * bonus_time : 0
         * contacts_name :
         * contacts_mobile :
         * code :
         * divide_family : -1
         * goodnum : 0
         * is_vip : 0
         * overtime : 0
         * money :
         * getnotice : 1
         * sysnotice : 1
         * feednotice : 1
         * tongzhinotice : 1
         * level : 1
         * level_anchor : 1
         */

        private String id;
        private String user_login;
        private String user_nicename;
        private String user_email;
        private String user_url;
        private String avatar;
        private String avatar_thumb;
        private String sex;
        private String birthday;
        private String signature;
        private String last_login_ip;
        private String last_login_time;
        private String create_time;
        private String user_activation_key;
        private String user_status;
        private String score;
        private String user_type;
        private String coin;
        private String mobile;
        private String token;
        private String expiretime;
        private String weixin;
        private String consumption;
        private String votes;
        private String votestotal;
        private String province;
        private String city;
        private String isrecommend;
        private String openid;
        private String login_type;
        private String iszombie;
        private String isrecord;
        private String iszombiep;
        private String issuper;
        private String ishot;
        private String isauth;
        private String bonus_day;
        private String bonus_time;
        private String contacts_name;
        private String contacts_mobile;
        private String code;
        private String divide_family;
        private String goodnum;
        private String is_vip;
        private String overtime;
        private String money;
        private String getnotice;
        private String sysnotice;
        private String feednotice;
        private String tongzhinotice;
        private String level;
        private String level_anchor;

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

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_url() {
            return user_url;
        }

        public void setUser_url(String user_url) {
            this.user_url = user_url;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUser_activation_key() {
            return user_activation_key;
        }

        public void setUser_activation_key(String user_activation_key) {
            this.user_activation_key = user_activation_key;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(String expiretime) {
            this.expiretime = expiretime;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getConsumption() {
            return consumption;
        }

        public void setConsumption(String consumption) {
            this.consumption = consumption;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
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

        public String getIsrecommend() {
            return isrecommend;
        }

        public void setIsrecommend(String isrecommend) {
            this.isrecommend = isrecommend;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getLogin_type() {
            return login_type;
        }

        public void setLogin_type(String login_type) {
            this.login_type = login_type;
        }

        public String getIszombie() {
            return iszombie;
        }

        public void setIszombie(String iszombie) {
            this.iszombie = iszombie;
        }

        public String getIsrecord() {
            return isrecord;
        }

        public void setIsrecord(String isrecord) {
            this.isrecord = isrecord;
        }

        public String getIszombiep() {
            return iszombiep;
        }

        public void setIszombiep(String iszombiep) {
            this.iszombiep = iszombiep;
        }

        public String getIssuper() {
            return issuper;
        }

        public void setIssuper(String issuper) {
            this.issuper = issuper;
        }

        public String getIshot() {
            return ishot;
        }

        public void setIshot(String ishot) {
            this.ishot = ishot;
        }

        public String getIsauth() {
            return isauth;
        }

        public void setIsauth(String isauth) {
            this.isauth = isauth;
        }

        public String getBonus_day() {
            return bonus_day;
        }

        public void setBonus_day(String bonus_day) {
            this.bonus_day = bonus_day;
        }

        public String getBonus_time() {
            return bonus_time;
        }

        public void setBonus_time(String bonus_time) {
            this.bonus_time = bonus_time;
        }

        public String getContacts_name() {
            return contacts_name;
        }

        public void setContacts_name(String contacts_name) {
            this.contacts_name = contacts_name;
        }

        public String getContacts_mobile() {
            return contacts_mobile;
        }

        public void setContacts_mobile(String contacts_mobile) {
            this.contacts_mobile = contacts_mobile;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDivide_family() {
            return divide_family;
        }

        public void setDivide_family(String divide_family) {
            this.divide_family = divide_family;
        }

        public String getGoodnum() {
            return goodnum;
        }

        public void setGoodnum(String goodnum) {
            this.goodnum = goodnum;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getOvertime() {
            return overtime;
        }

        public void setOvertime(String overtime) {
            this.overtime = overtime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getGetnotice() {
            return getnotice;
        }

        public void setGetnotice(String getnotice) {
            this.getnotice = getnotice;
        }

        public String getSysnotice() {
            return sysnotice;
        }

        public void setSysnotice(String sysnotice) {
            this.sysnotice = sysnotice;
        }

        public String getFeednotice() {
            return feednotice;
        }

        public void setFeednotice(String feednotice) {
            this.feednotice = feednotice;
        }

        public String getTongzhinotice() {
            return tongzhinotice;
        }

        public void setTongzhinotice(String tongzhinotice) {
            this.tongzhinotice = tongzhinotice;
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
    }
}
