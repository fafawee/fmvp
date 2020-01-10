package com.fagawee.mvp.base.core;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;


import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;


/**
 * Created by Mr.Tian on 2019/4/26.
 */

public class BaseApp extends Application {


    private static  BaseApp instance=null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        MultiDex.install(this);

        Utils.init(this);
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM);



    }
    public static  BaseApp getInstance()
    {
        return instance;
    }

}
