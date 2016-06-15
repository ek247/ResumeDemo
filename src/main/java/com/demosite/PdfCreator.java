package com.demosite;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by worri on 6/13/2016.
 */
public class PdfCreator {



    public static File createPDF(Resume resume)
    {

        try {

            if(!Files.exists(Paths.get("/pdfs/")))
                Files.createDirectory(Paths.get("/pdfs/"));


            if( Files.list(Paths.get("/pdfs/")).count() >= 200) //Every 200 files clean the directory
            {
                Files.newDirectoryStream( Paths.get("/pdfs/")).forEach( file -> {
                    try { if(file.getFileName().toString().contains(".pdf")) Files.delete( file ); }
                    catch ( IOException e ) { throw new UncheckedIOException(e); }
                } );            }

            File f = new File("/pdfs/"+resume.getAuthor().getName().replace(" ", "_")+(int)(Math.random()*10000)+".pdf");
            f.createNewFile();

            System.out.println(f.getAbsolutePath());

            Document doc = new Document(PageSize.LETTER);
            PdfWriter.getInstance(doc, new FileOutputStream(f.getAbsolutePath()));
            doc.open();

            //Edit pdf and insert resume stuff
            Font zapfdingbats = new Font(Font.FontFamily.ZAPFDINGBATS, 4);
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);
            LineSeparator line = new LineSeparator();


            //Header
            Paragraph header = new Paragraph();
            header.setAlignment(Element.ALIGN_CENTER);
            header.add(new Chunk(resume.getAuthor().getName(), new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD)));
            header.add(Chunk.NEWLINE);
            header.add(new Chunk(resume.getAuthor().getLoc() + " ", font));
            header.add(bullet);
            header.add(new Chunk(" " + resume.getAuthor().getEmail() + " ", font));
            header.add(bullet);
            header.add(new Chunk(" " + resume.getAuthor().getNumber(), font));
            line.setOffset(-2);
            doc.add(header);
            doc.add(line);

            //Body
            Paragraph body = new Paragraph();
            body.add(new Chunk("Objective: ", boldFont));
            body.add(new Chunk(resume.getAuthor().getObjective(), font));
            body.add(Chunk.NEWLINE);
            body.add(Chunk.NEWLINE);

            //Education
            if(resume.getEducation().size()!=0) {
                body.add(new Chunk("Education: ", boldFont));
                body.add(line);
                Paragraph education = new Paragraph();
                education.setIndentationLeft(50);
                for (Education e : resume.getEducation()) {
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100);
                    table.addCell(makeCell(e.getName(), PdfPCell.ALIGN_LEFT));
                    table.addCell(makeCell(e.getDates(), PdfPCell.ALIGN_RIGHT));
                    table.addCell(makeCell(e.getDegree() + " in " + e.getSubject(), PdfPCell.ALIGN_LEFT));
                    table.addCell(makeCell("GPA: " + e.getGpa(), PdfPCell.ALIGN_RIGHT));
                    education.add(table);
                    education.add(Chunk.NEWLINE);
                }
                body.add(education);
            }
                //Work Experience
            if(resume.getWork().size() != 0) {
                body.add(new Chunk("Work Experience: ", boldFont));
                body.add(line);
                Paragraph work = new Paragraph();
                work.setIndentationLeft(50);
                for (PastWork e : resume.getWork()) {
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100);
                    table.addCell(makeCell(e.getEmployer(), PdfPCell.ALIGN_LEFT));
                    table.addCell(makeCell(e.getLocation(), PdfPCell.ALIGN_RIGHT));
                    table.addCell(makeCell(e.getTitle(), PdfPCell.ALIGN_LEFT));
                    table.addCell(makeCell(e.getDates(), PdfPCell.ALIGN_RIGHT));
                    work.add(table);
                    Paragraph desc = new Paragraph(e.getDesc(), font);
                    desc.setIndentationLeft(10);
                    work.add(desc);
                    work.add(Chunk.NEWLINE);

                }
                body.add(work);
            }
            if(resume.getProjects().size() != 0)
                //Projects
            body.add(new Chunk("Personal Projects: ", boldFont));
            body.add(line);
            Paragraph projects = new Paragraph();
            projects.setIndentationLeft(50);
            for(Project e : resume.getProjects()) {
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.addCell(makeCell(e.getName(), PdfPCell.ALIGN_LEFT));
                table.addCell(makeCell(e.getDates(), PdfPCell.ALIGN_RIGHT));
                projects.add(table);
                Paragraph desc = new Paragraph(e.getDescription(), font);
                desc.setIndentationLeft(10);
                projects.add(desc);
                projects.add(Chunk.NEWLINE);

            }
            body.add(projects);

                //Awards
            if(resume.getAwards().size() != 0) {
                body.add(new Chunk("Awards: ", boldFont));
                body.add(line);
                Paragraph awards = new Paragraph();
                awards.setIndentationLeft(50);
                for (Award e : resume.getAwards()) {
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100);
                    table.addCell(makeCell(e.getAwardName(), PdfPCell.ALIGN_LEFT));
                    table.addCell(makeCell(e.getDate(), PdfPCell.ALIGN_RIGHT));
                    awards.add(table);
                    Paragraph desc = new Paragraph(e.getDesc(), font);
                    desc.setIndentationLeft(10);
                    awards.add(desc);
                    awards.add(Chunk.NEWLINE);
                }
                body.add(awards);
            }
                //Skills
            if(resume.getSkills().size() != 0) {
                body.add(new Chunk("Skills: ", boldFont));
                body.add(line);
                body.add(Chunk.NEWLINE);
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                for(int i = 0; i < resume.getSkills().size(); i++) {
                    int a = -1;
                    if(i%2==0)
                        a=PdfPCell.ALIGN_LEFT;
                    else
                        a=PdfPCell.ALIGN_CENTER;
                    table.addCell(makeCell("•"+resume.getSkills().get(i), a));
                }
                body.add(table);
            }


            doc.add(body);

            doc.close();

            return f;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }



        return null;
    }

    //Taken from http://developers.itextpdf.com/question/how-align-two-paragraphs-left-and-right-same-line, Thanks!
    private static PdfPCell makeCell(String msg, int align)
    {
        PdfPCell cell = new PdfPCell(new Phrase(msg, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
        cell.setPadding(0);
        cell.setHorizontalAlignment(align);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

}
