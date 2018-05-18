package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mxd on 2018/3/5.
 */

public class GiftBoxListActivity extends AppCompatActivity {
    private GridView grid_item;
    private ImageView backImage;
    private BaseAdapter itemAdapter = null;
    private ArrayList<GiftBoxItem>itemData = null;
    private GiftBoxItem goodsItem = null;
    private  boolean shouldTurnCustom = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_box_list);
        grid_item = (GridView) findViewById(R.id.grid_box);
        backImage = (ImageView) findViewById(R.id.top_bar_back);
        TextView top_bar_text = (TextView)findViewById(R.id.top_bar_text);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shouldTurnCustom = getPreference();
        itemData = new ArrayList<>();
        final int[] goodsData = {R.drawable.goodslihe68,R.drawable.goodslihe359,R.drawable.goodslihe699,R.drawable.goodslihe1299};
        if (shouldTurnCustom) {
            itemData.add(new GiftBoxItem(R.drawable.meishi188,"数码蛋糕","188",1));
            itemData.add(new GiftBoxItem(R.drawable.meishi0188,"美食蛋糕","188",1));

            itemData.add(new GiftBoxItem(R.drawable.shuma3600,"相机","3600",1));
            itemData.add(new GiftBoxItem(R.drawable.shuma999,"录音笔","999",1));
            itemData.add(new GiftBoxItem(R.drawable.shuma28,"水晶球","28",1));
            itemData.add(new GiftBoxItem(R.drawable.shuma80,"小电扇","79.9",1));
            itemData.add(new GiftBoxItem(R.drawable.shuma499,"数码手表","499",1));

            itemData.add(new GiftBoxItem(R.drawable.fushi1,"严选羊毛围巾","599",1));
            itemData.add(new GiftBoxItem(R.drawable.fushi45,"魔术贴","45",1));
            itemData.add(new GiftBoxItem(R.drawable.fushi55,"车载支架","55",1));
            itemData.add(new GiftBoxItem(R.drawable.fushi65,"香囊","65",1));
            itemData.add(new GiftBoxItem(R.drawable.fushi1299,"纯羊毛围巾","1299",1));

            itemData.add(new GiftBoxItem(R.drawable.liwu120,"严选钢笔","120",1));
            itemData.add(new GiftBoxItem(R.drawable.liwu186,"复古笔记本","186",1));
            itemData.add(new GiftBoxItem(R.drawable.liwu223,"紫砂壶","223",1));
            itemData.add(new GiftBoxItem(R.drawable.liwu268,"永生花","268",1));

            itemData.add(new GiftBoxItem(R.drawable.xiangshui98,"香水","98",1));
            itemData.add(new GiftBoxItem(R.drawable.xiangshui1189,"CHANEL香水","1189",1));
        }else {
            top_bar_text.setText("商品列表");
            itemData.add(new GiftBoxItem(R.drawable.lihe1,"实用礼盒","68",0));
            itemData.add(new GiftBoxItem(R.drawable.lihe2,"温暖礼盒","359",1));
            itemData.add(new GiftBoxItem(R.drawable.lihe3,"情怀礼盒","699",2));
            itemData.add(new GiftBoxItem(R.drawable.lihe4,"超级礼盒","1299",3));

        }
        filterGoods();
        itemAdapter = new GiftBoxAdapter<GiftBoxItem>(itemData, R.layout.gift_box_item) {
            @Override
            public void bindView(ViewHolder holder, GiftBoxItem obj) {
                holder.setImageResource(R.id.box_image, obj.getImageId());
                holder.setText(R.id.box_name, obj.getBoxName());
                holder.setText(R.id.box_price,"￥" + obj.getBoxPrice());
            }
        };

        grid_item.setAdapter(itemAdapter);

        grid_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                int goodsPosition = position;
                if (shouldTurnCustom) {
                    intent.putExtra("imageId",itemData.get(position).getImageId());
                }else {
                    intent.putExtra("imageId",goodsData[itemData.get(position).getIndex()]);
                    intent.putExtra("cartImageId",itemData.get(position).getImageId());
                }
                intent.putExtra("name",itemData.get(position).getBoxName());
                intent.putExtra("price",itemData.get(position).getBoxPrice());
                if (shouldTurnCustom) {
                    intent.setClass(GiftBoxListActivity.this,GoodsDetailActivity.class);
                }else {
                    intent.setClass(GiftBoxListActivity.this,CustomGoodsDetailActivity.class);
                }
                startActivity(intent);

            }
        });

    }

    private void filterGoods() {
        Intent getIntent = getIntent();
        String whoString = getIntent.getStringExtra("whoString");
        String moneyString = getIntent.getStringExtra("moneyString");
        String useString = getIntent.getStringExtra("useString");
        String kindString = getIntent.getStringExtra("kindString");

        Float minMoney;
        Float maxMoney;
        switch (moneyString) {
            case "100元以内":
                maxMoney = 100.0f;
                minMoney = 0.0f;
                break;
            case "100元~500元":
                maxMoney = 500f;
                minMoney = 100f;
                break;
            case "500元~1000元":
                maxMoney = 1000f;
                minMoney = 500f;
                break;
            case "1000元以上":
                maxMoney = 100000f;
                minMoney = 1000f;
                break;
            default:
                maxMoney = 100f;
                minMoney = 0f;
        }

        ArrayList<GiftBoxItem> tempData = new ArrayList<>();
        for (int i=0;i<itemData.size();i++) {
           if (Float.parseFloat(itemData.get(i).getBoxPrice()) < maxMoney && Float.parseFloat(itemData.get(i).getBoxPrice()) > minMoney) {
               tempData.add(itemData.get(i));
            }
        }
        itemData = tempData;
    }

    private boolean getPreference() {
        SharedPreferences preferences = this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        boolean shouldTurnCustom = preferences.getBoolean("shouldTurnCustom",false);
        return shouldTurnCustom;
    }
}
