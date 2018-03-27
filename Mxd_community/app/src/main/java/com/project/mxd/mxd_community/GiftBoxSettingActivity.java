package com.project.mxd.mxd_community;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by maohs on 2018/3/27.
 */

public class GiftBoxSettingActivity extends AppCompatActivity {
    private ImageView backImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_box_setting);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
