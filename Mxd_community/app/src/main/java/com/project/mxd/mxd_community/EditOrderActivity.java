package com.project.mxd.mxd_community;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by maohs on 2018/3/29.
 */

public class EditOrderActivity extends AppCompatActivity {
    private CommunityOpenHelper communityOpenHelper;
    private String walletString = "0";
    private String phoneNum;
    private ImageView backImage;
    private RelativeLayout recieverInfo;

    private ImageView goodsImage;
    private TextView goodsName;
    private TextView goodsPrice;

    private int goodsCount = 1;
    private float submitPrice;
    private float singlePrice;
    private int goodsImageId = 0;
    private String goodsNameString;

    private TextView recieverName;
    private TextView recieverPhone;
    private TextView recieverAddress;

    private ImageView orderNumDec;
    private ImageView orderNumAdd;
    private TextView orderNum;

    private TextView orderAmount;
    private TextView orderSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);
        SharedPreferences preferences = this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        phoneNum = preferences.getString("phoneNum","");
        communityOpenHelper = new CommunityOpenHelper(this,"community.db",null,1);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        recieverInfo = (RelativeLayout) findViewById(R.id.recieverInfo);
        orderNumDec = (ImageView) findViewById(R.id.orderNumDec);
        orderNumAdd = (ImageView) findViewById(R.id.orderNumAdd);

        recieverName = (TextView) findViewById(R.id.recieverName);
        recieverPhone = (TextView) findViewById(R.id.recieverPhone);
        recieverAddress = (TextView) findViewById(R.id.addressContent);

        goodsImage = (ImageView) findViewById(R.id.goodsImage);
        goodsName = (TextView) findViewById(R.id.goodsName);
        goodsPrice = (TextView) findViewById(R.id.goodsPrice);
        orderNum = (TextView) findViewById(R.id.orderNum);

        orderAmount = (TextView) findViewById(R.id.orderAmount);
        orderSubmit = (TextView) findViewById(R.id.orderSubmit);

        Intent getIntent = getIntent();
        goodsImageId = getIntent.getIntExtra("imageId",R.mipmap.ic_launcher);
        goodsNameString = getIntent.getStringExtra("name");
        submitPrice = Float.parseFloat(getIntent.getStringExtra("price"));
        goodsImage.setImageResource(goodsImageId);
        goodsName.setText(goodsNameString);
        goodsPrice.setText(submitPrice + "");
        singlePrice = submitPrice;
        orderAmount.setText("￥" + submitPrice + "元");
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recieverInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        orderNumDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsCount > 1) {
                    goodsCount -= 1;
                    submitPrice = goodsCount * singlePrice;
                    orderAmount.setText("￥" + String.format("%.2f", submitPrice).toString() + "元");
                    orderNum.setText(goodsCount + "");
                }
            }
        });

        orderNumAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsCount < 99) {
                    goodsCount += 1;
                    submitPrice = goodsCount * singlePrice;
                    orderAmount.setText("￥" + String.format("%.2f", submitPrice).toString() + "元");
                    orderNum.setText(goodsCount + "");
                }
            }
        });

        orderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        final OrderDialog dialog = new OrderDialog(this);
        dialog.show();
        dialog.setEnsureBtn(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ensurePay();
            }
        });
    }

    private void ensurePay() {
        SQLiteDatabase db = communityOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("userInfo",null,null,null,null,null,null);
        String dbPhone;
        try {
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    dbPhone = cursor.getString(cursor.getColumnIndex("phoneNum"));
                    if (dbPhone.equals(phoneNum)) {
                        walletString = cursor.getString(cursor.getColumnIndex("wallet"));
                        break;
                    }
                }
                if (walletString == null) {
                    walletString = "0";
                }
            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        float originWallet = Float.parseFloat(walletString);
        if (submitPrice > originWallet) {
            customToast("余额不足，请充值",2);
            db.close();
            return;
        }else {
            originWallet = originWallet - submitPrice;
        }
        ContentValues value = new ContentValues();
        value.put("wallet",originWallet + "");
        db.update("userInfo",value,"phoneNum=?",new String[] {phoneNum});

        ContentValues value2 = new ContentValues();
        value2.put("phoneNum",phoneNum);
        value2.put("goodsImageId",goodsImageId + "");
        value2.put("goodsName",goodsNameString + "");
        value2.put("goodsPrice",submitPrice + "");
        value2.put("recieverName",recieverName.getText().toString());
        value2.put("recieverPhone",recieverPhone.getText().toString());
        value2.put("recieverAddress",recieverAddress.getText().toString());
        db.insert("orderInfo","goodsImageId",value2);
        db.close();
        Intent intent = new Intent();
        intent.setClass(EditOrderActivity.this, OrderResultActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        startActivity(intent);
    }

    private void customToast(String string,int showTime) {
        Toast toast = Toast.makeText(EditOrderActivity.this,string,showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
}
