package com.project.mxd.mxd_community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by maohs on 2018/3/27.
 */

public class GiftBoxSettingActivity extends AppCompatActivity {
    private ImageView backImage;
    private TextView ensureBtn;
    private TextView resetBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_box_setting);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        ensureBtn = (TextView)findViewById(R.id.ensureBtn);
        resetBtn = (TextView)findViewById(R.id.resetBtn);
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
                intent.setClass(GiftBoxSettingActivity.this, GiftBoxListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
