package com.project.mxd.mxd_community;

/**
 * Created by mxd on 2018/3/5.
 */

public class GiftBoxItem {
    private int imageId;
    private String boxName;
    private String boxPrice;
    private int index;
    private String whoString;
    public GiftBoxItem() {}
    public GiftBoxItem(int imageId, String boxName, String boxPrice,int index,String whoString) {
        this.imageId = imageId;
        this.boxName = boxName;
        this.boxPrice = boxPrice;
        this.index = index;
        this.whoString = whoString;
    }

    public int getIndex() {
        return index;
    }

    public int getImageId() {
        return imageId;
    }

    public String getBoxName() {
        return boxName;
    }

    public String getBoxPrice() {
        return boxPrice;
    }

    public String getWhoString() {
        return whoString;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public void setBoxPrice(String boxPrice) {
        this.boxPrice = boxPrice;
    }

    public void setWhoString(String whoString) {
        this.whoString = whoString;
    }
}
