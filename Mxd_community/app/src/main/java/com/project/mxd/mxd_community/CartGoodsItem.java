package com.project.mxd.mxd_community;

/**
 * Created by mao on 2018/4/25.
 */

public class CartGoodsItem {
    private String goodsName;
    private String goodsPrice;

    public CartGoodsItem() {}

    public CartGoodsItem(String goodsName, String goodsPrice) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

}
