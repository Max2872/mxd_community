package com.project.mxd.mxd_community;

/**
 * Created by maohs on 2018/3/5.
 */

public class BoxItem {
    private int imageId;
    private String boxName;
    private String boxPrice;
    public BoxItem() {}
    public BoxItem(int imageId,String boxName,String boxPrice) {
        this.imageId = imageId;
        this.boxName = boxName;
        this.boxPrice = boxPrice;
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

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public void setBoxPrice(String boxPrice) {
        this.boxPrice = boxPrice;
    }
}
