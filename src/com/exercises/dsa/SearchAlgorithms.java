package com.exercises.dsa;

public class SearchAlgorithms {

    /**
     * Linear Search - O(n)
     * Searches for a product by its ID in an unsorted array.
     * Returns the product if found, else null.
     */
    public Product linearSearch(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId() == targetId) {
                return products[i];
            }
        }
        return null; // not found
    }

    /**
     * Binary Search - O(log n)
     * Searches for a product by its ID in a SORTED array (by productId).
     * Returns the product if found, else null.
     */
    public Product binarySearch(Product[] sortedProducts, int targetId) {
        int left = 0;
        int right = sortedProducts.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sortedProducts[mid].getProductId() == targetId) {
                return sortedProducts[mid];
            } else if (sortedProducts[mid].getProductId() < targetId) {
                left = mid + 1; // search right half
            } else {
                right = mid - 1; // search left half
            }
        }
        return null; // not found
    }
}