package com.demosite;

/**
 * Created by worri on 6/9/2016.
 */
public class PastWork {
    private String employer;
    private String location;
    private String dates;
    private String title;
    private String desc;

    public PastWork(String employer, String location, String dates, String title, String desc) {
        this.employer = employer;
        this.location = location;
        this.dates = dates;
        this.title = title;
        this.desc = desc;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
