package com.project.mxd.mxd_community;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }
    private void initViews() {

        TextView ignoreBtn =(TextView)findViewById(R.id.ignore_btn);
        TextView loginDesc = (TextView)findViewById(R.id.loginDesc);
        TextView loginBtn = (TextView)findViewById(R.id.login_btn);
        //文本下划线
        ignoreBtn.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        loginDesc.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        //忽略登录
        ignoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //登录/注册
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
