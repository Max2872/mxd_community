package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        String goodsImageId;
        String goodsName;
        String goodsPrice;

        String recieverName;
        String recieverPhone;
        String recieverAddress;
        SharedPreferences preferences = this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        String phoneNum = preferences.getString("phoneNum","");
        CommunityOpenHelper communityOpenHelper = new CommunityOpenHelper(this,"community.db",null,1);
        SQLiteDatabase db = communityOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("orderInfo",null,null,null,null,null,null);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("phoneNum")).equals(phoneNum)) {
                        goodsImageId = cursor.getString(cursor.getColumnIndex("goodsImageId"));
                        goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
                        goodsPrice = cursor.getString(cursor.getColumnIndex("goodsPrice"));

                        recieverName = cursor.getString(cursor.getColumnIndex("recieverName"));
                        recieverPhone = cursor.getString(cursor.getColumnIndex("recieverPhone"));
                        recieverAddress = cursor.getString(cursor.getColumnIndex("recieverAddress"));
                        itemData.add(new OrderManagerItem(goodsImageId,goodsName,goodsPrice,recieverName,recieverPhone,recieverAddress));
                    }
                }
            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();


        adapter = new OrderManagerAdapter((LinkedList<OrderManagerItem>)itemData,context);
        list.setAdapter(adapter);
    }
}
