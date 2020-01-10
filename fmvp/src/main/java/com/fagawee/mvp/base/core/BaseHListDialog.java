package com.fagawee.mvp.base.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;

/**
 * @author Panner
 * @version 2019-09-18 10:59
 */
public abstract class BaseHListDialog extends NormalListDialog {
    public View root;

    public BaseHListDialog(Context context, ArrayList<DialogMenuItem> baseItems) {
        super(context, baseItems);
    }

    public BaseHListDialog(Context context, String[] items) {
        super(context, items);
    }

    public BaseHListDialog(Context context, BaseAdapter adapter) {
        super(context, adapter);
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
