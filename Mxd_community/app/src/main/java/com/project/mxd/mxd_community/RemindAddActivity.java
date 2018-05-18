package com.project.mxd.mxd_community;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by mxd on 2018/4/25.
 */

public class RemindAddActivity extends AppCompatActivity {
    private ImageView backImage;
    private EditText remindTimeContent;
    private  EditText remindRemark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remind_add);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        remindTimeContent = (EditText)findViewById(R.id.remindTimeContent);
        remindRemark = (EditText)findViewById(R.id.remindRemark);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
