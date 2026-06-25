package com.exercises.dsa;

public class FinancialForecasting {

    /**
     * Recursive method to calculate future value.
     * Time Complexity: O(n) where n is the number of years.
     * 
     * @param initialValue The starting amount
     * @param growthRate The annual growth rate (e.g., 0.05 for 5%)
     * @param years The number of years to forecast
     * @return The future value after 'years' years
     */
    public double calculateFutureValue(double initialValue, double growthRate, int years) {
        // Base case: if no years left, return the current value
        if (years == 0) {
            return initialValue;
        }
        
        // Recursive case: apply growth for one year and reduce years by 1
        return calculateFutureValue(initialValue * (1 + growthRate), growthRate, years - 1);
    }

    /**
     * Optimized recursive method using divide and conquer.
     * This reduces time complexity to O(log n) by calculating (1+rate)^years
     * using exponentiation by squaring.
     * 
     * @param initialValue The starting amount
     * @param growthRate The annual growth rate (e.g., 0.05 for 5%)
     * @param years The number of years to forecast
     * @return The future value after 'years' years
     */
    public double calculateFutureValueOptimized(double initialValue, double growthRate, int years) {
        // Use exponentiation by squaring to calculate (1+rate)^years
        double factor = power(1 + growthRate, years);
        return initialValue * factor;
    }

    /**
     * Helper method: Recursive exponentiation by squaring.
     * Time Complexity: O(log n)
     */
    private double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent % 2 == 0) {
            // If exponent is even: base^exponent = (base^(exponent/2))^2
            double half = power(base, exponent / 2);
            return half * half;
        } else {
            // If exponent is odd: base^exponent = base * base^(exponent-1)
            return base * power(base, exponent - 1);
        }
    }
}