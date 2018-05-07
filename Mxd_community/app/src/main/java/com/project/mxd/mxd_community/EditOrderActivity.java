package com.project.mxd.mxd_community;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by maohs on 2018/3/29.
 */

public class EditOrderActivity extends AppCompatActivity {
    private ImageView backImage;
    private RelativeLayout recieverInfo;

    private ImageView goodsImage;
    private TextView goodsName;
    private TextView goodsPrice;

    private int goodsCount = 1;
    private float submitPrice;
    private float singlePrice;

    private ImageView orderNumDec;
    private ImageView orderNumAdd;
    private TextView orderNum;

    private TextView orderAmount;
    private TextView orderSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        recieverInfo = (RelativeLayout) findViewById(R.id.recieverInfo);
        orderNumDec = (ImageView) findViewById(R.id.orderNumDec);
        orderNumAdd = (ImageView) findViewById(R.id.orderNumAdd);

        goodsImage = (ImageView) findViewById(R.id.goodsImage);
        goodsName = (TextView) findViewById(R.id.goodsName);
        goodsPrice = (TextView) findViewById(R.id.goodsPrice);
        orderNum = (TextView) findViewById(R.id.orderNum);

        orderAmount = (TextView) findViewById(R.id.orderAmount);
        orderSubmit = (TextView) findViewById(R.id.orderSubmit);

        Intent getIntent = getIntent();
        goodsImage.setImageResource(getIntent.getIntExtra("imageId",R.mipmap.ic_launcher));
        goodsName.setText(getIntent.getStringExtra("name"));
        goodsPrice.setText(getIntent.getStringExtra("price"));
        submitPrice = Float.parseFloat(getIntent.getStringExtra("price"));
        singlePrice = submitPrice;
        orderAmount.setText("￥" + submitPrice + "元");
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recieverInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        orderNumDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsCount > 1) {
                    goodsCount -= 1;
                    submitPrice = goodsCount * singlePrice;
                    orderAmount.setText("￥" + submitPrice + "元");
                    orderNum.setText(goodsCount + "");
                }
            }
        });

        orderNumAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsCount < 99) {
                    goodsCount += 1;
                    submitPrice = goodsCount * singlePrice;
                    orderAmount.setText("￥" + submitPrice + "元");
                    orderNum.setText(goodsCount + "");
                }
            }
        });

        orderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
