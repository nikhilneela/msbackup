public class SmallestDivisor {
    public static int smallestDivisor(int[] nums, int threshold) {
        int start = nums[0], end = nums[0];

        for (int i = 0; i < nums.length; ++i) {
            start = Math.min(start, nums[i]);
            end = Math.max(end, nums[i]);
        }

        int smallestDivisor = -1;
        int divisonResult;
        while (start <= end) {
            int mid = (start + end) / 2;
            divisonResult = getDivisonResult(nums, mid);

            if (divisonResult <= threshold) {
                smallestDivisor = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return smallestDivisor;
    }

    private static int getDivisonResult(int[] nums, int divisor) {
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            int x = (int) Math.ceil((nums[i] * 1.0) / divisor);
            result += x;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getDivisonResult(new int[] {1, 2, 5, 9}, 5));
    }
}
