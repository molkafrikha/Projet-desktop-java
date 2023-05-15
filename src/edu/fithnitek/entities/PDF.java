/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.entities;
//import com.itextpdf.io.image.ImageDataFactory;
import edu.fithnitek.entities.Sponsor;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.sun.scenario.effect.ImageData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * @author waelb
 */
public class PDF {
    


    public void GeneratePdf(String filename, Sponsor s, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        //document.add();
        Image image = Image.getInstance("edu/fithnitek/image/aaa.png" );  
        document.add(image);
        
        document.add(new Paragraph("Date :"+LocalDateTime.now()));
        document.add(new Paragraph("Le sponsor :"+s.getSponsore()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("____________________________________________________________________________________________________________________"));

        document.add(new Paragraph("L'identifiant de ce Sponsor  :" + s.getId()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Le nom du Société :" + s.getSponsore()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("La date de signature du contrat:" + s.getDateSignature()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("L'adresse de la société :" + s.getAdresse()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Le montant donné par cette société :" + s.getMontant()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("L'adresse EMAIL: " + s.getEmail()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                              Fi Thnitek                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
}