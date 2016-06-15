package com.demosite;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
    Resume class that stores all data needed and generates fields needed to populate a form to fill the resume.
 */
public class Resume {
    private Author author;

    private ArrayList<Education> education;
    private ArrayList<PastWork> work;
    private ArrayList<String> skills;
    private ArrayList<Award> awards;
    private ArrayList<Project> projects;

    public Resume() {
        education = new ArrayList<Education>();
        work = new ArrayList<PastWork>();
        skills = new ArrayList<String>();
        awards = new ArrayList<Award>();
        projects = new ArrayList<Project>();
    }

    static TreeMap<String, ArrayList<ArrayList<Field>>> populateResumeFields(boolean fillFromResume, Resume r)
    {
        TreeMap<String, ArrayList<ArrayList<Field>>> fields = new TreeMap<String, ArrayList<ArrayList<Field>>>();

        ArrayList<ArrayList<Field>> toAdd = new ArrayList<ArrayList<Field>>();
        ArrayList<Field> tmp = new ArrayList<Field>();

        //Get projects
        for(int i = 0; i < 4; i++)
        {
            Project curr = new Project("","","");
            if(fillFromResume && (i < r.projects.size()))
                curr = r.projects.get(i);

            tmp.add(new Field("name","text", 20, "Name of Project", curr.getName()));
            tmp.add(new Field("desc","textarea", 20, "Description", curr.getDescription()));
            tmp.add(new Field("date","text", 20, "Dates worked on", curr.getDates()));
            toAdd.add(tmp);
            tmp = new ArrayList<Field>();
        }

        fields.put("z", toAdd); //Don't ask why I use these as keys. Just don't look.
        toAdd = new ArrayList<ArrayList<Field>>();

        //Get awards
        for(int i = 0; i < 4; i++)
        {
            Award curr = new Award("","","");
            if(fillFromResume && (i < r.awards.size()))
                curr = r.awards.get(i);

            tmp.add(new Field("name","text", 20, "Name of Award", curr.getAwardName()));
            tmp.add(new Field("desc","textarea", 20, "Description", curr.getDesc()));
            tmp.add(new Field("date","text", 20, "Date Awarded", curr.getDate()));
            toAdd.add(tmp);
            tmp = new ArrayList<Field>();
        }

        fields.put("y", toAdd);
        toAdd = new ArrayList<ArrayList<Field>>();

        //Get Skills
        ArrayList<String> curr =
        Stream.of("", "", "", "", "", "", "", "").collect(Collectors.toCollection(ArrayList::new)); //Fill arraylist with empty strings as default
        if(fillFromResume) {
            curr = r.getSkills();
            int size = curr.size();
            for(int i = 8-size; i>0; i--)
                curr.add("");
        }
        for(int i = 0; i < 8; i++) {
            tmp.add(new Field("skills", "text", 100, "Skill #"+(i+1), curr.get(i)));
            toAdd.add(tmp);
            tmp = new ArrayList<Field>();
        }

        fields.put("x", toAdd);
        toAdd = new ArrayList<ArrayList<Field>>();

        //Get work experience
        for(int i = 0; i < 3; i++)
        {
            PastWork current = new PastWork("","","","","");
            if(fillFromResume && (i < r.work.size()))
                current = r.work.get(i);

            tmp.add(new Field("name","text", 20, "Employer", current.getEmployer()));
            tmp.add(new Field("loc","text", 20, "Location", current.getLocation()));
            tmp.add(new Field("date","text", 20, "Dates Worked", current.getDates()));
            tmp.add(new Field("title","text", 20, "Title", current.getTitle()));
            tmp.add(new Field("desc","textarea", 20, "Description", current.getDesc()));
            toAdd.add(tmp);
            tmp = new ArrayList<Field>();
        }

        fields.put("w", toAdd);
        toAdd = new ArrayList<ArrayList<Field>>();

        //Get education
        for(int i = 0; i < 3; i++)
        {
            Education current = new Education("","","","","");
            if(fillFromResume && (i < r.education.size()))
                current = r.education.get(i);

            tmp.add(new Field("name","text", 20, "School Name", current.getName()));
            tmp.add(new Field("degree","text", 20, "Degree", current.getDegree()));
            tmp.add(new Field("subject","text", 20, "Subject", current.getSubject()));
            tmp.add(new Field("gpa","text", 20, "gpa", (current.getGpa())));
            tmp.add(new Field("date", "text", 20, "Date of Graduation", current.getDates()));
            toAdd.add(tmp);
            tmp = new ArrayList<Field>();
        }

        fields.put("e", toAdd);
        toAdd = new ArrayList<ArrayList<Field>>();

        Author current = new Author("", "", "", "", "");
        if(fillFromResume && r.author != null)
            current = r.author;

        //Get the Author + objective. Feels like bad design, ask someone about this
        tmp.add(new Field("name","text", 20, "Full Name", current.getName()));
        tmp.add(new Field("number","text", 20, "Phone Number", current.getNumber()));
        tmp.add(new Field("email","text", 20, "Email", current.getEmail()));
        tmp.add(new Field("loc","text", 20, "Where do you live?", current.getLoc()));
        tmp.add(new Field("desc","textarea", 20, "Objective", current.getObjective()));
        toAdd.add(tmp);

        fields.put("a", toAdd);
        toAdd = new ArrayList<ArrayList<Field>>();

        return fields;
    }

    ArrayList<Education> getEducation() {
        return education;
    }

    Author getAuthor() {
        return author;
    }

    void setAuthor(Author author) {
        this.author = author;
    }

    void addEducation(ArrayList<Education> education) {
        this.education = (education);
    }

    ArrayList<PastWork> getWork() {
        return work;
    }

    void addWork(ArrayList<PastWork> work) {
        this.work = (work);
    }

    ArrayList<String> getSkills() {
        return skills;
    }

    void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    ArrayList<Award> getAwards() {
        return awards;
    }

    void addAwards(ArrayList<Award> awards) {
        this.awards = (awards);
    }

    ArrayList<Project> getProjects() {
        return projects;
    }

    void addProjects(ArrayList<Project> projects) {
        this.projects = (projects);
    }


}
