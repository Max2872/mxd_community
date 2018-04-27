package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.Intent;
import android.media.ImageWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by mao on 2018/4/27.
 */

public class AddressManagerAdapter extends BaseAdapter {
    private LinkedList<AddressManagerItem> itemData;
    private Context itemContext;

    public AddressManagerAdapter(LinkedList<AddressManagerItem>itemData,Context itemContext) {
        this.itemData = itemData;
        this.itemContext = itemContext;
    }

    @Override
    public int getCount() {
        return itemData.size();
    }

    @Override
    public Object getItem(int position) {
        return itemData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(itemContext).inflate(R.layout.address_manager_item,parent,false);
        TextView reciever = (TextView) convertView.findViewById(R.id.reciever);
        TextView phoneNum = (TextView) convertView.findViewById(R.id.phoneNum);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        ImageView addressSelectedImage = (ImageView) convertView.findViewById(R.id.addressSelectedImage);
        ImageView addressEditImage = (ImageView) convertView.findViewById(R.id.addressEditImage);
        ImageView addressDeleteImage = (ImageView) convertView.findViewById(R.id.addressDeleteImage);
        reciever.setText(itemData.get(position).getReceiver());
        phoneNum.setText(itemData.get(position).getPhoneNum());
        address.setText(itemData.get(position).getAddress());

        addressSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        addressEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        addressDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return convertView;
    }
}
