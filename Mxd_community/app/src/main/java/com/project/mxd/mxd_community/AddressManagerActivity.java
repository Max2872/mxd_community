package com.project.mxd.mxd_community;

import android.content.Context;
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

public class AddressManagerActivity extends AppCompatActivity {
    private List<AddressManagerItem> itemData = null;
    private Context context;
    private AddressManagerAdapter adapter = null;
    private ListView list;
    private ImageView backImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_manager);
        context = AddressManagerActivity.this;
        backImage = (ImageView)findViewById(R.id.top_bar_back);
        list = (ListView)findViewById(R.id.address_list);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        itemData = new LinkedList<AddressManagerItem>();
        itemData.add(new AddressManagerItem("马晓东","18217752872","上海上海市浦东新区张江高科技园区祥科路58号炬芯研发大楼A栋8楼"));
        itemData.add(new AddressManagerItem("李莉","13817821581","上海上海市松江区方松街道文诚路888弄（珠江新城）19号1502室"));
        itemData.add(new AddressManagerItem("马晓东","18217752872","上海上海市浦东新区曹路镇上海第二工业大学河东男寝"));
        itemData.add(new AddressManagerItem("马晓东","18217752872","上海上海市浦东新区曹路镇上海第二工业大学河东男寝"));
        adapter = new AddressManagerAdapter((LinkedList<AddressManagerItem>)itemData,context);
        list.setAdapter(adapter);

    }
}
