package com.fagawee.mvp.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mr.Tian on 2016/12/22.
 */

public class BusProvider {

    private static EventBusImpl bus;

    public static EventBusImpl getBus() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = new EventBusImpl();
                }
            }
        }
        return bus;
    }

}
