package com.demosite;

import org.apache.catalina.Session;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by worri on 6/9/2016.
 */
@Controller
@Scope("session")
public class HomeController {

    @RequestMapping("/")
    public String index(Model model)
    {
        return "index";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String indexPost(@ModelAttribute Contact contact, Model model )
    {
        System.out.println(contact);
        return "index";
    }

    @RequestMapping(value="/resume", method= RequestMethod.GET)
    public String resume(Model model, HttpSession session)
    {
        TreeMap<String, ArrayList<ArrayList<Field>>> map = null;
        if(!resumeQueue.isStarted()) {
            map = Resume.populateResumeFields(false, null);
            resumeQueue.setResume(new Resume());

        }
        else
        {
            map = Resume.populateResumeFields(true, resumeQueue.getResume());
        }
            Queue<String> queue = new LinkedList<String>();
            queue.addAll(map.keySet());

            resumeQueue.setCurrent(queue);
            resumeQueue.setMap(map);

            ArrayList<ArrayList<Field>> fields = resumeQueue.getMap().get(resumeQueue.getCurrent().peek());
            model.addAttribute("formName", "Enter Your Personal Information");
            model.addAttribute("size", fields.size());
            model.addAttribute("fieldsList", fields);
            model.addAttribute("givenPath", "resume.html");

        return "resume";
    }

    private ResumeQueue resumeQueue;

    @RequestMapping(value="/resume", method= RequestMethod.POST)
    public String resumePost(@ModelAttribute("genericdata") GenericData genericdata, BindingResult bindingResult, Model model, HttpServletResponse response)
    {


        if(-1 != resumeQueue.setNext(genericdata))
        {
            resumeQueue.getCurrent().remove();
        }


        ArrayList<ArrayList<Field>> fields = resumeQueue.next();
        model.addAttribute("formName", "Input Your Information");
        model.addAttribute("fieldsList", fields);
        model.addAttribute("size", fields.size());
        if(resumeQueue.getCurrent().size() == 1)
            model.addAttribute("givenPath", "download.html");
        else
            model.addAttribute("givenPath", "resume.html");

        return "resume";
    }

    @RequestMapping(value="/download", method=RequestMethod.POST)
    public String getDownload(@ModelAttribute("genericdata") GenericData genericdata, BindingResult bindingResult)
    {
        resumeQueue.setNext(genericdata);
        resumeQueue.setPdf(PdfCreator.createPDF(resumeQueue.getResume()));
        return "download";
    }

    @RequestMapping(value="/downloaded", method=RequestMethod.POST)
    public void getFile(HttpServletResponse response) {
        try {
            InputStream is = new FileInputStream(resumeQueue.getPdf());

            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @Autowired
    public void setResumeQueue(ResumeQueue rs)
    {
        resumeQueue=rs;
    }
}
