package com.fagawee.mvp.base.core;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

import com.flyco.dialog.widget.popup.base.BasePopup;

/**
 * Created by Mr.Tian on 2019/5/23.
 */

public abstract  class BaseHPop extends BasePopup<BaseHPop> {
    public View root;
    abstract  public int layoutRes();

    abstract public void initView();
    public BaseHPop(Context context) {

        super(context);
    }

    @Override
    public View onCreateView() {
        initView();
        return super.onCreateView();
    }

    @Override
    public View onCreatePopupView() {

        root= View.inflate(getContext(),layoutRes(),null);


        return root;
    }

    @Override
    public void setUiBeforShow() {

    }
    @Override
    public <T extends View> T findViewById(int id) {
        return root.findViewById(id);
    }


    public int mm2px(int mm)
    {
        float value= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,mm,getContext().getResources().getDisplayMetrics());
        return (int) value;
    }
}
