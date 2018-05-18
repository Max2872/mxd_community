package com.project.mxd.mxd_community;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mxd on 2018/4/25.
 */

public class RemindAddActivity extends AppCompatActivity {
    private ImageView backImage;
    private EditText remindTimeContent;
    private  EditText remindRemark;
    private TextView remindSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remind_add);
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        remindTimeContent = (EditText)findViewById(R.id.remindTimeContent);
        remindRemark = (EditText)findViewById(R.id.remindRemark);
        remindSave = (TextView)findViewById(R.id.remindSave);
        remindSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = RemindAddActivity.this.getSharedPreferences("userPreference", Context.MODE_PRIVATE);
                String phoneNum = preferences.getString("phoneNum","");
                CommunityOpenHelper communityOpenHelper = new CommunityOpenHelper(RemindAddActivity.this,"community.db",null,1);
                SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put("phoneNum",phoneNum);
                value.put("remindTime",remindTimeContent.getText().toString());
                value.put("remindAdranceDay","7");
                value.put("remark",remindRemark.getText().toString());
                db.insert("remindInfo","phoneNum",value);
                db.close();

                finish();
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
