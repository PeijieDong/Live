package com.mymusic.music.DataBean;

/**
 * Create By MR.D
 * 2019/7/12
 * USE:
 **/
public class AdvEntity extends ResponseEntity<AdvEntity>{
    public ListBean list;

    public static class ListBean {
        public String link;
        public String imgurl;
        public String title;

    }

}
