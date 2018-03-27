package com.project.mxd.mxd_community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maohs on 2018/3/21.
 */

public class OrderServiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView backImage;
    private Spinner spin_one;
    private Spinner spin_two;
    private BaseAdapter reasonAdapter = null;
    private BaseAdapter modeAdapter = null;
    private TextView applyBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_service);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        applyBtn = (TextView) findViewById(R.id.applyBtn);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(OrderServiceActivity.this,ServiceResultActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
        bindViews();
    }
    private void bindViews() {
        spin_one = (Spinner)findViewById(R.id.spin_one);
        spin_two = (Spinner)findViewById(R.id.spin_two);

        ArrayList<ServiceReason> spin_one_Array = new ArrayList<>();
        spin_one_Array.add(new ServiceReason("质量问题"));
        spin_one_Array.add(new ServiceReason("不喜欢"));
        spin_one_Array.add(new ServiceReason("其他原因"));

        ArrayList<ServiceMode> spin_two_Array = new ArrayList<>();
        spin_two_Array.add(new ServiceMode("仅退款"));
        spin_two_Array.add(new ServiceMode("退货退款"));

        reasonAdapter = new ServiceAdapter<ServiceReason>(spin_one_Array,R.layout.order_service_spin) {
            @Override
            public void bindView(ViewHolder holder, ServiceReason obj) {
                holder.setText(R.id.txt_name,obj.getContent());
            }
        };
        modeAdapter = new ServiceAdapter<ServiceMode>(spin_two_Array,R.layout.order_service_spin) {
            @Override
            public void bindView(ViewHolder holder, ServiceMode obj) {
                holder.setText(R.id.txt_name,obj.getContent());
            }
        };
        spin_one.setAdapter(reasonAdapter);
        spin_two.setAdapter(modeAdapter);
        spin_one.setOnItemSelectedListener(this);
        spin_two.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin_one:

                break;
            case R.id.spin_two:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
