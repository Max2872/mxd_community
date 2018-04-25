package com.project.mxd.mxd_community;

/**
 * Created by mao on 2018/4/25.
 */

public class RemindItem {
    private String celebrateTime;
    private String earlyDays;
    private String remark;

    public RemindItem() {}

    public RemindItem(String celebrateTime, String earlyDays, String remark) {
        this.celebrateTime = celebrateTime;
        this.earlyDays = earlyDays;
        this.remark = remark;
    }

    public String getCelebrateTime() {
        return celebrateTime;
    }

    public String getEarlyDays() {
        return earlyDays;
    }

    public String getRemark() {
        return remark;
    }

    public void setCelebrateTime(String celebrateTime) {
        this.celebrateTime = celebrateTime;
    }

    public void setEarlyDays(String earlyDays) {
        this.earlyDays = earlyDays;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
