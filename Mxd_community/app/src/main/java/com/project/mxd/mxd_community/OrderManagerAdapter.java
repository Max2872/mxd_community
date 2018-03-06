package com.project.mxd.mxd_community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.LinkedList;

/**
 * Created by maohs on 2018/3/5.
 */

public abstract class OrderManagerAdapter extends BaseAdapter {
    private LinkedList<OrderManagerItem> itemData;
    private Context itemContext;

    public OrderManagerAdapter(LinkedList<OrderManagerItem>itemData,Context itemContext) {
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
        convertView = LayoutInflater.from(itemContext).inflate(R.layout.order_manager_item,parent,false);
//        ImageView img_icon = (TextView) convertView.findViewById(R.id.img_icon);
//        TextView txt_aName = (TextView) convertView.findViewById(R.id.txt_aName);
//        TextView txt_aSpeak = (TextView) convertView.findViewById(R.id.txt_aSpeak);
//        img_icon.setBackgroundResource(mData.get(position).getaIcon());
//        txt_aName.setText(itemData.get(position).getaName());
//        txt_aSpeak.setText(itemData.get(position).get());
        return convertView;
    }
}
