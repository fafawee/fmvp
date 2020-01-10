package com.fagawee.fmvp.app.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.fagawee.fmvp.app.R;
import com.fagawee.mvp.mvp.BaseUiView;

/**
 * Created by Mr.Tian on 2019/4/28.
 */

public class TestUiView extends BaseUiView {

    private TextView mTest;





    public TestUiView(@NonNull Context context) {
        super(context);
    }

    public TestUiView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestUiView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onCreateView() {
        mTest = findViewById(R.id.test);
        mTest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCallback(1);
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.test_activity_layout;
    }
}
