package com.fagawee.mvp.mvp.delegate;

import com.fagawee.mvp.mvp.IMvpPresenter;
import com.fagawee.mvp.mvp.IMvpView;

/**
 * V/P 媒介
 *
 * @param <V>
 * @param <P>
 */
public interface MvpDelegateCallback<V extends IMvpView, P extends IMvpPresenter<V>> {

    /**
     * Gets the presenter.
     */
    P[] getPresenter();

    /**
     * Gets the MvpView for the presenter
     *
     * @return The view associated with the presenter
     */
    V[] getMvpView();

}

