package com.fagawee.mvp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fagawee.mvp.R;


/**
 * Created by Mr.Tian on 2019/6/4.
 */

public class LoadingDialog extends Dialog {




    private String message;
    private float dimAmount;
    private ProgressBar mProgress;
    private TextView mMessageNormal;



    public LoadingDialog(Context context) {
        super(context, R.style.loadindialog);
        setDialogTheme();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog_layout);
        mProgress = findViewById(R.id.progress);
        mMessageNormal = findViewById(R.id.message_normal_tv);


    }
    /** set dialog theme(设置对话框主题) */
    private void setDialogTheme() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// android:windowNoTitle
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// android:windowBackground
    }




    public LoadingDialog setMessage(String message) {
        this.message = message;

        if (mMessageNormal!=null)
        {

            mMessageNormal.setText(message);
        }

        return this;
    }
}
