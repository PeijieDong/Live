package com.mymusic.music.Callback;

/**
 * Create By mr.mao in 2019/6/4 22:48
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public interface NetCallback<T> {

    void onSucceed(T data);

    void onComplete();

    void onError(Throwable e);
}
