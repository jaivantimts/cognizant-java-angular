package com.exercises.dsa;

public class SearchTest {
    public static void main(String[] args) {
        // Create an array of products (unsorted)
        Product[] products = new Product[] {
            new Product(105, "Laptop", "Electronics"),
            new Product(102, "T-Shirt", "Apparel"),
            new Product(108, "Book", "Stationery"),
            new Product(101, "Phone", "Electronics"),
            new Product(104, "Shoes", "Apparel")
        };

        // Create a SORTED array (by productId) for binary search
        Product[] sortedProducts = new Product[] {
            new Product(101, "Phone", "Electronics"),
            new Product(102, "T-Shirt", "Apparel"),
            new Product(104, "Shoes", "Apparel"),
            new Product(105, "Laptop", "Electronics"),
            new Product(108, "Book", "Stationery")
        };

        SearchAlgorithms search = new SearchAlgorithms();

        // --- Test Linear Search ---
        System.out.println("--- LINEAR SEARCH (O(n)) ---");
        Product foundLinear = search.linearSearch(products, 105);
        if (foundLinear != null) {
            System.out.println("Found: " + foundLinear);
        } else {
            System.out.println("Product not found.");
        }

        // Test searching for a non-existent product
        Product notFound = search.linearSearch(products, 999);
        System.out.println("Searching for 999: " + (notFound == null ? "Not Found" : "Found"));

        // --- Test Binary Search ---
        System.out.println("\n--- BINARY SEARCH (O(log n)) ---");
        Product foundBinary = search.binarySearch(sortedProducts, 104);
        if (foundBinary != null) {
            System.out.println("Found: " + foundBinary);
        } else {
            System.out.println("Product not found.");
        }

        // Test searching for non-existent
        Product notFoundBinary = search.binarySearch(sortedProducts, 999);
        System.out.println("Searching for 999: " + (notFoundBinary == null ? "Not Found" : "Found"));

        // --- Time Complexity Analysis ---
        System.out.println("\n--- TIME COMPLEXITY ANALYSIS ---");
        System.out.println("Linear Search (Unsorted Array): O(n) - Checks every element.");
        System.out.println("Binary Search (Sorted Array):   O(log n) - Divides search space in half each step.");
        System.out.println("Binary Search is faster for large sorted datasets, but requires the array to be sorted first.");
        System.out.println("\nBest Case: O(1) for both (target is the first/mid element).");
        System.out.println("Worst Case: Linear O(n), Binary O(log n).");
    }
}