package com.exercises.designpatterns;

public class FactoryTest {
    public static void main(String[] args) {
        System.out.println("--- Factory Method Demo ---");

        // Create a Word document using its factory
        DocumentFactory wordFactory = new WordFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.save();

        System.out.println();

        // Create a PDF document using its factory
        DocumentFactory pdfFactory = new PdfFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.save();

        System.out.println();

        // Create an Excel document using its factory
        DocumentFactory excelFactory = new ExcelFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.save();
    }
}