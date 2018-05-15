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
import android.view.View.OnClickListener;

import java.util.LinkedList;

/**
 * Created by mao on 2018/4/27.
 */

public class AddressManagerAdapter extends BaseAdapter implements OnClickListener {
    private LinkedList<AddressManagerItem> itemData;
    private Context itemContext;
    private int selectedIndex = 0;
    private MyClickListener mListener;

    //自定义接口，用于回调按钮点击事件到Activity
    public interface MyClickListener{
        public void clickListener(View v);
    }


    public AddressManagerAdapter(LinkedList<AddressManagerItem>itemData,Context itemContext,MyClickListener listener) {
        this.itemData = itemData;
        this.itemContext = itemContext;
        this.mListener = listener;
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
        convertView = LayoutInflater.from(itemContext).inflate(R.layout.address_manager_item,parent,false);
        TextView reciever = (TextView) convertView.findViewById(R.id.reciever);
        TextView phoneNum = (TextView) convertView.findViewById(R.id.phoneNum);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        final ImageView addressSelectedImage = (ImageView) convertView.findViewById(R.id.addressSelectedImage);
        ImageView addressEditImage = (ImageView) convertView.findViewById(R.id.addressEditImage);
        ImageView addressDeleteImage = (ImageView) convertView.findViewById(R.id.addressDeleteImage);
        addressDeleteImage.setTag(position);
        reciever.setText(itemData.get(position).getReceiver());
        phoneNum.setText(itemData.get(position).getPhoneNum());
        address.setText(itemData.get(position).getAddress());

        if (selectedIndex == position) {
            addressSelectedImage.setImageResource(R.drawable.address_selected);
        }

        addressSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex = position;
                notifyDataSetChanged();
            }
        });
        addressEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemContext,AddressEditActivity.class);
                intent.putExtra("isSelected",(selectedIndex == position));
                intent.putExtra("reciever",itemData.get(position).getReceiver());
                intent.putExtra("phoneNum",itemData.get(position).getPhoneNum());
                intent.putExtra("address",itemData.get(position).getAddress());
                itemContext.startActivity(intent);
            }
        });
        addressDeleteImage.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        mListener.clickListener(v);
    }
}
