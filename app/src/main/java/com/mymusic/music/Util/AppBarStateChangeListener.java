package com.mymusic.music.Util;

import android.support.design.widget.AppBarLayout;
import android.util.Log;

/**
 * Create By mr.mao in 2019/6/7 17:37
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
