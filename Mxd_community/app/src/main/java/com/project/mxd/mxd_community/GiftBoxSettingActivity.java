package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by mxd on 2018/3/27.
 */

public class GiftBoxSettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ImageView backImage;

    private Spinner spin_who;
    private Spinner spin_money;
    private Spinner spin_use;
    private Spinner spin_kind;

    private BaseAdapter whoAdapter = null;
    private BaseAdapter moneyAdapter = null;
    private BaseAdapter useAdapter = null;
    private BaseAdapter kindAdapter = null;

    private ArrayList<ServiceMode> spin_who_Array;
    private ArrayList<ServiceMode> spin_money_Array;
    private ArrayList<ServiceMode> spin_use_Array;
    private ArrayList<ServiceMode> spin_kind_Array;

    private String whoString;
    private String moneyString;
    private String useString;
    private String kindString;

    private TextView ensureBtn;
    private TextView resetBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_box_setting);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        ensureBtn = (TextView)findViewById(R.id.ensureBtn);
        resetBtn = (TextView)findViewById(R.id.resetBtn);
        TextView top_bar_text = (TextView)findViewById(R.id.top_bar_text);
        if (getPreference()) {
            top_bar_text.setText("自由搭配");
        }
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
                intent.putExtra("whoString",whoString);
                intent.putExtra("moneyString",moneyString);
                intent.putExtra("useString",useString);
                intent.putExtra("kindString",kindString);
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
        bindViews();
    }
    private void bindViews() {
        spin_who = (Spinner)findViewById(R.id.spin_who);
        spin_money = (Spinner)findViewById(R.id.spin_money);
        spin_use = (Spinner)findViewById(R.id.spin_use);
        spin_kind = (Spinner)findViewById(R.id.spin_kind);

        spin_who_Array = new ArrayList<>();
        spin_who_Array.add(new ServiceMode("长辈"));
        spin_who_Array.add(new ServiceMode("情侣"));
        spin_who_Array.add(new ServiceMode("朋友"));
        spin_who_Array.add(new ServiceMode("商业伙伴"));

        spin_money_Array = new ArrayList<>();
        spin_money_Array.add(new ServiceMode("100元以内"));
        spin_money_Array.add(new ServiceMode("100元~500元"));
        spin_money_Array.add(new ServiceMode("500元~1000元"));
        spin_money_Array.add(new ServiceMode("1000元以上"));

        spin_use_Array = new ArrayList<>();
        spin_use_Array.add(new ServiceMode("生日"));
        spin_use_Array.add(new ServiceMode("节日"));
        spin_use_Array.add(new ServiceMode("升职"));
        spin_use_Array.add(new ServiceMode("迁居"));

        spin_kind_Array = new ArrayList<>();
        spin_kind_Array.add(new ServiceMode("数码"));
        spin_kind_Array.add(new ServiceMode("养生"));
        spin_kind_Array.add(new ServiceMode("服饰"));
        spin_kind_Array.add(new ServiceMode("美食"));

        whoAdapter = new ServiceAdapter<ServiceMode>(spin_who_Array,R.layout.order_service_spin) {
            @Override
            public void bindView(ViewHolder holder, ServiceMode obj) {
                holder.setText(R.id.txt_name,obj.getContent());
            }
        };
        moneyAdapter = new ServiceAdapter<ServiceMode>(spin_money_Array,R.layout.order_service_spin) {
            @Override
            public void bindView(ViewHolder holder, ServiceMode obj) {
                holder.setText(R.id.txt_name,obj.getContent());
            }
        };

        useAdapter = new ServiceAdapter<ServiceMode>(spin_use_Array,R.layout.order_service_spin) {
            @Override
            public void bindView(ViewHolder holder, ServiceMode obj) {
                holder.setText(R.id.txt_name,obj.getContent());
            }
        };

        kindAdapter = new ServiceAdapter<ServiceMode>(spin_kind_Array,R.layout.order_service_spin) {
            @Override
            public void bindView(ViewHolder holder, ServiceMode obj) {
                holder.setText(R.id.txt_name,obj.getContent());
            }
        };
        spin_who.setAdapter(whoAdapter);
        spin_money.setAdapter(moneyAdapter);
        spin_use.setAdapter(useAdapter);
        spin_kind.setAdapter(kindAdapter);

        spin_who.setOnItemSelectedListener(this);
        spin_money.setOnItemSelectedListener(this);
        spin_use.setOnItemSelectedListener(this);
        spin_kind.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin_who:
                whoString = spin_who_Array.get(position).getContent();
                break;
            case R.id.spin_money:
                moneyString = spin_money_Array.get(position).getContent();
                break;
            case R.id.spin_use:
                useString = spin_use_Array.get(position).getContent();
                break;
            case R.id.spin_kind:
                kindString = spin_kind_Array.get(position).getContent();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean getPreference() {
        SharedPreferences preferences = this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        boolean shouldTurnCustom = preferences.getBoolean("shouldTurnCustom",false);
        return shouldTurnCustom;
    }
}
