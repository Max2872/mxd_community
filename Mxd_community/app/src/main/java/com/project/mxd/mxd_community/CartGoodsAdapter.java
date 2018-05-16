package com.project.mxd.mxd_community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(itemContext).inflate(R.layout.cart_content_item,parent,false);

        ImageView cartSelectImage = (ImageView)convertView.findViewById(R.id.cartSelectImage);
        ImageView goodsImage = (ImageView) convertView.findViewById(R.id.goodsImage);
        TextView goods_name = (TextView) convertView.findViewById(R.id.goodsName);
        TextView price_num = (TextView) convertView.findViewById(R.id.goodsPrice);
        final TextView goodsNum = (TextView) convertView.findViewById(R.id.goodsNum);

        ImageView numDec = (ImageView) convertView.findViewById(R.id.numDec);
        ImageView numAdd = (ImageView) convertView.findViewById(R.id.numAdd);

        if (itemData.get(position).getIsSelected()) {
            cartSelectImage.setImageResource(R.drawable.goods_selected);
        }else {
            cartSelectImage.setImageResource(R.drawable.goods_normal);
        }
        goodsImage.setImageResource(Integer.parseInt(itemData.get(position).getGoodsImageId()));
        goods_name.setText("商品名：" + itemData.get(position).getGoodsName());
        price_num.setText("价格：" + itemData.get(position).getGoodsPrice());
        goodsNum.setText(itemData.get(position).getGoodNum());

        cartSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tempSelected = itemData.get(position).getIsSelected();
                tempSelected = !tempSelected;
                itemData.get(position).setSelected(tempSelected);
                notifyDataSetChanged();
            }
        });

        numDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsAccount = Integer.parseInt(itemData.get(position).getGoodNum());
                if (goodsAccount > 1) {
                    goodsAccount -= 1;
                }
                goodsNum.setText(goodsAccount + "");
                itemData.get(position).setGoodNum(goodsAccount + "");
            }
        });

        numAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsAccount = Integer.parseInt(itemData.get(position).getGoodNum());
                if (goodsAccount < 99) {
                    goodsAccount += 1;
                }
                goodsNum.setText(goodsAccount + "");
                itemData.get(position).setGoodNum(goodsAccount + "");
            }
        });

        return convertView;
    }
}
