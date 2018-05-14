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

import java.util.ArrayList;

/**
 * Created by maohs on 2018/3/5.
 */

public class GiftBoxListActivity extends AppCompatActivity {
    private GridView grid_item;
    private ImageView backImage;
    private BaseAdapter itemAdapter = null;
    private ArrayList<GiftBoxItem>itemData = null;
    private GiftBoxItem goodsItem = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_box_list);
        grid_item = (GridView) findViewById(R.id.grid_box);
        backImage = (ImageView) findViewById(R.id.top_bar_back);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        itemData = new ArrayList<>();
        itemData.add(new GiftBoxItem(R.drawable.meishi188,"数码蛋糕","188"));
        itemData.add(new GiftBoxItem(R.drawable.meishi50,"美食--芝麻丸","49.9"));
        itemData.add(new GiftBoxItem(R.drawable.meishi0188,"美食--蛋糕","188"));

        itemData.add(new GiftBoxItem(R.drawable.shuma3600,"数码--相机","3600"));
        itemData.add(new GiftBoxItem(R.drawable.shuma999,"数码--录音笔","999"));
        itemData.add(new GiftBoxItem(R.drawable.shuma28,"数码--水晶球","28"));
        itemData.add(new GiftBoxItem(R.drawable.shuma80,"数码--小电扇","79.9"));
        itemData.add(new GiftBoxItem(R.drawable.shuma499,"数码--手表","499"));

        itemData.add(new GiftBoxItem(R.drawable.fushi1,"礼盒5","1"));
        itemData.add(new GiftBoxItem(R.drawable.fushi45,"礼盒6","45"));
        itemData.add(new GiftBoxItem(R.drawable.fushi55,"礼盒6","55"));
        itemData.add(new GiftBoxItem(R.drawable.fushi65,"礼盒6","65"));
        itemData.add(new GiftBoxItem(R.drawable.fushi1299,"礼盒6","1299"));

        itemData.add(new GiftBoxItem(R.drawable.liwu120,"礼盒7","120"));
        itemData.add(new GiftBoxItem(R.drawable.liwu186,"礼盒8","186"));
        itemData.add(new GiftBoxItem(R.drawable.liwu220,"礼盒7","220"));
        itemData.add(new GiftBoxItem(R.drawable.liwu223,"礼盒8","223"));
        itemData.add(new GiftBoxItem(R.drawable.liwu268,"礼盒7","268"));

        itemData.add(new GiftBoxItem(R.drawable.xiangshui98,"礼盒8","98"));
        itemData.add(new GiftBoxItem(R.drawable.xiangshui148,"礼盒8","148"));
        itemData.add(new GiftBoxItem(R.drawable.xiangshui1189,"礼盒8","1189"));

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
                intent.putExtra("imageId",itemData.get(position).getImageId());
                intent.putExtra("name",itemData.get(position).getBoxName());
                intent.putExtra("price",itemData.get(position).getBoxPrice());
                if (getPreference()) {
                    intent.setClass(GiftBoxListActivity.this,CustomGoodsDetailActivity.class);
                }else {
                    intent.setClass(GiftBoxListActivity.this,GoodsDetailActivity.class);
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
