import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllocateBooks {

    public int allocateBooksLS(ArrayList<Integer> arr, int noOfBooks, int noOfStudents) {
        if (noOfStudents > noOfBooks) {
            return -1;
        }

        int start = Integer.MIN_VALUE, end = 0;

        for (int i = 0; i < noOfBooks; i++) {
            start = Math.max(arr.get(i), start);
            end += arr.get(i);
        }

        while (start <= end) {
           int students = canAllocate(arr, start);
           if (students == noOfStudents) {
               return start;
           }
           start++;
        }
        return 0;
    }

    public int allocateBooksBS(ArrayList<Integer> arr, int noOfBooks, int noOfStudents) {
        if (noOfStudents > noOfBooks) {
            return -1;
        }

        int start = Integer.MIN_VALUE, end = 0;

        for (int i = 0; i < noOfBooks; i++) {
            start = Math.max(arr.get(i), start);
            end += arr.get(i);
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            int students = canAllocate(arr, mid);
            if (students > noOfStudents) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    int canAllocate(ArrayList<Integer> arr, int maxPagesPerStudent) {
        int noOfStudents = 1;
        int pagesPerStudent = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (pagesPerStudent + arr.get(i) <= maxPagesPerStudent) {
                pagesPerStudent += arr.get(i);
            } else {
                pagesPerStudent = arr.get(i);
                noOfStudents++;
            }
        }
        return noOfStudents;
    }


    @Test
    public void TestCanAllocate() {
        assertEquals(71, allocateBooksLS((new ArrayList<>(Arrays.asList(25, 46, 28, 49, 24))), 5, 4));
        assertEquals(71, allocateBooksBS((new ArrayList<>(Arrays.asList(25, 46, 28, 49, 24))), 5, 4));
    }
}
