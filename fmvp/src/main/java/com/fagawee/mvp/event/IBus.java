package com.fagawee.mvp.event;

import com.fagawee.mvp.eventbus.EventMessage;

/**
 * Created by Mr.Tian on 2016/12/22.
 */

public interface IBus {

    boolean isRegistered(Object object);

    void register(Object object);

    void unregister(Object object);

    void post(EventMessage event);

    void postSticky(EventMessage event);





}
