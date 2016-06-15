package com.demosite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java Object for taking in unspecified data from fields in the Thymeleaf template. Has converter methods to return necessary information.
 */
public class GenericData {
    private ArrayList<String> name;
    private ArrayList<String> desc;
    private ArrayList<String> email;
    private ArrayList<String> number;
    private ArrayList<String> degree;
    private ArrayList<String> subject;
    private ArrayList<String> gpa;
    
    //private boolean done;
    private List<String> done;
    private ArrayList<String> loc;
    private ArrayList<String> title;
    private ArrayList<String> date;
    private ArrayList<String> skills;

    public GenericData()
    {
        name = new ArrayList<String>();
        desc = new ArrayList<String>();
        email = new ArrayList<String>();
        number = new ArrayList<String>();
        degree = new ArrayList<String>();
        subject = new ArrayList<String>();
        gpa = new ArrayList<String>();
        title = new ArrayList<String>();
        date = new ArrayList<String>();
        skills = new ArrayList<String>();


    }

    public void setName(ArrayList<String> name) {
        name.removeAll(Arrays.asList("", null));
        this.name = name;
    }

    public void setDesc(ArrayList<String> desc) {
        desc.remove("");
        this.desc = desc;
    }

    public void setEmail(ArrayList<String> email) {
        email.removeAll(Arrays.asList("", null));
        this.email = email;
    }

    public void setNumber(ArrayList<String> number) {
        number.removeAll(Arrays.asList("", null));
        this.number = number;
    }

    public void setDegree(ArrayList<String> degree) {
        degree.removeAll(Arrays.asList("", null));
        this.degree = degree;
    }

    public void setSubject(ArrayList<String> subject) {
        subject.removeAll(Arrays.asList("", null));
        this.subject = subject;
    }

    public void setGpa(ArrayList<String> gpa) {
        gpa.removeAll(Arrays.asList("", null));
        this.gpa = gpa;
    }

    public void setTitle(ArrayList<String> title) {
        title.removeAll(Arrays.asList("", null));
        this.title = title;
    }

    public void setDate(ArrayList<String> date) {
        date.removeAll(Arrays.asList("", null));
        this.date = date;
    }

    public void setSkills(ArrayList<String> skills) {
        skills.removeAll(Arrays.asList("", null));
        this.skills = skills;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<String> getDesc() {
        return desc;
    }

    public String getEmail() {
        return email.get(0);
    }

    public String getNumber() {
        return number.get(0);
    }

    public ArrayList<String> getDegree() {
        return degree;
    }

    public ArrayList<String> getSubject() {
        return subject;
    }

    public String getGpa(int index) {
        return gpa.get(index);
    }


    public List<String> isDone() {
        return done;
    }

    public void setDone(List<String> done) {
        this.done = done;
    }

    public ArrayList<String> getLoc() {
        return loc;
    }

    public void setLoc(ArrayList<String> loc) {
        loc.remove("");
        this.loc=loc;
    }

    public ArrayList<String> getTitle() {
        return title;
    }



    public ArrayList<String> getDate() {
        return date;
    }



    public ArrayList<String> getSkills() {
        return skills;
    }



    public ArrayList<Education> toEducation()
    {
        ArrayList<Education> toRet = new ArrayList<Education>();
        for(int index = 0; index < name.size(); index++)
            toRet.add(new Education(name.get(index), degree.get(index), subject.get(index), gpa.get(index), date.get(index)));
        return toRet;
    }

    public Author toAuthor()
    {
        String name = "";
        String num = "";
        String email = "";
        String desc = "";
        String loc = "";
        if(this.name.size() > 0)
            name = this.name.get(0);
        if(this.number.size() > 0)
            num = this.number.get(0);
        if(this.email.size() > 0)
            email = this.email.get(0);
        if(this.desc.size() > 0)
            desc = this.desc.get(0);
        if(this.loc.size() > 0)
            loc = this.loc.get(0);

        return new Author(name, num, email, desc, loc);
    }

    public ArrayList<Award> toAward()
    {
        ArrayList<Award> toRet = new ArrayList<Award>();
        for(int index = 0; index < name.size(); index++)
            toRet.add(new Award(desc.get(index), name.get(index), date.get(index)));
        return toRet;
    }

    public ArrayList<PastWork> toPastWork()
    {
        ArrayList<PastWork> toRet = new ArrayList<PastWork>();
        for(int index = 0; index < name.size(); index++)
            toRet.add(new PastWork(name.get(index), loc.get(index), date.get(index), title.get(index), desc.get(index)));
        return toRet;
    }

    public ArrayList<Project> toProject()
    {
        ArrayList<Project> toRet = new ArrayList<Project>();
        for(int index = 0; index < name.size(); index++)
            toRet.add((new Project(name.get(index), desc.get(index), date.get(index))));
        return toRet;
    }

}
