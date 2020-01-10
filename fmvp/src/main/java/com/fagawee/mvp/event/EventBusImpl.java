package com.fagawee.mvp.event;

import com.fagawee.mvp.eventbus.EventMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mr.Tian on 2020/1/9.
 */

public class EventBusImpl implements IBus {
    @Override
    public boolean isRegistered(Object object) {
      return   EventBus.getDefault().isRegistered(object);
    }

    @Override
    public void register(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    @Override
    public void post(EventMessage event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(EventMessage event) {

    }
}
