package com.fagawee.mvp.mvp.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.fagawee.mvp.mvp.IBaseUiView;
import com.fagawee.mvp.mvp.IMvpPresenter;
import com.fagawee.mvp.mvp.IMvpView;


/**
 * Fragment 媒介
 * 备注:主要是连接 Fragment 的生命周期与 Presenter 实现特定生命周期绑定与解除 V
 *
 *
 */
public class FragmentMvpDelegateImpl<V extends IMvpView, P extends IMvpPresenter<V>> implements FragmentMvpDelegate {

    protected IBaseUiView iBaseUiView;
    /**
     * Fragment
     */
    protected Fragment fragment;

    /**
     * V & P
     */
    private MvpDelegateCallback<V, P> delegateCallback;

    public FragmentMvpDelegateImpl(Fragment fragment, MvpDelegateCallback<V, P> delegateCallback) {
        if (fragment == null) {
            throw new NullPointerException("Fragment is null!");
        }
        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.fragment = fragment;
        this.delegateCallback = delegateCallback;
    }

    /**
     * 是否保留V&P实例
     *
     * @return
     */
    private static boolean retainVPInstance(Activity activity, Fragment fragment) {
        if (activity.isChangingConfigurations()) {
            return false;
        }
        if (activity.isFinishing()) {
            return false;
        }
        return !fragment.isRemoving();
    }


    /**
     * 获取Activity
     *
     * @return
     */
    private Activity getActivity() {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new NullPointerException("Activity returned by Fragment.getActivity() is null. Fragment is " + fragment);
        }
        return activity;
    }


    @Override
    public void setBaseUIView(IBaseUiView iBaseUiView) {
        this.iBaseUiView=iBaseUiView;
    }

    @Override
    public IBaseUiView getUiView() {
        return  this.iBaseUiView;
    }

    @Override
    public void onCreate(Bundle saved) {

    }

    @Override
    public void onDestroy() {
        Activity activity = getActivity();
        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            P presenter;
            for (int i = 0; i < pArray.length; i++) {
                presenter = pArray[i];
                if (presenter != null) {
                    if (!retainVPInstance(activity, fragment)) {
                        //销毁 V & P 实例
                        presenter.destroy();
                    }
                }
            }
        }
        if (iBaseUiView!=null)
        {
            iBaseUiView.onDestory();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            V[] vArray = delegateCallback.getMvpView();
            P p;
            V v;
            for (int i = 0; i < pArray.length; i++) {
                p = pArray[i];
                v = vArray[i];
                if (p != null && v != null) {
                    //关联view
                    p.attachView(v);
                    p.attachFragment(fragment);
                }
            }
        }
        if (iBaseUiView!=null)
        {
            //iBaseUiView.onCreateView();
        }
    }

    @Override
    public void onDestroyView() {
        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            P presenter;
            for (int i = 0; i < pArray.length; i++) {
                presenter = pArray[i];
                if (presenter != null) {
                    //解除View
                    presenter.detachView();
                    presenter.detachFragment();
                }
            }
        }
        if (iBaseUiView!=null)
        {
            iBaseUiView.onDestoryView();
        }
    }

    @Override
    public void onPause() {
        if (iBaseUiView!=null)
        {
            iBaseUiView.onPause();
        }
    }

    @Override
    public void onResume() {
        if (iBaseUiView!=null)
        {
            iBaseUiView.onResume();
        }
    }

    @Override
    public void onStart() {
        if (iBaseUiView!=null)
        {
            iBaseUiView.onStart();
        }
    }

    @Override
    public void onStop() {
        if (iBaseUiView!=null)
        {
            iBaseUiView.onStop();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
