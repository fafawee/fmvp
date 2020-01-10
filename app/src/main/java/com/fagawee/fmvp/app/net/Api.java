package com.fagawee.fmvp.app.net;

import com.fagawee.mvp.net.XApi;

/**
 * Created by Mr.Tian on 2020/1/10.
 */

public class Api {

    public static final String API_BASE_URL = "http://api.medrd.com/";

    private static DeviceService deviceService;

    public static DeviceService getDeviceService() {
        if (deviceService == null) {
            synchronized (Api.class) {
                if (deviceService == null) {
                    deviceService = XApi.getInstance().getRetrofit(API_BASE_URL, true).create(DeviceService.class);
                }
            }
        }
        return deviceService;
    }


}
