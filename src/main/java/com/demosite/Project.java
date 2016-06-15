package com.demosite;

/**
 * Created by worri on 6/9/2016.
 */
public class Project {
    private String name;
    private String description;
    private String dates;

    public Project(String name, String description, String dates) {
        this.name = name;
        this.description = description;
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}
