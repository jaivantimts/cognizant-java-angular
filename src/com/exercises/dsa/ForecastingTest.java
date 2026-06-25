package com.exercises.dsa;

public class ForecastingTest {
    public static void main(String[] args) {
        FinancialForecasting forecast = new FinancialForecasting();

        double initialInvestment = 1000.0;
        double annualGrowthRate = 0.05; // 5% growth per year
        int years = 10;

        System.out.println("--- FINANCIAL FORECASTING ---");
        System.out.println("Initial Investment: $" + initialInvestment);
        System.out.println("Annual Growth Rate: " + (annualGrowthRate * 100) + "%");
        System.out.println("Forecast Period: " + years + " years");
        System.out.println();

        // Method 1: Simple Recursive (O(n))
        long startTime = System.nanoTime();
        double futureValue = forecast.calculateFutureValue(initialInvestment, annualGrowthRate, years);
        long endTime = System.nanoTime();
        
        System.out.println("** Simple Recursive Approach (O(n)) **");
        System.out.println("Future Value after " + years + " years: $" + String.format("%.2f", futureValue));
        System.out.println("Execution Time: " + (endTime - startTime) + " ns");
        System.out.println();

        // Method 2: Optimized Recursive (O(log n))
        startTime = System.nanoTime();
        double futureValueOptimized = forecast.calculateFutureValueOptimized(initialInvestment, annualGrowthRate, years);
        endTime = System.nanoTime();
        
        System.out.println("** Optimized Recursive Approach (O(log n)) **");
        System.out.println("Future Value after " + years + " years: $" + String.format("%.2f", futureValueOptimized));
        System.out.println("Execution Time: " + (endTime - startTime) + " ns");
        System.out.println();

        // Time Complexity Analysis
        System.out.println("--- TIME COMPLEXITY ANALYSIS ---");
        System.out.println("Simple Recursive (O(n)): Performs one recursive call per year.");
        System.out.println("  - Good for small n (e.g., < 1000 years).");
        System.out.println("  - For large n, it can cause stack overflow or be slow.");
        System.out.println();
        System.out.println("Optimized Recursive (O(log n)): Uses exponentiation by squaring.");
        System.out.println("  - Reduces the number of operations dramatically.");
        System.out.println("  - Much faster for large n (e.g., 10,000+ years).");
        System.out.println();
        System.out.println("** Best Practice: Always use the optimized version for large datasets. **");
    }
}