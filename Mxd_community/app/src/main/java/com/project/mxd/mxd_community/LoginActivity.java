package com.project.mxd.mxd_community;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static LoginActivity loginInstance;
    private boolean hasLogin;
    private CommunityOpenHelper communityOpenHelper;
    private EditText phoneEdit;
    private EditText passwardEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginInstance = this;
        communityOpenHelper = new CommunityOpenHelper(LoginActivity.this,"community.db",null,1);
        if (hasUserInfo()) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainTabbarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
            startActivity(intent);
            return;
        }
        initViews();
    }
    private void initViews() {
        Intent originIntent = getIntent();
        hasLogin = originIntent.getBooleanExtra("hasLogin",false);
        TextView ignoreBtn =(TextView)findViewById(R.id.ignore_btn);
        final TextView loginDesc = (TextView)findViewById(R.id.loginDesc);
        final TextView loginBtn = (TextView)findViewById(R.id.login_btn);

        phoneEdit = (EditText)findViewById(R.id.phoneEdit);
        passwardEdit = (EditText)findViewById(R.id.passwardEdit);
        //文本下划线
        ignoreBtn.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        loginDesc.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        //忽略登录
        ignoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreference(false);
                if (hasLogin) {
                    finish();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainTabbarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });

        loginDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginDesc.getText().toString().contains("登录")) {
                    loginDesc.setText("没有账号？去注册");
                    loginBtn.setText("登录");
                }else {
                    loginDesc.setText("已有账号？去登录");
                    loginBtn.setText("注册");
                }
            }
        });

        //登录/注册
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检验手机号
                String telRegex = "[1][34578]\\d{9}";
                String phoneNum = phoneEdit.getText().toString();
                String passward = passwardEdit.getText().toString();
                if (phoneNum.isEmpty()) {
                    customToast("请输入手机号",2);
                    return;
                }else if (!phoneNum.matches(telRegex)) {
                    customToast("请输入正确的手机号",2);
                    return;
                }
                //检验密码
                if (passward.length() < 6) {
                    customToast("密码必须六位以上",2);
                    return;
                } else if (passward.length() > 15) {
                    customToast("密码长度过长",2);
                    return;
                }
                saveUserInfo(phoneNum,passward);
                savePreference(true);
                if (hasLogin) {
                    finish();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainTabbarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
    }
    private void customToast(String string,int showTime) {
        Toast toast = Toast.makeText(LoginActivity.this,string,showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
    private void saveUserInfo(String phoneNum,String passward) {
        SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("userInfo",null,null,null,null,null,null);
        ContentValues value = new ContentValues();
        value.put("userId",1);
        value.put("phoneNum",phoneNum);
        value.put("passward",passward);
        Log.i("phoneNum",phoneNum);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                db.update("userInfo",value,null,null);
            }else  {
                db.insert("userInfo",null,value);
            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();
    }
    private boolean hasUserInfo() {
        boolean hasData = false;
        SQLiteDatabase db = communityOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("userInfo",null,null,null,null,null,null);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    hasData = true;
                }
            }else  {

            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();
        return hasData;
    }
    private void savePreference(boolean shouldLogin) {
        SharedPreferences preference = LoginActivity.this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("shouldLogin",shouldLogin);
        editor.commit();
    }
}
