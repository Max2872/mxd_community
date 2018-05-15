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
import com.project.mxd.mxd_community.AddressManagerAdapter.MyClickListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maohs on 2018/3/30.
 */

public class AddressManagerActivity extends AppCompatActivity implements MyClickListener {
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

        itemData = new LinkedList();
        adapter = new AddressManagerAdapter((LinkedList<AddressManagerItem>)itemData,context,this);
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

    @Override
    public void clickListener(View v) {
        int tag = Integer.parseInt(v.getTag().toString());
        SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
        db.delete("addressInfo","phoneNum=? and recieverName=?",new String[] {phoneNum,itemData.get(tag).getReceiver()});
        db.close();
        itemData.remove(tag);
        adapter.notifyDataSetChanged();
    }
}
