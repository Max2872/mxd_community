package com.project.mxd.mxd_community;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mxd on 2018/5/7.
 */

public class OrderDialog extends Dialog {
    protected TextView ensureBtn;
    public OrderDialog(Context context) {
        super(context, R.style.OrderDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        ensureBtn = (TextView) findViewById(R.id.ensureBtn);
    }

    public void setEnsureBtn(View.OnClickListener clickListener) {
        ensureBtn.setOnClickListener(clickListener);
    }
}
