package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mxd on 2018/3/27.
 */

public class GoodsDetailActivity extends AppCompatActivity {
    private ImageView backImage;
    private ImageView goodsImage;
    private TextView goodsName;
    private TextView goodsPrice;
    private TextView buyBtn;
    private int imageId;
    private String singlePrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        goodsImage = (ImageView)findViewById(R.id.goodsImage);
        goodsName = (TextView) findViewById(R.id.goodsName);
        goodsPrice = (TextView) findViewById(R.id.goodsPrice);
        buyBtn = (TextView) findViewById(R.id.buyBtn);

        Intent getIntent = getIntent();
        imageId = getIntent.getIntExtra("imageId",R.mipmap.ic_launcher);
        singlePrice = getIntent.getStringExtra("price");
        goodsImage.setImageResource(imageId);
        goodsName.setText(getIntent.getStringExtra("name"));
        goodsPrice.setText("￥" + singlePrice);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPreference()) {
                    Intent intent = new Intent();
                    intent.putExtra("imageId",imageId);
                    intent.putExtra("name",goodsName.getText());
                    intent.putExtra("price",singlePrice);
                    intent.setClass(GoodsDetailActivity.this, EditOrderActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    startActivity(intent);
                }else {
                    //跳转登录
                    Intent intent = new Intent();
                    intent.setClass(GoodsDetailActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    intent.putExtra("hasLogin",true);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean getPreference() {
        SharedPreferences preferences = this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        boolean shouldLogin = preferences.getBoolean("shouldLogin",false);
        return shouldLogin;
    }
}
