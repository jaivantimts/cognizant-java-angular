package com.exercises.designpatterns;

public class SingletonTest {
    public static void main(String[] args) {
        // Try to get the instance twice
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Check if both references point to the SAME object
        System.out.println("Are both references the same? " + (logger1 == logger2));

        // Use the logger
        logger1.log("Application started.");
        logger2.log("User logged in.");
    }
}