package com.demosite;

/**
 * Created by worri on 6/9/2016.
 */
public class Education {
    private String name;
    private String degree;
    private String subject;
    private String gpa;
    private String dates;

    public Education(String name, String degree, String subject, String gpa, String dates) {
        this.name = name;
        this.degree = degree;
        this.subject = subject;
        setGpa(gpa);
        this.dates = dates;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
}
