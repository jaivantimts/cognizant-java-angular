package com.exercises.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SLF4J Exercise 1: Logging Error Messages and Warning Levels
 */
public class LoggingExample {

    // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        // Example 1: Logging at different levels
        logger.info("Application started...");
        logger.debug("Debugging information (only shown if debug level enabled)");

        // Example 2: Warning level log
        int diskSpace = 15; // in GB
        if (diskSpace < 20) {
            logger.warn("Low disk space! Only {} GB remaining.", diskSpace);
        }

        // Example 3: Error level log with exception
        try {
            int result = 10 / 0; // This will throw ArithmeticException
        } catch (ArithmeticException e) {
            logger.error("An error occurred while performing division: {}", e.getMessage(), e);
        }

        // Example 4: Parameterized logging (multiple parameters)
        String user = "Alice";
        String action = "login";
        logger.info("User '{}' performed '{}' action.", user, action);

        // Example 5: Error message for a failed operation
        try {
            processPayment(500);
        } catch (Exception e) {
            logger.error("Payment processing failed for amount $500. Reason: {}", e.getMessage());
        }

        logger.info("Application finished.");
    }

    // Simulate a payment method that can fail
    public static void processPayment(double amount) throws Exception {
        if (amount > 1000) {
            throw new Exception("Amount exceeds daily limit");
        }
        // Simulate a failure for demo purposes
        if (amount < 100) {
            throw new Exception("Amount is below minimum threshold");
        }
        System.out.println("Payment of $" + amount + " processed successfully.");
    }
}