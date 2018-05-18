package com.project.mxd.mxd_community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.LinkedList;

/**
 * Created by mxd on 2018/3/5.
 */

public class OrderManagerAdapter extends BaseAdapter {
    private LinkedList<OrderManagerItem> itemData;
    private Context itemContext;
    private int index;

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
        TextView goods_name = (TextView) convertView.findViewById(R.id.goods_name);
        TextView price_num = (TextView) convertView.findViewById(R.id.price_num);
        TextView receiver_name = (TextView) convertView.findViewById(R.id.receiver_name);
        TextView logistics = (TextView) convertView.findViewById(R.id.logistics);
        TextView order_detail = (TextView) convertView.findViewById(R.id.order_detail);
        TextView order_service = (TextView) convertView.findViewById(R.id.order_service);
        goods_name.setText(itemData.get(position).getGoodsName());
        price_num.setText(itemData.get(position).getGoodsPrice());
        receiver_name.setText(itemData.get(position).getRecieverName());
        index = position;
        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemContext,LogisticActivity.class);
                itemContext.startActivity(intent);
            }
        });
        order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemContext,OrderDetailActivity.class);
                intent.putExtra("goodsImageId",itemData.get(index).getGoodsImageId());
                intent.putExtra("goodsName",itemData.get(index).getGoodsName());
                intent.putExtra("goodsPrice",itemData.get(index).getGoodsPrice());

                intent.putExtra("recieverName",itemData.get(index).getRecieverName());
                intent.putExtra("recieverPhone",itemData.get(index).getRecieverPhone());
                intent.putExtra("recieverAddress",itemData.get(index).getRecieverAddress());
                itemContext.startActivity(intent);
            }
        });
        order_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemContext,OrderServiceActivity.class);
                itemContext.startActivity(intent);
            }
        });
        return convertView;
    }
}
