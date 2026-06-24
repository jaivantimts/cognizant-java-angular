package com.exercises.designpatterns;

public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel spreadsheet...");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document as .xlsx");
    }
}