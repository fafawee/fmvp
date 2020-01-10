package com.fagawee.mvp.base.core;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.fagawee.mvp.dialog.LoadingDialog;
import com.fagawee.mvp.eventbus.IntentMessage;
import com.fagawee.mvp.eventbus.RefreshMessage;
import com.fagawee.mvp.manager.EventManager;
import com.fagawee.mvp.mvp.BaseUiView;
import com.fagawee.mvp.mvp.IMvpPresenter;
import com.fagawee.mvp.mvp.IMvpView;
import com.fagawee.mvp.mvp.MvpAppCompatActivity;
import com.githang.statusbar.StatusBarCompat;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.io.Serializable;


/**
 * Created by Mr.Tian on 2019/4/26.
 */

public abstract class BaseActivity<V extends IMvpView, P extends IMvpPresenter<V>> extends MvpAppCompatActivity<V, P> {


    private LoadingDialog loadingDialog;
    private EventManager eventManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventManager = new EventManager(this);
        eventManager.register();
        BaseUiView view = converView((BaseUiView) getUiView());

        if (view != null) {
            setContentView(view);
        }
        onInit(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventManager != null) {
            eventManager.unRegister();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    protected BaseUiView converView(BaseUiView view) {
        return view;
    }

    protected void onInit(Bundle savedInstanceState) {

    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE, true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE, true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this.getActivity());

        }
        loadingDialog.show();
    }

    public void showLoading(String message) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this.getActivity());

        }
        loadingDialog.setMessage(message);
        loadingDialog.show();


    }

    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void setLigthFullScreen() {
        StatusBarCompat.setTranslucent(getWindow(), true);
        StatusBarCompat.setLightStatusBar(getWindow(), false);
    }

    public void setDarkFullScreen() {
        StatusBarCompat.setTranslucent(getWindow(), true);
        StatusBarCompat.setLightStatusBar(getWindow(), true);
    }

    public boolean isActivityTop() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(getClass().getName());
    }


    public Activity getActivity() {
        return this;
    }


    public void toActivity(Context from, Class<?> target) {
        Intent intent = new Intent(from, target);
        from.startActivity(intent);

    }

    public void toActivity(Class<?> target) {
        Intent intent = new Intent(this, target);
        this.startActivity(intent);

    }

    public static final String Action_Data = "Action_Data";

    public static <T extends Serializable> void toThisActivity(Context from, Class<?> targetClass, T data) {
        Intent intent = new Intent(from, targetClass);
        intent.putExtra(Action_Data, data);
        from.startActivity(intent);
    }

    public <T extends Serializable> T getAction_Data() {
        Intent intent = getIntent();
        try {
            if (intent != null) {
                Object obj = intent.getSerializableExtra(Action_Data);
                return (T) obj;

            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }

    }

    public void eventPost(Object obj) {
        eventManager.post(obj);
    }

    public void postIntentMessage(Object obj, Class<?> cls) {
        IntentMessage intentMessage = new IntentMessage();
        intentMessage.obj = obj;
        intentMessage.cls = cls;
        eventPost(intentMessage);
    }

    public void onDataMessage(Object obj) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onIntentMessage(IntentMessage intentMessage) {
        if (intentMessage != null) {
            if (intentMessage.cls != null && intentMessage.cls.getSimpleName().equals(getClass().getSimpleName())) {
                onDataMessage(intentMessage.obj);
            } else {
                onDataMessage(intentMessage.obj);
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onIntentMessage(RefreshMessage refreshMessage) {
        if (refreshMessage != null) {
            if (refreshMessage.cls != null && refreshMessage.cls.getSimpleName().equals(getClass().getSimpleName())) {
                onRefresh(refreshMessage.action, refreshMessage.obj);
            }

        }

    }

    public void refresh(Class<?> cls, String action, Object obj) {
        RefreshMessage refreshMessage = new RefreshMessage();
        refreshMessage.cls = cls;
        refreshMessage.action = action;
        refreshMessage.obj = obj;
        eventPost(refreshMessage);
    }

    //////////refresh//////////
    protected void onRefresh(String action_Data, Object obj) {

    }








    //View 回调来的数据
    public  void onCallback(int what,Object ...data)
    {

    }
    public <T> T getCallbackData(Object data)
    {
        try {
            return (T)data;
        }
        catch (Exception e)
        {
            return null;
        }

    }

}
