
package com.fagawee.mvp.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Presenter基础实现
 *
 * @param <V>
 */
public class MvpPresenter<V extends IMvpView> implements IMvpPresenter<V> {

    /*View弱引用*/
    private WeakReference<V> viewRef;
    private WeakReference<Activity> activityRef;
    private WeakReference<Fragment> fragmentRef;
    /**
     * 获取view
     *
     * @return
     */
    @UiThread
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * 获取Activity
     * @param <A>
     * @return
     */
    @UiThread
    public <A extends Activity> A getActivity() {
        return viewRef == null ? null : (A)activityRef.get();
    }
    @UiThread
    public <F extends Fragment> F getFragment() {
        return viewRef == null ? null : (F)fragmentRef.get();
    }
    /**
     * 判断View是否已经添加
     *
     * @return
     */
    @UiThread
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * 绑定View
     *
     * @param view
     */
    @UiThread
    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    @Override
    public void attachActivity(@NonNull Activity activity) {
        activityRef=new WeakReference<Activity>(activity);
    }
    @Override
    public void attachFragment(@NonNull Fragment fragment) {
        fragmentRef=new WeakReference<Fragment>(fragment);
    }
    /**
     * 移除View
     */
    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void detachActivity() {
        if (activityRef != null) {
            activityRef.clear();
            activityRef = null;
        }
    }
    @Override
    public void detachFragment() {
        if (fragmentRef != null) {
            fragmentRef.clear();
            fragmentRef = null;
        }
    }
    @Override
    public void destroy() {
    }

}
