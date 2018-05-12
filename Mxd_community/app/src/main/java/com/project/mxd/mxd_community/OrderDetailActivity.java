package com.project.mxd.mxd_community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by maohs on 2018/3/21.
 */

public class OrderDetailActivity extends AppCompatActivity {
    private ImageView backImage;
    private TextView order_num;

    private TextView receiver_name;
    private TextView receiver_phone;
    private TextView receiver_address;

    private ImageView goodsImage;
    private TextView goodsName;
    private TextView goodsPrice;

    private String goodsImageId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);
        backImage = (ImageView)findViewById(R.id.top_bar_back);

        order_num = (TextView)findViewById(R.id.order_num);

        receiver_name = (TextView)findViewById(R.id.receiver_name);
        receiver_phone = (TextView)findViewById(R.id.receiver_phone);
        receiver_address = (TextView)findViewById(R.id.receiver_address);

        goodsImage = (ImageView)findViewById(R.id.goodsImage);
        goodsName = (TextView)findViewById(R.id.goodsName);
        goodsPrice = (TextView)findViewById(R.id.goodsPrice);

        Intent getIntent = getIntent();
        goodsImageId = getIntent.getStringExtra("goodsImageId");
        order_num.setText("订单编号：" + goodsImageId);

        receiver_name.setText("收件人：" + getIntent.getStringExtra("recieverName"));
        receiver_phone.setText("联系电话：" + getIntent.getStringExtra("recieverPhone"));
        receiver_address.setText("地址：" + getIntent.getStringExtra("recieverAddress"));

        goodsImage.setImageResource(Integer.parseInt(goodsImageId));
        goodsName.setText(getIntent.getStringExtra("goodsName"));
        goodsPrice.setText(getIntent.getStringExtra("goodsPrice"));


        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
