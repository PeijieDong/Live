package com.mymusic.music.DataBean.base;

/**
 * Create By mr.mao in 2019/6/3 21:40
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 * Use for:跟服务端商定接口规则
 **/
public class baseResult<T> {

    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
