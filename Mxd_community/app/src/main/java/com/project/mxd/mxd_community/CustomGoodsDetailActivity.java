package com.project.mxd.mxd_community;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by maohs on 2018/3/29.
 */

public class CustomGoodsDetailActivity extends AppCompatActivity {
    private ImageView backImage;
    private View cartBtn;
    private TextView cartHint;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_goods_detail);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        cartBtn = findViewById(R.id.cartBtn);
        cartHint = (TextView) findViewById(R.id.cartHint);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cartHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
