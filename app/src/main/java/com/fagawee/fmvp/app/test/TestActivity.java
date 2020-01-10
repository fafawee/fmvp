package com.fagawee.fmvp.app.test;


import android.os.Bundle;

import com.fagawee.mvp.base.core.BaseActivity;
import com.fagawee.mvp.mvp.IBaseUiView;


public class TestActivity extends BaseActivity<TestView, TestPresenter> implements TestView {







    private TestPresenter presenter = new TestPresenter();
    private TestUiView uiView;


    @Override
    protected TestPresenter[] getPresenterArray() {
        return new TestPresenter[]{presenter};
    }

    @Override
    protected IBaseUiView buildUiView() {
        uiView = new TestUiView(this);
        return uiView;
    }

    @Override
    protected void onInit(Bundle savedInstanceState) {
        super.onInit(savedInstanceState);
        presenter.loadData();
    }


    @Override
    public void onCallback(int what, Object... data) {
        super.onCallback(what, data);
        if(what ==1)
        {

        }
    }
}
