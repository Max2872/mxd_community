package com.project.mxd.mxd_community;

/**
 * Created by mxd on 2018/3/5.
 */

public class OrderManagerItem {
    private String goodsImageId;
    private String goodsName;
    private String goodsPrice;

    private String recieverName;
    private String recieverPhone;
    private String recieverAddress;


    public OrderManagerItem() {}

    public OrderManagerItem(String goodsImageId,String goodsName, String goodsPrice, String recieverName,String recieverPhone,String recieverAddress) {
        this.goodsImageId = goodsImageId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;

        this.recieverName = recieverName;
        this.recieverPhone = recieverPhone;
        this.recieverAddress = recieverAddress;
    }

    public String getGoodsImageId() {
        return goodsImageId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public String getRecieverPhone() {
        return recieverPhone;
    }

    public String getRecieverAddress() {
        return recieverAddress;
    }

    public void setGoodsImageId(String goodsImageId) {
        this.goodsImageId = goodsImageId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public void setRecieverPhone(String recieverPhone) {
        this.recieverPhone = recieverPhone;
    }

    public void setRecieverAddress(String recieverAddress) {
        this.recieverAddress = recieverAddress;
    }
}
