package com.fagawee.fmvp.app.test;

import com.fagawee.fmvp.app.net.Api;
import com.fagawee.fmvp.app.net.response.DeviceResponse;
import com.fagawee.fmvp.app.net.response.DeviceValidateNetBean;
import com.fagawee.mvp.base.core.BasePresenter;

import com.fagawee.mvp.net.ApiObserver;
import com.fagawee.mvp.net.XApi;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mr.Tian on 2019/4/28.
 */

public class TestPresenter extends BasePresenter<TestView> {

    public void loadData() {
        Api.getDeviceService().deviceValidate("7484E1047010","7484E1047010")
                .compose(XApi.getMainScheduler())
                .compose(getRxActivity().bindToLifecycle())
                .subscribe(new ApiObserver<DeviceValidateNetBean>() {
                    @Override
                    public void onStart(Disposable d) {

                    }

                    @Override
                    public void onSuccess(DeviceValidateNetBean data) {

                    }

                    @Override
                    public void onErr(Throwable e) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

}
