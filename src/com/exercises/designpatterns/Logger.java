package com.exercises.designpatterns;

public class Logger {

    // 1. Private static instance (created once when class loads)
    private static final Logger instance = new Logger();

    // 2. Private constructor - prevents anyone from creating another instance
    private Logger() {
        System.out.println("Logger instance created.");
    }

    // 3. Public static getter - returns the single instance
    public static Logger getInstance() {
        return instance;
    }

    // 4. A simple method to test logging
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}