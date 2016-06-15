package com.demosite;

/**
 * Created by worri on 6/9/2016.
 */
public class Author {
    private String name;
    private String number;
    private String email;
    private String objective;
    private String loc;

    public Author(String name, String number, String email, String objective, String loc) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.objective = objective;
        this.loc = loc;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", objective='" + objective + '\'' +
                '}';
    }
}
