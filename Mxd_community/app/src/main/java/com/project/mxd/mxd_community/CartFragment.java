package com.project.mxd.mxd_community;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

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
    private ImageView backImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_content,container,false);
        context = getActivity();
        list = (ListView)view.findViewById(R.id.cart_list);

        itemData = new LinkedList<CartGoodsItem>();
        itemData.add(new CartGoodsItem("礼盒1","￥299.0元"));
        itemData.add(new CartGoodsItem("礼盒2","￥199.0元"));
        itemData.add(new CartGoodsItem("礼盒3","￥99.0元"));
        itemData.add(new CartGoodsItem("礼盒4","￥89.0元"));
        itemData.add(new CartGoodsItem("礼盒5","￥69.0元"));
        itemData.add(new CartGoodsItem("礼盒6","￥99.0元"));
        adapter = new CartGoodsAdapter((LinkedList<CartGoodsItem>)itemData,context);
        list.setAdapter(adapter);
        return view;
    }
}
