package com.fagawee.mvp.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;

/**
 * MVP  根Presenter
 *
 *
 */
public interface IMvpPresenter<V extends IMvpView> {

    /**
     * 将 View 添加到当前 Presenter
     */
    @UiThread
    void attachView(@NonNull V view);
    @UiThread
    void attachActivity(@NonNull Activity activity);
    @UiThread
    void attachFragment(@NonNull Fragment activity);
    /**
     * 将 View 从 Presenter 移除
     */
    @UiThread
    void detachView();
    @UiThread
    void detachActivity();
    @UiThread
    void detachFragment();
    /**
     * 销毁 V 实例
     */
    @UiThread
    void destroy();

}
