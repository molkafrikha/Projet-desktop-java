package edu.fithnitek.gui;

import edu.fithnitek.entities.Voiture;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


public class VoiturePDFGenerator {
    
    public  void generatePDF(List<Voiture> voitures) throws IOException {
        try (PDDocument document = new PDDocument()) {
            System.out.println("????????????????????????????,");
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            System.out.println("????????????????????????????,");
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            System.out.println("????????????????????????????,");
            PDRectangle pageSize = page.getMediaBox();
            float margin = 50;
            float yStart = pageSize.getHeight() - margin;
            float tableTop = yStart - 50;
            System.out.println("????????????????????????????,");
            
            // Load font
            
            PDFont font = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));
            int fontSize = 12;
            float leading = 1.5f * fontSize;
            
            // Add table headers
            float tableBottom = addTableHeader(contentStream, margin, tableTop, (PDType1Font) font, fontSize);
            
            // Add table rows for each car
            float rowHeight = 20;
            for (Voiture voiture : voitures) {
                tableBottom = addTableRow(contentStream, margin, tableBottom, rowHeight, font, fontSize, voiture);
            }
            
            contentStream.close();
            document.save(new File("voitures.pdf"));
        }
        System.out.println("????????????????????????????,");
    }

   private static float addTableHeader(PDPageContentStream contentStream, float x, float y, PDType1Font font, int fontSize) throws IOException {
    float rowHeight = 20;
    float contentWidth = 0;

    // Calculate the width of each string and add them up to get the total content width
    contentWidth += font.getStringWidth("ID") / 1000 * fontSize;
    contentWidth += font.getStringWidth("Matricule") / 1000 * fontSize * 0.2f;
    contentWidth += font.getStringWidth("Nb places") / 1000 * fontSize;
    contentWidth += font.getStringWidth("Puissance") / 1000 * fontSize * 0.2f;
    contentWidth += font.getStringWidth("Assurance") / 1000 * fontSize * 0.2f;
    contentWidth += font.getStringWidth("Kilometrage") / 1000 * fontSize;
    contentWidth += font.getStringWidth("Date Vidange") / 1000 * fontSize * 0.2f;

    // Draw the text
    contentStream.beginText();
    contentStream.setFont(font, fontSize);
    contentStream.newLineAtOffset(x, y);
    contentStream.showText("ID");
    contentStream.newLineAtOffset(contentWidth * 0.1f, 0);
    contentStream.showText("Matricule");
    contentStream.newLineAtOffset(contentWidth * 0.2f, 0);
    contentStream.showText("Nb places");
    contentStream.newLineAtOffset(contentWidth * 0.1f, 0);
    contentStream.showText("Puissance");
    contentStream.newLineAtOffset(contentWidth * 0.2f, 0);
    contentStream.showText("Assurance");
    contentStream.newLineAtOffset(contentWidth * 0.2f, 0);
    contentStream.showText("Kilometrage");
    contentStream.newLineAtOffset(contentWidth * 0.1f, 0);
    contentStream.showText("Date Vidange");
    contentStream.newLineAtOffset(-contentWidth * 0.9f, -rowHeight);
    contentStream.endText();

    return y - rowHeight;
}

    private static float addTableRow(PDPageContentStream contentStream, float x, float y, float rowHeight, PDFont font, int fontSize, Voiture voiture) throws IOException {
    
        
        //float contentWidth = contentStream.getPage().getMediaBox().getWidth() - 2 * x;
    float contentWidth = 0;
    contentStream.beginText();
    contentStream.setFont(font, fontSize);

    // Display the data for the Voiture object
    contentStream.newLineAtOffset(x, -rowHeight);
    contentStream.showText(Integer.toString(voiture.getId_voiture()));
        
    contentStream.newLineAtOffset(contentWidth * 0.1f, 0);
    contentStream.showText(voiture.getMatricule());
    contentStream.newLineAtOffset(contentWidth * 0.2f, 0);
    contentStream.showText(voiture.getPuissance());
    contentStream.newLineAtOffset(contentWidth * 0.1f, 0);
    contentStream.showText(Integer.toString(voiture.getKilometrage()));
    contentStream.newLineAtOffset(contentWidth * 0.2f, 0);
    contentStream.showText(Integer.toString(voiture.getNbplaces()));
    contentStream.newLineAtOffset(contentWidth * 0.2f, 0);
   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   String formattedDate = dateFormat.format(voiture.getDateAssurance());
   contentStream.showText(formattedDate);
    contentStream.newLineAtOffset(contentWidth * 0.1f, 0);
    SimpleDateFormat dateFormatt = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDatee = dateFormatt.format(voiture.getDateDVidange());
contentStream.showText(formattedDatee);

    contentStream.endText();

    return y - rowHeight;
}
}