package com.project.mxd.mxd_community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
        LayoutInflater mainInflater = LayoutInflater.from(this);
        View formView = mainInflater.inflate(R.layout.activity_login,null);
//        TextView ignoreBtn =

    }
}
