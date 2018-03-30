package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by maohs on 2018/3/29.
 */

public class CustomGoodsDetailActivity extends AppCompatActivity {
    private ImageView backImage;
    private TextView cartBtn;
    private TextView cartHint;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_goods_detail);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        cartBtn = (TextView) findViewById(R.id.cartBtn);
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
                if (getPreference()) {
                    Intent intent = new Intent();
                    intent.setClass(CustomGoodsDetailActivity.this, MainTabbarActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    intent.putExtra("toCart",true);
                    startActivity(intent);
                }else {
                    //跳转登录
                    Intent intent = new Intent();
                    intent.setClass(CustomGoodsDetailActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    intent.putExtra("hasLogin",true);
                    startActivity(intent);
                }
            }
        });
        cartHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPreference()) {
                    customToast("添加成功",2);
                }else {
                    //跳转登录
                    Intent intent = new Intent();
                    intent.setClass(CustomGoodsDetailActivity.this, LoginActivity.class);
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
    private void customToast(String string,int showTime) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_custom,(ViewGroup) findViewById(R.id.goodsDetailToat));
        TextView toastMsg = (TextView)view.findViewById(R.id.toastMsg);
        toastMsg.setText(string);
        Toast toast = new Toast(CustomGoodsDetailActivity.this);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,200);
        toast.setDuration(showTime);
        toast.setView(view);
        toast.show();
    }
}
