package com.fagawee.mvp.base.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.fagawee.mvp.eventbus.IntentMessage;
import com.fagawee.mvp.manager.EventManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Mr.Tian on 2019/4/30.
 */

public class BaseService extends Service {

    private EventManager eventManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        eventManager=new EventManager(this);
        eventManager.register();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        if (eventManager!=null)
        {
            eventManager.unRegister();
        }
        super.onDestroy();

    }


    public void eventPost(Object obj)
    {
        eventManager.post(obj);
    }

    public void postIntentMessage(Object obj, Class<?> cls)
    {
        IntentMessage intentMessage=new IntentMessage();
        intentMessage.obj=obj;
        intentMessage.cls=cls;
        eventPost(intentMessage);
    }
    public void onDataMessage(Object obj){}

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onIntentMessage(IntentMessage intentMessage)
    {
        if (intentMessage!=null)
        {
            if (intentMessage.cls!=null&&intentMessage.cls.getSimpleName().equals(getClass().getSimpleName()))
            {
                onDataMessage(intentMessage.obj);
            }
            else
            {
                onDataMessage(intentMessage.obj);
            }
        }

    }
}
