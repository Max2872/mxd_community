package com.project.mxd.mxd_community;

/**
 * Created by maohs on 2018/3/5.
 */

public class OrderManagerItem {
    private String goodsName;
    private String goodsPrice;
    private String receiver;

    public OrderManagerItem() {}

    public OrderManagerItem(String goodsName, String goodsPrice, String receiver) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.receiver = receiver;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
