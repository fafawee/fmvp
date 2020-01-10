package com.fagawee.fmvp.app;

import com.blankj.utilcode.util.Utils;
import com.fagawee.mvp.BaseApp;
import com.fagawee.mvp.net.NetProvider;
import com.fagawee.mvp.net.RequestHandler;
import com.fagawee.mvp.net.XApi;
import com.fagawee.netwindow.NetInterpoter;
import com.fagawee.netwindow.NetWindow;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Mr.Tian on 2020/1/9.
 */

public class MyApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initNet();
        initNet();
    }


    private void initNet()
    {
        NetWindow.init(this);
        XApi.registerProvider(new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                NetInterpoter interpoter=new NetInterpoter();
                return new Interceptor[]{interpoter};
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }


            @Override
            public boolean dispatchProgressEnable() {
                return false;
            }
        });

    }
}
