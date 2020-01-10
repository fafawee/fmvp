package com.fagawee.mvp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;
import com.wanjian.cockroach.Cockroach;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;


/**
 * Created by Mr.Tian on 2019/4/26.
 */

public class BaseApp extends Application {


    private static BaseApp instance=null;

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
    public static BaseApp getInstance()
    {
        return instance;
    }




    public void doAppException() {
        Cockroach.install(new Cockroach.ExceptionHandler() {

            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException

            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Writer writer = new StringWriter();
                            PrintWriter printWriter = new PrintWriter(writer);
                            throwable.printStackTrace(printWriter);
                            Throwable cause = throwable.getCause();
                            while (cause != null) {
                                cause.printStackTrace(printWriter);
                                cause = cause.getCause();
                            }
                            printWriter.close();
                            String result = writer.toString();
                            //ExceptionIntentObj exceptionIntentObj = new ExceptionIntentObj();
                            //exceptionIntentObj.exception = result;
                            //ExceptionActivity.toThisActivity(ActivityUtils.getTopActivity(), ExceptionActivity.class, exceptionIntentObj);


                        } catch (Throwable e) {

                        }
                    }
                });
            }
        });
    }

}
