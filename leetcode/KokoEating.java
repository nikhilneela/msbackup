public class KokoEating {
    public static int minEatingSpeed(int[] piles, int hours) {

        int start = 1, end = piles[0];

        for (int i = 0; i < piles.length; ++i) {
            end = Math.max(end, piles[i]);
        }
        int minEatingSpeed = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            double minHoursRequired = 0;
            for (int i = 0; i < piles.length; ++i) {
                minHoursRequired += Math.ceil(piles[i] * 1.0 / mid);
            }
            if (minHoursRequired <= hours) {
                minEatingSpeed = mid;
                //eating too fast, slow down
                end = mid - 1;

            } else {
                //eating too slow, fast up
                start = mid + 1;
            }
        }
        return minEatingSpeed;
    }

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[] {1,1,1,999999999}, 10));
    }
}
