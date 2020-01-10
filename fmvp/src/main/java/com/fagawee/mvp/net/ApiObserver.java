package com.fagawee.mvp.net;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;


/**
 * Created by Mr.Tian on 2016/12/26.
 */

public abstract class  ApiObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
        onStart(d);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onErr(e);
    }

    @Override
    public void onComplete() {

    }


    public abstract void onStart(Disposable d);

    public abstract void onSuccess(T data);

    public abstract void onErr(Throwable e);

    public abstract  void onFinish();









}
