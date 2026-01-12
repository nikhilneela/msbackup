import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianOfTwoSortedArrays {
    public double FindMedian(int[] arr1, int[] arr2) {

        int numElements = (arr1.length + arr2.length) / 2;

        int p = 0, q = 0;
        int last = 0, lastBefore = 0;
        for (int i = 0; i <= numElements; i++) {
            int currentElement;
            if (p >= arr1.length) {
                currentElement = arr2[q++];
            } else if (q >= arr2.length) {
                currentElement = arr1[p++];
            } else if (arr1[p] <= arr2[q]) {
                currentElement = arr1[p++];
            } else {
                currentElement = arr2[q++];
            }
            if (i == numElements - 1) {
                lastBefore = currentElement;
            }
            if(i == numElements) {
                last = currentElement;
            }
        }

        if ((arr1.length + arr2.length) % 2 == 0) {
            return (last + lastBefore) / 2.0;
        } else {
            return last;
        }
    }

    @Test
    public void Test() {
        assertEquals(5, FindMedian(new int[] {1, 3, 4, 7, 10, 12}, new int[] {2, 3, 6, 15}));
        assertEquals(6, FindMedian(new int[] {1, 3, 4, 7, 10, 12}, new int[] {2, 3, 6, 15, 18}));
        assertEquals(4.5, FindMedian(new int[] {1}, new int[] {2, 3, 6, 15, 18}));
        assertEquals(4, FindMedian(new int[] {1, 3, 4, 6, 10, 12}, new int[] {2}));
    }
}
