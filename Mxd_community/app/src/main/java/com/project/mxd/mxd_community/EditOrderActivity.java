package com.project.mxd.mxd_community;

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

    private ImageView orderNumDec;
    private ImageView orderNumAdd;

    private TextView orderSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        recieverInfo = (RelativeLayout) findViewById(R.id.recieverInfo);
        orderNumDec = (ImageView) findViewById(R.id.orderNumDec);
        orderNumAdd = (ImageView) findViewById(R.id.orderNumAdd);

        orderSubmit = (TextView) findViewById(R.id.orderSubmit);

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

            }
        });

        orderNumAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        orderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
