package com.fagawee.mvp.imageloader;

import android.graphics.drawable.Drawable;

/**
 * Created by Mr.Tian on 2016/12/21.
 */

public abstract class LoadCallback {
    void onLoadFailed() {}

    public abstract void onLoadReady(Drawable drawable);

    void onLoadCanceled() {}
}
