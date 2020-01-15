package com.fagawee.fmvp.app.test;

import android.util.Log;

import com.fagawee.fmvp.app.net.Api;
import com.fagawee.fmvp.app.net.response.DeviceResponse;
import com.fagawee.fmvp.app.net.response.DeviceValidateNetBean;
import com.fagawee.mvp.base.core.BasePresenter;

import com.fagawee.mvp.log.XLog;
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
                        XLog.d(data.toString());
                    }

                    @Override
                    public void onErr(Throwable e) {
                        XLog.d("error:"+e.getMessage());
                    }

                    @Override
                    public void onFinish() {
                        XLog.d("onFinish");
                    }
                });
    }

}
