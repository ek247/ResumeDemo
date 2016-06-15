package com.demosite;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * Created by worri on 6/9/2016.
 */
@Service
@Scope("session")
public class ResumeQueue{
    //General idea is only peek the

    private Queue<String> current;
    private TreeMap<String, ArrayList<ArrayList<Field>>> map;
    private Resume resume;
    private boolean started = false;
    private File pdf;

    public File getPdf() {
        return pdf;
    }

    public void setPdf(File pdf) {
        this.pdf = pdf;
    }

    public ResumeQueue()
    {}

    public ResumeQueue(Queue<String> current, Resume resume) {
        this.current = current;
        this.resume = resume;
    }


    public Queue<String> getCurrent() {
        return current;
    }

    public void setCurrent(Queue<String> current) {
        started = true;
        this.current = current;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public TreeMap<String, ArrayList<ArrayList<Field>>> getMap() {
        return map;
    }

    public void setMap(TreeMap<String, ArrayList<ArrayList<Field>>> map) {
        this.map = map;
    }

    public ArrayList<ArrayList<Field>> next()
    {
        return map.get(current.peek());
    }

    public int setNext(GenericData next){
        if(current.isEmpty())
            return 1;
        try {
            String obj = current.peek();
            if (obj.contains("a")) //Author
                resume.setAuthor(next.toAuthor());
            else if (obj.contains("e")) //Education
            {
                resume.addEducation(next.toEducation());
            } else if (obj.contains("w")) {
                resume.addWork(next.toPastWork());
            } else if (obj.contains("x")) {
                ArrayList<String> skills = next.getSkills();
                resume.setSkills(skills);
            } else if (obj.contains("y")) {
                resume.addAwards(next.toAward());
            } else if (obj.contains("z")) {
                resume.addProjects(next.toProject());
                return 1;
            } else {
                System.err.println("Shouldn't be here"); //Throw exception here
                return 1; //Finished building resume.
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            return -1;
        }

        return 0; //Still more to add
    }

    public boolean isStarted() {
        return started;
    }

    //Delete file when session finishes and this is finalized
    @Override
    public void finalize() throws Throwable
    {
        super.finalize();
        if(pdf != null)
            if(pdf.exists())
                pdf.delete();
    }


}
