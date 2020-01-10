package com.fagawee.mvp.base.core;


import com.fagawee.mvp.dialog.LoadingDialog;
import com.fagawee.mvp.mvp.IMvpView;
import com.fagawee.mvp.mvp.MvpPresenter;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by Mr.Tian on 2019/4/28.
 */

public class BasePresenter<V extends IMvpView> extends MvpPresenter<V> {

    private LoadingDialog loadingDialog;



    public void showLoading()
    {
        if (loadingDialog==null)
        {
            loadingDialog=new LoadingDialog(this.getActivity());

        }
        loadingDialog.show();
    }
    public void showLoading(String message)
    {
        if (loadingDialog==null)
        {
            loadingDialog=new LoadingDialog(this.getActivity());

        }
        loadingDialog.setMessage(message);
        loadingDialog.show();
    }
    public void dismissLoading()
    {
        if (loadingDialog!=null&&loadingDialog.isShowing())
        {
            loadingDialog.dismiss();
        }
    }


    public BaseActivity getBaseActivity()
    {
        BaseActivity baseActivity=getActivity();

        return baseActivity;
    }
    public RxAppCompatActivity getRxActivity()
    {
        RxAppCompatActivity rxActivity=getActivity();
        return rxActivity;
    }
}
