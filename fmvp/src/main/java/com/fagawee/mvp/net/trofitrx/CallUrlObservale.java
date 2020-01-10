package com.fagawee.mvp.net.trofitrx;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Mr.Tian on 2019/2/21.
 */

public abstract  class CallUrlObservale<T> extends Observable<Response<T>> {

    public String url;
    private  Call<T> originalCall;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Call<T> getOriginalCall() {
        return originalCall;
    }

    public void setOriginalCall(Call<T> originalCall) {
        this.originalCall = originalCall;
    }
}
