public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(sqrtN(1));
    }

    public static int sqrtN(long N) {
        long start = 1; long end = N;
        int mid = 0;
        int ans = 0;
        while (start <= end) {
            mid = (int)((start + end) / 2);
            long v = N/mid;
            if (v == mid) return mid;
            else if (mid > v) {
                end = mid - 1;
            } else {
                ans = mid;
                //move up
                start = mid + 1;
            }
        }
        return ans;
    }
}
