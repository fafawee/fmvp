package com.fagawee.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fagawee.mvp.base.core.BaseActivity;


/**
 * Created by Mr.Tian on 2019/4/28.
 */

public abstract class BaseUiView extends FrameLayout implements IBaseUiView {
    private boolean isHaveInit = false;

    public BaseUiView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseUiView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseUiView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        if (isHaveInit) {
            return;
        }
        LayoutInflater factory = LayoutInflater.from(getContext());
        if (getLayoutRes() == 0) {
            throw new RuntimeException("please set layout resId");
        }
        factory.inflate(getLayoutRes(), this, true);

        onCreateView();
        isHaveInit = true;
    }


    @Override
    public void onDestory() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestoryView() {

    }

    public int mm2px(int mm) {
        float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, getContext().getResources().getDisplayMetrics());
        return (int) value;
    }

    public float mm2pxfloat(int mm) {
        float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, getContext().getResources().getDisplayMetrics());
        return value;
    }


    public <T extends Activity> T getActivity() {
        if (getContext() instanceof Activity) {
            return (T) getContext();
        } else {
            return null;
        }

    }

    public FragmentActivity getFragmentActivity() {
        return (FragmentActivity) getContext();
    }

    public <T extends BaseActivity> T getBaseActivity() {
        if (getContext() instanceof BaseActivity) {
            return (T) getContext();
        } else {
            return null;
        }

    }

    public void toActivity(Class<?> target) {
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, target);
            activity.startActivity(intent);
        }

    }

    public boolean isEmpty(String src) {
        return TextUtils.isEmpty(src);
    }

    public boolean isEmpty(TextView textView) {
        String src = textView.getText().toString();
        return TextUtils.isEmpty(src);
    }

    public void setTextEmpty(TextView... textEmpty) {
        if (textEmpty == null) return;
        for (int i = 0; i < textEmpty.length; i++) {
            if (textEmpty[i] == null) continue;

            textEmpty[i].setText("");
        }

    }


    //数据回调给Activcity
    public void registerCallback(int what,Object ...data)
    {
        BaseActivity baseActivity=getBaseActivity();
        if(baseActivity!=null&&data!=null&&data.length>0)
        {
            baseActivity.onCallback(what,data);
        }
    }

}
