package com.project.mxd.mxd_community;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mxd on 2018/2/27.
 */

public class CartFragment extends Fragment {
    private List<CartGoodsItem> itemData = null;
    private Context context;
    private CartGoodsAdapter adapter = null;
    private ListView list;
    private TextView orderSelectAll;
    private boolean selectAll = false;
    private TextView orderAmount;
    private TextView orderSubmit;
    private Float totalPrice;
    private String selectCount = "0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_content,container,false);
        context = getActivity();
        list = (ListView)view.findViewById(R.id.cart_list);
        orderSelectAll = (TextView)view.findViewById(R.id.orderSelectAll);
        orderAmount = (TextView)view.findViewById(R.id.orderAmount);
        orderSubmit = (TextView)view.findViewById(R.id.orderSubmit);

        orderSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAll = !selectAll;
                if (selectAll) {
                    Drawable drawable=getResources().getDrawable(R.drawable.goods_selected);
                    drawable.setBounds(0,0,70,70);
                    orderSelectAll.setCompoundDrawables(drawable,null,null,null);
                    orderAmount.setText("共：" + itemData.size() + "件");
                    updateData(true);
                }else {
                    Drawable drawable=getResources().getDrawable(R.drawable.goods_normal);
                    drawable.setBounds(0,0,70,70);
                    orderSelectAll.setCompoundDrawables(drawable,null,null,null);
                    orderAmount.setText("共：0件");
                    updateData(false);
                }
            }
        });
        orderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float tempPrice = calculatePrice();
                if (selectCount.equals("0")) {
                    customToast("请选择商品",2);
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("imageId",R.drawable.order_goods_bg);
                intent.putExtra("name","DIY礼盒——用心创造");
                intent.putExtra("price", tempPrice + "");
                intent.setClass(getActivity(), EditOrderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
        return view;
    }

    private void updateData(boolean isSelect) {
        for (int i=0;i<itemData.size();i++) {
            itemData.get(i).setSelected(isSelect);
        }
        adapter.notifyDataSetChanged();
    }

    private Float calculatePrice() {
        totalPrice = 0.0f;
        for (int i=0;i<itemData.size();i++) {
            if (itemData.get(i).getIsSelected()) {
                selectCount = Integer.parseInt(selectCount) + 1 + "";
                totalPrice += Float.parseFloat(itemData.get(i).getGoodsPrice()) *Integer.parseInt(itemData.get(i).getGoodNum());
            }
        }
        return totalPrice;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        itemData = new LinkedList();
        SharedPreferences preferences = getActivity().getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        String phoneNum = preferences.getString("phoneNum","");
        boolean shouldLogin = preferences.getBoolean("shouldLogin",false);
        CommunityOpenHelper communityOpenHelper = new CommunityOpenHelper(getActivity(),"community.db",null,1);
        SQLiteDatabase db = communityOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("cartGoodsInfo",null,null,null,null,null,null);

        String goodsImageId;
        String goodsName;
        String goodsPrice;
        String goodNum;
        boolean isSelect;
        try {
            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("phoneNum")).equals(phoneNum)) {
                        if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("isSelected"))) == 1) {
                            isSelect = true;
                        }else {
                            isSelect = false;
                        }
                        goodsImageId = cursor.getString(cursor.getColumnIndex("goodsImageId"));
                        goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
                        goodsPrice = cursor.getString(cursor.getColumnIndex("goodsPrice"));
                        goodNum = cursor.getString(cursor.getColumnIndex("goodNum"));
                        itemData.add(new CartGoodsItem(isSelect,goodsImageId,goodsName,goodsPrice,goodNum));
                    }
                }
            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();
        if (orderSelectAll != null) {
            Drawable drawable=getResources().getDrawable(R.drawable.goods_normal);
            drawable.setBounds(0,0,70,70);
            orderSelectAll.setCompoundDrawables(drawable,null,null,null);
            orderAmount.setText("共：0件");
        }
        adapter = new CartGoodsAdapter((LinkedList<CartGoodsItem>)itemData,context);
        list.setAdapter(adapter);
    }

    private void customToast(String string,int showTime) {
        Toast toast = Toast.makeText(getActivity(),string,showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
}

