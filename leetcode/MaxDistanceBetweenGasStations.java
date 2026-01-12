import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxDistanceBetweenGasStations {
    public double MinimizeMaxDistance(int[] arr, int k) {
        int[] numberOfGasStationsAtPositions = new int[arr.length - 1];

        double maxSectionLength = 0;
        int maxIndex = 0;

        for (int i = 0; i < k; ++i) {
            maxSectionLength = 0;
            maxIndex = 0;
            for (int j = 0; j < arr.length - 1; j++) {
                double distance = (arr[j + 1] - arr[j]) / (1.0 * numberOfGasStationsAtPositions[j] + 1);
                if (distance > maxSectionLength) {
                    maxSectionLength = distance;
                    maxIndex = j;
                }
            }
            //now place kth gas station at maxIndex
            numberOfGasStationsAtPositions[maxIndex]++;
        }

        maxSectionLength = 0;
        for (int i = 0; i < numberOfGasStationsAtPositions.length; i++) {
            double distance = (arr[i + 1] - arr[i]) / (1.0 * numberOfGasStationsAtPositions[i] + 1);
            maxSectionLength = Math.max(distance, maxSectionLength);
        }

        return maxSectionLength;
    }


    @Test
    public void Test() {
        assertEquals(3.5, MinimizeMaxDistance(new int[] {1, 8 ,12, 19, 21, 28, 34}, 5));
        assertEquals(3, MinimizeMaxDistance(new int[] {1, 13, 17, 23}, 5));
    }
}
