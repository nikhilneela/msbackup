package org.example;

import java.util.Arrays;

public class MinColumnDeletionsToMakeSorted {
    public int minDeletionSize1(String[] strs) {
        int wordLength = strs[0].length();
        int rows = strs.length;
        int deleteCount = 0;
        boolean[] sorted = new boolean[rows];

        for (int i = 0; i < wordLength; i++) {
            boolean shouldDelete = false;
            for (int j = 1; j < rows; j++) {
                char prev = strs[i].charAt(j - 1);
                char current = strs[i].charAt(j);

                if (sorted[j]) {
                    continue;
                }

                if (prev > current) {
                    deleteCount++;
                    shouldDelete = true;
                }
            }
            
            if (!shouldDelete) {
                sorted[0] = true;
                for (int j = 1; j < rows; j++) {
                    char prev = strs[i].charAt(j - 1);
                    char current = strs[i].charAt(j);
                    sorted[j] = prev < current;
                }
            }
        }
        return deleteCount;
    }
}
