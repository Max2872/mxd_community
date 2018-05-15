package com.project.mxd.mxd_community;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maohs on 2018/3/30.
 */

public class AddressManagerActivity extends AppCompatActivity {
    private List<AddressManagerItem> itemData = null;
    private Context context;
    private AddressManagerAdapter adapter = null;
    private ListView list;
    private TextView addNewAddress;
    private ImageView backImage;
    private String phoneNum;
    private CommunityOpenHelper communityOpenHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_manager);
        context = AddressManagerActivity.this;
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        list = (ListView)findViewById(R.id.address_list);
        addNewAddress = (TextView)findViewById(R.id.addNewAddress);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AddressManagerActivity.this, AddressEditActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
        SharedPreferences preferences = this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        phoneNum = preferences.getString("phoneNum","");
        communityOpenHelper = new CommunityOpenHelper(this,"community.db",null,1);
        SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
        db.delete("addressInfo",null,null);
        ContentValues value = new ContentValues();
        value.put("phoneNum",phoneNum);
        value.put("recieverName","马晓东");
        value.put("recieverPhone","18217752872");
        value.put("recieverAddress","上海上海市浦东新区张江高科技园区祥科路58号炬芯研发大楼A栋8楼");
        db.insert("addressInfo","phoneNum",value);

        ContentValues value1 = new ContentValues();
        value1.put("phoneNum",phoneNum);
        value1.put("recieverName","李莉");
        value1.put("recieverPhone","13817821581");
        value1.put("recieverAddress","上海上海市松江区方松街道文诚路888弄（珠江新城）19号1502室");
        db.insert("addressInfo","phoneNum",value1);
        db.close();

        itemData = new LinkedList();
        adapter = new AddressManagerAdapter((LinkedList<AddressManagerItem>)itemData,context);
        list.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        itemData.clear();
        String recieverName;
        String recieverPhone;
        String recieverAddress;
        SQLiteDatabase db = communityOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("addressInfo",null,null,null,null,null,null);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("phoneNum")).equals(phoneNum)) {
                        recieverName = cursor.getString(cursor.getColumnIndex("recieverName"));
                        recieverPhone = cursor.getString(cursor.getColumnIndex("recieverPhone"));
                        recieverAddress = cursor.getString(cursor.getColumnIndex("recieverAddress"));
                        itemData.add(new AddressManagerItem(recieverName,recieverPhone,recieverAddress));
                    }
                }
            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();
        adapter.notifyDataSetChanged();
    }
}
