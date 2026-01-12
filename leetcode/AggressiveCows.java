import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggressiveCows {
    public int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);

        int minDistance = 0;

        while (true) {
            if (!canPlaceCows(stalls, k, minDistance + 1)) {
                break;
            }
            minDistance++;
        }
        return minDistance;
    }

    public int aggressiveCowsBS(int[] stalls, int k) {
        Arrays.sort(stalls);
        int start = 1, end = stalls[stalls.length - 1] - stalls[0];

        while (start <= end) {
            int mid = (start + end) / 2;

            if (canPlaceCows(stalls, k, mid)) {
                //can place k cows at a distance of mid, can we do better ? increase mid => move start
                start = mid + 1;
            } else {
                //cannot place k cows at a distance of mid, we need to decrease mid => move end
                end = mid - 1;
            }
        }
        return end;
    }

    private boolean canPlaceCows(int[] stalls, int noOfCows, int distance) {
        int lastPlacedIndex = 0;
        noOfCows--;
        for (int i = 1; i < stalls.length && noOfCows > 0; ++i) {
            if (stalls[i] - stalls[lastPlacedIndex] >= distance) {
                noOfCows--;
                lastPlacedIndex = i;
            }
        }
        return noOfCows == 0;
    }


    @Test
    public void Test() {

        assertEquals(3, aggressiveCows(new int[] {0, 3, 4, 7, 10, 9}, 4));
        assertEquals(5, aggressiveCows(new int[] {4, 2, 1, 3, 6}, 2));
        assertEquals(7, aggressiveCows(new int[] {8, 12, 3, 6, 9, 15, 0}, 3));
        assertEquals(1, aggressiveCows(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 6));

        assertEquals(aggressiveCows(new int[] {0, 3, 4, 7, 10, 9}, 4), aggressiveCowsBS(new int[] {0, 3, 4, 7, 10, 9}, 4));
        assertEquals(aggressiveCows(new int[] {4, 2, 1, 3, 6}, 2), aggressiveCowsBS(new int[] {4, 2, 1, 3, 6}, 2));
        assertEquals(aggressiveCows(new int[] {8, 12, 3, 6, 9, 15, 0}, 3), aggressiveCowsBS(new int[] {8, 12, 3, 6, 9, 15, 0}, 3));
        assertEquals(aggressiveCows(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 6), aggressiveCowsBS(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 6));

    }
}
