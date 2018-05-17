package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maohs on 2018/3/30.
 */

public class RemindActivity extends AppCompatActivity {
    private List<RemindItem> itemData = null;
    private Context context;
    private RemindAdapter adapter = null;
    private ListView list;
    private ImageView backImage;
    private ImageView remindAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remind);
        context = RemindActivity.this;
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        remindAdd = (ImageView)findViewById(R.id.remindAdd);
        list = (ListView)findViewById(R.id.remind_list);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        remindAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RemindActivity.this,RemindAddActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });

        itemData = new LinkedList();
        itemData.add(new RemindItem("2018年05月20日","7天","情人节，和xxx的xxx天。"));
        itemData.add(new RemindItem("2018年9月13日","3天","张三的生日，给她买个礼物。"));
        adapter = new RemindAdapter((LinkedList<RemindItem>)itemData,context);
        list.setAdapter(adapter);
    }
}
