package com.project.mxd.mxd_community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by mao on 2018/4/25.
 */

public class CartGoodsAdapter extends BaseAdapter {
    private LinkedList<CartGoodsItem> itemData;
    private Context itemContext;

    public CartGoodsAdapter(LinkedList<CartGoodsItem>itemData,Context itemContext) {
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
        convertView = LayoutInflater.from(itemContext).inflate(R.layout.cart_content_item,parent,false);
        TextView goods_name = (TextView) convertView.findViewById(R.id.goodsName);
        TextView price_num = (TextView) convertView.findViewById(R.id.goodsPrice);
        goods_name.setText("商品名：" + itemData.get(position).getGoodsName());
        price_num.setText("价格：" + itemData.get(position).getGoodsPrice());

        return convertView;
    }
}
