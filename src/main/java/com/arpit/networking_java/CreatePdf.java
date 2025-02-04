package com.arpit.networking_java;


import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CreatePdf {
  public static void main(String[] args) {

    // PDDocument pdfdoc = new PDDocument();
    // PDPage page = new PDPage();


    // try {

    //   PDPageContentStream contentStream = new PDPageContentStream(pdfdoc, page);
    //   contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
    //         contentStream.beginText();
    //         contentStream.newLineAtOffset(100, 700); // Position where text starts
    //         contentStream.showText("Hello, Arpit! This is a sample PDF with text.");
    //         contentStream.endText();
    //   contentStream.close();

    //   pdfdoc.addPage(page);

    //       pdfdoc.save("/home/arpit-parekh/files/Sample.pdf");
    //       pdfdoc.close();
    //   } catch (IOException ex) {
    //   }

    // System.out.println("PDF created");

    String filePath = "/home/arpit-parekh/files/SalesReport.pdf";

        try (PDDocument pdfdoc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            pdfdoc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(pdfdoc, page);

            // Define starting point and table properties
            float margin = 50;
            float yStart = 750; // Start drawing from top
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = yStart;
            float rowHeight = 20;
            float tableHeight = rowHeight * 5; // 5 rows (including header)
            float colWidth = tableWidth / 4; // 4 columns
            int rows = 5;
            int cols = 4;

            // Draw Table Borders
            contentStream.setLineWidth(1f);
            for (int i = 0; i <= rows; i++) {
                contentStream.moveTo(margin, yStart - (i * rowHeight));
                contentStream.lineTo(margin + tableWidth, yStart - (i * rowHeight));
            }
            for (int i = 0; i <= cols; i++) {
                contentStream.moveTo(margin + (i * colWidth), yStart);
                contentStream.lineTo(margin + (i * colWidth), yStart - tableHeight);
            }
            contentStream.stroke();

            // Set Font
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Table Headers
            String[] headers = {"Product", "Quantity", "Price", "Total"};
            yPosition -= 15;
            float textX = margin + 5;
            for (String header : headers) {
                contentStream.beginText();
                contentStream.newLineAtOffset(textX, yPosition);
                contentStream.showText(header);
                contentStream.endText();
                textX += colWidth;
            }

            // Table Data
            String[][] data = {
                {"Laptop", "2", "$800", "$1600"},
                {"Phone", "5", "$500", "$2500"},
                {"Headphones", "10", "$50", "$500"},
                {"Keyboard", "4", "$30", "$120"}
            };

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (String[] row : data) {
                yPosition -= rowHeight;
                textX = margin + 5;
                for (String cell : row) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(textX, yPosition);
                    contentStream.showText(cell);
                    contentStream.endText();
                    textX += colWidth;
                }
            }

            // Close content stream and save PDF
            contentStream.close();
            pdfdoc.save(filePath);
            System.out.println("Sales Table PDF created successfully: " + filePath);
          } catch (IOException e) {
            e.printStackTrace();
          }


  }
}
