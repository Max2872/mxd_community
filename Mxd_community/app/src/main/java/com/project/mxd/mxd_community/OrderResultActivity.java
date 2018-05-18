package com.project.mxd.mxd_community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mxd on 2018/5/7.
 */

public class OrderResultActivity extends AppCompatActivity {
    private ImageView backImage;
    private TextView ensureBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_result);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        ensureBtn = (TextView) findViewById(R.id.ensureBtn);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(OrderResultActivity.this, OrderManagerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
    }
}
