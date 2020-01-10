/*
 * Copyright (C) 2016 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fagawee.mvp.net.trofitrx;
import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Response;

final class BodyObservable<T> extends Observable<T> {
  private  final CallUrlObservale<T> upstream;

  BodyObservable(CallUrlObservale<T> upstream) {
    this.upstream = upstream;
  }

  @Override
  protected void subscribeActual(Observer<? super T> observer) {

    upstream.subscribe(new BodyObserver<T>(observer,upstream.getUrl(),upstream));
  }

  private static class BodyObserver<R> implements Observer<Response<R>> {
    private final Observer<? super R> observer;
    private boolean terminated;
    private CallUrlObservale<R> upstream;
    private String url;
    BodyObserver(Observer<? super R> observer, String url, CallUrlObservale<R> upstream) {
      this.observer = observer;
      this.url = url;
      this.upstream = upstream;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
      observer.onSubscribe(disposable);
    }

    @Override
    public void onNext(Response<R> response) {
      if (response.isSuccessful()) {
        R r=response.body();

          Field field= null;
          try {
            field = r.getClass().getField("BaseRequestUrl");
            field.set(r,url);
            observer.onNext(r);
          } catch (Exception e) {
            observer.onNext(response.body());
          }


      } else {
        terminated = true;
        Throwable t = new HttpException(response);
        try {

          NetThrowable netThrowable=new NetThrowable(t,upstream.getOriginalCall());
          observer.onError(netThrowable);
        } catch (Throwable inner) {
          Exceptions.throwIfFatal(inner);
          RxJavaPlugins.onError(new CompositeException(t, inner));
        }
      }
    }

    @Override
    public void onComplete() {
      if (!terminated) {
        observer.onComplete();
      }
    }

    @Override
    public void onError(Throwable throwable) {
      if (!terminated) {
        NetThrowable netThrowable=new NetThrowable(throwable,upstream.getOriginalCall());
        observer.onError(netThrowable);
      } else {
        // This should never happen! onNext handles and forwards errors automatically.
        Throwable broken = new AssertionError(
            "This should never happen! Report as a bug with the full stacktrace.");
        //noinspection UnnecessaryInitCause Two-arg AssertionError constructor is 1.7+ only.
        broken.initCause(throwable);
        RxJavaPlugins.onError(broken);
      }
    }
  }
}
