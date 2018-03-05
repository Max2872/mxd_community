package com.project.mxd.mxd_community;

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
    private ArrayList<BoxItem>itemData = null;
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
        itemData.add(new BoxItem(R.color.imageColor,"礼盒1","¥20"));
        itemData.add(new BoxItem(R.color.imageColor,"礼盒2","¥30"));
        itemData.add(new BoxItem(R.color.imageColor,"礼盒3","¥40"));
        itemData.add(new BoxItem(R.color.imageColor,"礼盒4","¥50"));
        itemData.add(new BoxItem(R.color.imageColor,"礼盒5","¥60"));
        itemData.add(new BoxItem(R.color.imageColor,"礼盒6","¥70"));
        itemData.add(new BoxItem(R.color.imageColor,"礼盒7","¥80"));
        itemData.add(new BoxItem(R.color.imageColor,"礼盒8","¥90"));

        itemAdapter = new GiftBoxAdapter<BoxItem>(itemData, R.layout.gift_box_item) {
            @Override
            public void bindView(ViewHolder holder, BoxItem obj) {
                holder.setImageResource(R.id.box_image, obj.getImageId());
                holder.setText(R.id.box_name, obj.getBoxName());
                holder.setText(R.id.box_price,obj.getBoxPrice());
            }
        };

        grid_item.setAdapter(itemAdapter);

        grid_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
