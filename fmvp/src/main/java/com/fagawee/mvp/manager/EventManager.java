package com.fagawee.mvp.manager;


import com.fagawee.mvp.event.BusProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Mr.Tian on 2019/5/9.
 */

public class EventManager {


    private Object object;

    public EventManager(Object object) {
        this.object = object;
    }

    public void register() {
        if (object != null) {
            boolean isRegister = BusProvider.getBus().isRegistered(object);
            if (!isRegister) {
                EventBus.getDefault().register(object);
            }
        }

    }

    public void unRegister() {
        if (object != null) {
            boolean isRegister = EventBus.getDefault().isRegistered(object);
            if (isRegister) {
                EventBus.getDefault().unregister(object);
            }
        }
    }

    public void post(Object obj) {
        EventBus.getDefault().post(obj);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void t() {

    }
}
