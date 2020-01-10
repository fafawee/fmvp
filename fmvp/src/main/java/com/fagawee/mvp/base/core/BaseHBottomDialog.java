package com.fagawee.mvp.base.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;

/**
 * Created by Mr.Tian on 2019/5/10.
 */

public abstract class BaseHBottomDialog extends BottomBaseDialog<BaseHBottomDialog> {


    public View root;

    public BaseHBottomDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {

        root = View.inflate(getContext(), layoutRes(), null);

        initView();
        return root;
    }

    @Override
    public void setUiBeforShow() {

    }

    abstract public int layoutRes();

    abstract public void initView();

    @Override
    public <T extends View> T findViewById(int id) {
        return root.findViewById(id);
    }

    @Override
    public void show() {

        super.show();
    }

    public int mm2px(int mm) {
        float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, getContext().getResources().getDisplayMetrics());
        return (int) value;
    }

    public boolean isEmpty(String src) {
        return TextUtils.isEmpty(src);
    }

    public boolean isEmpty(TextView textView) {
        String src = textView.getText().toString();
        return TextUtils.isEmpty(src);
    }


}
