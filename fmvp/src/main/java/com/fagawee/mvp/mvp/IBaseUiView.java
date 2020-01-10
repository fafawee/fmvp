package com.fagawee.mvp.mvp;

/**
 * Created by Mr.Tian on 2019/4/28.
 */

public interface IBaseUiView {


    void onCreateView();
    void onDestoryView();
    int getLayoutRes();
    void onDestory();
    void onStart();
    void onResume();
    void onStop();
    void onPause();

}
