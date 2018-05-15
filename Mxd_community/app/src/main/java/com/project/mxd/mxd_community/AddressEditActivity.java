package com.project.mxd.mxd_community;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by mao on 2018/5/12.
 */

public class AddressEditActivity extends AppCompatActivity {
    private ImageView backImage;
    private EditText recieverContent;
    private EditText phoneContent;
    private EditText detailContent;
    private Switch settingSwitch;
    private TextView addressSave;

    private boolean isSelected;
    private  String reciever;
    private String phoneNumString;
    private String addressString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_edit);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        recieverContent = (EditText)findViewById(R.id.recieverContent);
        phoneContent = (EditText)findViewById(R.id.phoneContent);
        detailContent = (EditText)findViewById(R.id.detailContent);
        settingSwitch = (Switch)findViewById(R.id.settingSwitch);
        addressSave = (TextView)findViewById(R.id.addressSave);

        Intent getIntent = getIntent();
        isSelected = getIntent.getBooleanExtra("isSelected",false);
        reciever = getIntent.getStringExtra("reciever");
        phoneNumString = getIntent.getStringExtra("phoneNum");
        addressString = getIntent.getStringExtra("address");
        if (reciever != null) {
            recieverContent.setText(reciever);
            phoneContent.setText(phoneNumString);
            detailContent.setText(addressString);
            settingSwitch.setChecked(isSelected);
        }else {
            reciever = "";
            phoneNumString = "";
            addressString = "";
        }

        settingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = !isSelected;
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addressSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = AddressEditActivity.this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
                String phoneNum = preferences.getString("phoneNum","");
                CommunityOpenHelper communityOpenHelper = new CommunityOpenHelper(AddressEditActivity.this,"community.db",null,1);
                SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put("phoneNum",phoneNum);
                value.put("recieverName",reciever);
                value.put("recieverPhone",phoneNumString);
                value.put("recieverAddress",addressString);
//                if ()
                db.insert("addressInfo","phoneNum",value);
                db.close();

                finish();
            }
        });
    }
}
