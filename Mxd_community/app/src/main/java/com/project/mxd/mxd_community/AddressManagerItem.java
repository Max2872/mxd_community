package com.project.mxd.mxd_community;

/**
 * Created by mxd on 2018/4/27.
 */

public class AddressManagerItem {
    private String receiver;
    private String phoneNum;
    private String address;

    public AddressManagerItem() {}

    public AddressManagerItem(String receiver, String phoneNum, String address) {
        this.receiver = receiver;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }


    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
