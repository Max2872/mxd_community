package com.project.mxd.mxd_community;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maohs on 2018/3/5.
 */

public class OrderManagerActivity extends AppCompatActivity {
    private List<OrderManagerItem> itemData = null;
    private Context context;
    private OrderManagerAdapter adapter = null;
    private ListView list;
    private ImageView backImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_manager);
        context = OrderManagerActivity.this;
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = (ListView)findViewById(R.id.order_list);

        itemData = new LinkedList<OrderManagerItem>();
        itemData.add(new OrderManagerItem("礼盒1","￥299.0元","马晓东"));
        itemData.add(new OrderManagerItem("礼盒2","￥199.0元","李晓东"));
        itemData.add(new OrderManagerItem("礼盒3","￥99.0元","张晓东"));
        adapter = new OrderManagerAdapter((LinkedList<OrderManagerItem>)itemData,context);
        list.setAdapter(adapter);
    }
}
