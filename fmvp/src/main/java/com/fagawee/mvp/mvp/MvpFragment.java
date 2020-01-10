package com.fagawee.mvp.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.fagawee.mvp.mvp.delegate.FragmentMvpDelegate;
import com.fagawee.mvp.mvp.delegate.FragmentMvpDelegateImpl;
import com.fagawee.mvp.mvp.delegate.MvpDelegateCallback;
import com.trello.rxlifecycle2.components.support.RxFragment;


/**
 * MVPFragment
 * 备注:
 * 1.XXFragment 继承 MvpFragment,当页面存在 Presenter 时，具体 Fragment 需要调用 setPresenter(P... presenter)
 * 2.由于此框架集合了 RxLifecycle 因此本 Fragment 继承自 RxFragment (开发者也可以直接继承 Fragment)
 * 3.支持一个 Fragment 存在多个 Presenter
 *
 * @param <V>
 * @param <P>
 */
public abstract class MvpFragment<V extends IMvpView, P extends IMvpPresenter<V>> extends RxFragment implements IMvpView, MvpDelegateCallback<V, P> {

    protected FragmentMvpDelegate mvpDelegate;

    /**
     * 获取 Presenter 数组
     */
    protected abstract P[] getPresenterArray();

    protected abstract IBaseUiView buildUiView();

    public <T extends IBaseUiView> T getUiView() {
        return (T) (mvpDelegate.getUiView());
    }


    @Override
    public P[] getPresenter() {
        return getPresenterArray();
    }

    @Override
    public V[] getMvpView() {
        V[] view = null;
        P[] pArray = getPresenter();
        if (pArray != null) {
            view = (V[]) new IMvpView[pArray.length];
            for (int i = 0; i < pArray.length; i++) {
                view[i] = (V) this;
            }
        }
        return view;
    }

    @NonNull
    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new FragmentMvpDelegateImpl(this, this);
            mvpDelegate.setBaseUIView(buildUiView());
        }
        return mvpDelegate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getMvpDelegate().onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getMvpDelegate().onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }
}


