package com.fagawee.fmvp.app.test;


import android.os.Bundle;
import android.util.Log;

import com.fagawee.mvp.base.core.BaseActivity;
import com.fagawee.mvp.mvp.IBaseUiView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


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

/*
        MyService service=(MyService)Proxy.newProxyInstance(MyService.class.getClassLoader(),  new Class<?>[]{MyService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name=method.getName();
                String url=(String) args[0];
                if(name.equals("get"))
                {
                    ResModel resModel=new ResModel();
                    resModel.result="get返回值";
                    resModel.url="返回的"+url;
                    return resModel;
                }
                else if(name.equals("post"))
                {
                    ResModel resModel=new ResModel();
                    resModel.result="post返回值";
                    resModel.url="返回的"+url;
                    return resModel;
                }
                else
                {
                    return null;
                }


               // return null;

            }
        });

        ResModel resModel_get=service.get("geturl");
        ResModel resModel_post=service.post("posturl");
        Log.i("Test",resModel_get.result+"   "+resModel_get.url);
        Log.i("Test",resModel_post.result+"   "+resModel_post.url);

*/


    }






    @Override
    public void onCallback(int what, Object... data) {
        super.onCallback(what, data);
        if(what ==1)
        {

        }
    }


}
