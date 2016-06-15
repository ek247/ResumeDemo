package com.demosite;

/**
 * Created by worri on 6/9/2016.
 */
public class Award {
    private String desc;
    private String awardName;
    private String date;

    public Award(String desc, String awardName, String date) {
        this.desc = desc;
        this.awardName = awardName;
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
