package com.project.mxd.mxd_community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by mxd on 2018/4/25.
 */

public class RemindAdapter extends BaseAdapter {
    private LinkedList<RemindItem> itemData;
    private Context itemContext;

    public RemindAdapter(LinkedList<RemindItem>itemData,Context itemContext) {
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
        convertView = LayoutInflater.from(itemContext).inflate(R.layout.remind_item,parent,false);
        TextView celebrateTime = (TextView) convertView.findViewById(R.id.celebrateTime);
        TextView earlyDays = (TextView) convertView.findViewById(R.id.earlyDays);
        TextView remarkContent = (TextView) convertView.findViewById(R.id.remarkContent);
        celebrateTime.setText("庆祝日期：" + itemData.get(position).getCelebrateTime());
        earlyDays.setText("提前几天提醒：" + itemData.get(position).getEarlyDays());
        remarkContent.setText(itemData.get(position).getRemark());

        return convertView;
    }
}
