package com.project.mxd.mxd_community;

/**
 * Created by mxd on 2018/4/25.
 */

public class CartGoodsItem {
    private String goodsImageId;
    private String goodsName;
    private String goodsPrice;
    private String goodNum;
    private boolean isSelected;

    public CartGoodsItem() {}

    public CartGoodsItem(boolean isSelected, String goodsImageId,String goodsName, String goodsPrice,String goodNum) {
        this.goodsImageId = goodsImageId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodNum = goodNum;
        this.isSelected = isSelected;
    }

    public boolean getIsSelected() {
        return isSelected;
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

    public String getGoodNum() {
        return goodNum;
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

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
