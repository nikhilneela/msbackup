import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipWithinDays {
    public int shipWithinDays(int[] weights, int days) {
        int start = weights[0], end = 0;

        for (int i = 0; i < weights.length; ++i) {
            start = Math.max(start, weights[i]);
            end += weights[i];
        }

        int minWeightOfShip = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            int numDays = calculateNoDaysWithWeight(weights, mid);

            if (numDays <= days) {
                //this means the wight of the ship is higher since we are shipping in lesser then required days
                //we can still do better, so reduce the weight of the ship => move left
                minWeightOfShip = mid;
                end = mid - 1;
            } else {
                //this means the weight if the ship is lesser and cannot send all packages in required days
                //we need to increase the weight of the ship => move right
                start = mid + 1;
            }
        }
        return minWeightOfShip;
    }

    private int calculateNoDaysWithWeight(int[] weights, int weight) {
        //assumes weight is always >= the max weight of the packages
        int leftOverWeight = weight;
        int numDays = 0;
        for (int i = 0; i < weights.length; ++i) {
            if (weights[i] > leftOverWeight) {
                //reset
                leftOverWeight = weight;
                numDays++;
            }
            leftOverWeight -= weights[i];
        }
        return numDays + 1;
    }

    @Test
    public void testCalculateShipWeight() {
        assertEquals(5, calculateNoDaysWithWeight(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15));
        assertEquals(7, calculateNoDaysWithWeight(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
        assertEquals(6, calculateNoDaysWithWeight(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 11));
        assertEquals(6, calculateNoDaysWithWeight(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 8));
    }

    @Test
    public void testWeightOftheShip() {
        assertEquals(15, shipWithinDays(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        assertEquals(6, shipWithinDays(new int[] {3, 2, 2, 4, 1, 4}, 3));
        assertEquals(3, shipWithinDays(new int[] {1, 2, 3, 1, 1}, 4));
    }



}