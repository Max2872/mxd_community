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
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static LoginActivity loginInstance;
    private boolean hasLogin;
    private CommunityOpenHelper communityOpenHelper;
    private EditText phoneEdit;
    private EditText passwardEdit;

    private TextView checkDesc;
    private EditText checkEdit;
    private RelativeLayout checkLayout;
    private boolean isLoginMode = false;
    private boolean couldLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginInstance = this;
        communityOpenHelper = new CommunityOpenHelper(LoginActivity.this,"community.db",null,1);
        SharedPreferences preference = LoginActivity.this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        boolean shouldLogin = preference.getBoolean("shouldLogin",false);
        if (shouldLogin) {
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

        checkDesc = (TextView) findViewById(R.id.checkDesc);
        checkEdit = (EditText)findViewById(R.id.checkEdit);
        checkLayout = (RelativeLayout)findViewById(R.id.checkLayout);
        phoneEdit = (EditText)findViewById(R.id.phoneEdit);
        passwardEdit = (EditText)findViewById(R.id.passwardEdit);
        //文本下划线
        ignoreBtn.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        loginDesc.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        checkDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDesc.setText("0519");
            }
        });

        //忽略登录
        ignoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreference(false,null);
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
                    checkLayout.setVisibility(View.GONE);
                    isLoginMode = true;
                }else {
                    loginDesc.setText("已有账号？去登录");
                    loginBtn.setText("注册");
                    checkLayout.setVisibility(View.VISIBLE);
                    checkDesc.setText("验证码");
                    checkEdit.setText("");
                    isLoginMode = false;
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

                if (!isLoginMode) {
                    if (!checkEdit.getText().toString().equals("0519")) {
                        customToast("请输入有效的验证码",2);
                        return;
                    }
                }
                saveUserInfo(phoneNum,passward);

            }
        });
    }
    private void customToast(String string,int showTime) {
        Toast toast = Toast.makeText(LoginActivity.this,string,showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
    private void saveUserInfo(String phoneNum,String passward) {
        boolean shouldRegister = true;
        SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("userInfo",null,null,null,null,null,null);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    if (isLoginMode) {
                        //登录状态则校验账号密码是否存在
                        if (phoneNum.equals(cursor.getString(cursor.getColumnIndex("phoneNum"))) && passward.equals(cursor.getString(cursor.getColumnIndex("passward")))) {
                            couldLogin = true;
                            break;
                        }else if (phoneNum.equals(cursor.getString(cursor.getColumnIndex("phoneNum")))) {
                            shouldRegister = false;
                        }
                    }else {
                        //注册状态则插入到数据库中
                        if (phoneNum.equals(cursor.getString(cursor.getColumnIndex("phoneNum")))) {
                            shouldRegister = false;
                        }
                    }
                }

                if (isLoginMode) {
                   if (!shouldRegister) {
                       customToast("密码输入错误",2);
                   }else if (shouldRegister && !couldLogin) {
                       customToast("这是个新账号，请注册",2);
                   }
                }else {
                    if (!shouldRegister) {
                        customToast("该账号已存在，请登录",2);

                    }else {
                        cursor.close();
                        ContentValues value1 = new ContentValues();
                        value1.put("phoneNum",phoneNum);
                        value1.put("passward",passward);
                        value1.put("wallet",0);
                        db.insert("userInfo","phoneNum",value1);
                        couldLogin = true;

                        ContentValues value2 = new ContentValues();
                        value2.put("phoneNum",phoneNum);
                        value2.put("recieverName","马晓东");
                        value2.put("recieverPhone","18217752872");
                        value2.put("recieverAddress","上海上海市浦东新区张江高科技园区祥科路58号炬芯研发大楼A栋8楼");
                        db.insert("addressInfo","phoneNum",value2);

                        ContentValues value3 = new ContentValues();
                        value3.put("phoneNum",phoneNum);
                        value3.put("recieverName","李莉");
                        value3.put("recieverPhone","13817821581");
                        value3.put("recieverAddress","上海上海市松江区方松街道文诚路888弄（珠江新城）19号1502室");
                        db.insert("addressInfo","phoneNum",value3);
                    }
                }

            }else  {
                cursor.close();
                //数据库没数据的情况下，不论登录、注册
                ContentValues value = new ContentValues();
                value.put("phoneNum",phoneNum);
                value.put("passward",passward);
                value.put("wallet",0);
                db.insert("userInfo","phoneNum",value);

                ContentValues value2 = new ContentValues();
                value2.put("phoneNum",phoneNum);
                value2.put("recieverName","马晓东");
                value2.put("recieverPhone","18217752872");
                value2.put("recieverAddress","上海上海市浦东新区张江高科技园区祥科路58号炬芯研发大楼A栋8楼");
                db.insert("addressInfo","phoneNum",value2);

                ContentValues value3 = new ContentValues();
                value3.put("phoneNum",phoneNum);
                value3.put("recieverName","李莉");
                value3.put("recieverPhone","13817821581");
                value3.put("recieverAddress","上海上海市松江区方松街道文诚路888弄（珠江新城）19号1502室");
                db.insert("addressInfo","phoneNum",value3);
            }
        }catch (Exception e) {

        }finally {
            db.close();
            if (!couldLogin) {
                return;
            }
            savePreference(true,phoneNum);
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
    }

    private void savePreference(boolean shouldLogin,String phoneNum) {
        SharedPreferences preference = getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("shouldLogin",shouldLogin);
        editor.putString("phoneNum",phoneNum);
        editor.commit();
    }
}
