package com.project.mxd.mxd_community;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maohs on 2018/2/27.
 */

public class CartFragment extends Fragment {
    private List<CartGoodsItem> itemData = null;
    private Context context;
    private CartGoodsAdapter adapter = null;
    private ListView list;
    private TextView orderSelectAll;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_content,container,false);
        context = getActivity();
        list = (ListView)view.findViewById(R.id.cart_list);
        orderSelectAll = (TextView)view.findViewById(R.id.orderSelectAll);
        Drawable drawable=getResources().getDrawable(R.drawable.goods_normal);
        drawable.setBounds(0,0,70,70);
        orderSelectAll.setCompoundDrawables(drawable,null,null,null);

        return view;
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
        try {
            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("phoneNum")).equals(phoneNum)) {
                        goodsImageId = cursor.getString(cursor.getColumnIndex("goodsImageId"));
                        goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
                        goodsPrice = cursor.getString(cursor.getColumnIndex("goodsPrice"));
                        goodNum = cursor.getString(cursor.getColumnIndex("goodNum"));
                        itemData.add(new CartGoodsItem(goodsImageId,goodsName,goodsPrice,goodNum));
                    }
                }
            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();
        adapter = new CartGoodsAdapter((LinkedList<CartGoodsItem>)itemData,context);
        list.setAdapter(adapter);
    }
}

