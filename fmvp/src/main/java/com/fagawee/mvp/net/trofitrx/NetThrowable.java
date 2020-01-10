package com.fagawee.mvp.net.trofitrx;


import retrofit2.Call;

/**
 * Created by Mr.Tian on 2019/2/21.
 */

public class NetThrowable extends Throwable {

    public NetThrowable(Throwable cause, Call call) {
        super(cause);
        this.call = call;
    }

    Call call;

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
