package org.example;

import java.util.Arrays;

public class MinPartitionArray {

    public static int solve3Memo(int[] array, int fromIndex, int partitionCount, int[][] memo) {
        int n = array.length;
        // we already solved for this combination
        if (memo[fromIndex][partitionCount] != -1) {
            System.out.println("memo hit");
            return memo[fromIndex][partitionCount];
        }

        //there is only one partition, just find max in the partition
        if (partitionCount == 1) {
            int maxVal = Integer.MIN_VALUE;
            for (int p = fromIndex; p < n; p++) {
                maxVal = Math.max(maxVal, array[p]);
            }
            return maxVal;
        }

        //find all possible partitions
        int maxSoFar = Integer.MIN_VALUE;
        int result = Integer.MAX_VALUE;
        for (int j = fromIndex; j <= n - partitionCount; ++j) {
            maxSoFar = Math.max(maxSoFar, array[j]);
            int minObtained = solve3Memo(array, j + 1, partitionCount - 1, memo) + maxSoFar;
            result = Math.min(result, minObtained);
        }
        memo[fromIndex][partitionCount] = result;
        return result;
    }

    public static int solve3(int[] array, int fromIndex, int partitionCount) {
        int[][] dp = new int[array.length][partitionCount + 1];

        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        return solve3Memo(array, fromIndex, partitionCount, dp);
    }

    public static int solve2(int[] array, int fromIndex, int partitionCount) {
        int n = array.length;

        //there is only one partition, just find max in the partition
        if (partitionCount == 1) {
            int maxVal = Integer.MIN_VALUE;
            for (int p = fromIndex; p < n; p++) {
                maxVal = Math.max(maxVal, array[p]);
            }
            return maxVal;
        }

        //find all possible partitions
        int maxSoFar = Integer.MIN_VALUE;
        int result = Integer.MAX_VALUE;
        //there should be atleast partitionCount - 1 elements after taking elements from left
        //n - 1 - (j + 1) + 1 >= partitionCount - 1
        //n - 1 - j - 1 + 1 >= partitionCount - 1
        //n - 1 - j >= partitionCount - 1
        //n - j >= partitionCount
        //n - partitionCount >= j
        //j <= n - partitionCount
        for (int j = fromIndex; n - 1 - (j + 1) + 1 >= partitionCount - 1; ++j) {
            maxSoFar = Math.max(maxSoFar, array[j]);
            int minObtained = solve2(array, j + 1, partitionCount - 1) + maxSoFar;
            result = Math.min(result, minObtained);
        }
        return result;
    }

    // Recursive function to partition the array from index i into k parts
    // such that the sum of maximums of each part is minimized.
    public static int solve(int[] array, int i, int k) {
        // Base case: if k == 1, return the maximum from i to the end.
        if (k == 1) {
            int maxVal = Integer.MIN_VALUE;
            for (int p = i; p < array.length; p++) {
                maxVal = Math.max(maxVal, array[p]);
            }
            return maxVal;
        }

        // Try partitioning at every valid j, and take the minimum among them.
        int minVal = Integer.MAX_VALUE;

        // Loop over possible partition boundaries
        // Adjust the boundary condition to ensure you have enough elements for the remaining (k-1) partitions
        for (int j = i + 1; j <= array.length - (k - 1); j++) {
            // Compute the max in the current segment [i..j-1]
            int currentMax = Integer.MIN_VALUE;
            for (int p = i; p < j; p++) {
                currentMax = Math.max(currentMax, array[p]);
            }

            // Recursively solve for the remaining subarray starting at j
            int cost = currentMax + solve(array, j, k - 1);

            // Keep track of the minimum sum of maxes
            minVal = Math.min(minVal, cost);
        }

        return minVal;
    }

    public static void runTests() {
        // Test Case 1:
        // Array: [30, 8, 4, 12, 9, 16, 21], k = 3
        // Expected: 55 (as per the example provided)
        int[] arr1 = {30, 8, 4, 12, 9, 16, 21};
        int k1 = 3;
        int res1 = solve3(arr1, 0, k1);
        System.out.println("Test Case 1: Expected: 55, Got: " + res1);

        // Test Case 2:
        // Array: [5, 1, 3, 2], k = 4 (each element forms a partition)
        // Expected: 5 + 1 + 3 + 2 = 11
        int[] arr2 = {5, 1, 3, 2};
        int k2 = 4;
        int res2 = solve3(arr2, 0, k2);
        System.out.println("Test Case 2: Expected: 11, Got: " + res2);

        // Test Case 3:
        // Array: [5, 1, 3, 2], k = 1 (one partition: the whole array)
        // Expected: max(5,1,3,2) = 5
        int[] arr3 = {5, 1, 3, 2};
        int k3 = 1;
        int res3 = solve3(arr3, 0, k3);
        System.out.println("Test Case 3: Expected: 5, Got: " + res3);

        // Test Case 4:
        // Array: [1, 2, 3, 4, 5], k = 2
        // Best partition: [1] and [2,3,4,5] -> maxes: 1 and 5, sum = 6
        int[] arr4 = {1, 2, 3, 4, 5};
        int k4 = 2;
        int res4 = solve3(arr4, 0, k4);
        System.out.println("Test Case 4: Expected: 6, Got: " + res4);

        // Test Case 5:
        // Array: [10, 5, 20, 15, 10, 25], k = 3
        // One optimal partition: [10], [5], [20,15,10,25] -> maxes: 10, 5, and 25, sum = 40
        int[] arr5 = {10, 5, 20, 15, 10, 25};
        int k5 = 3;
        int res5 = solve3(arr5, 0, k5);
        System.out.println("Test Case 5: Expected: 40, Got: " + res5);


        int[] arr6 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4};
        int k6 = 10;
        int res6 = solve3(arr6, 0, k6);
        System.out.println("Test Case 6: Expected: 40, Got: " + res6);
    }

    public static void main(String[] args) {
        runTests();
    }
}

