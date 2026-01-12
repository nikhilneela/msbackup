public class NthRoot {
    //returns 0 if mid is nth root of m
    //returns -1 if mid is lesser than the nth root of m
    //returns 2 if mid is greater than the nth root of m
    static int isNthRoot(int n, int mid, int m) {
        long res;
        res = 1;

        for (int i = 1; i <= n; ++i) {
            res = res * mid;
            if (res > m) return 2;
        }

        if (res == m) return 0;
        return -1;
    }

    public static int NthRoot(int n, int m) {
        // Write your code here.
        int start = 1; int end = m;

        while (start <= end) {
            int mid = (start + end)/2;

            int res = isNthRoot(n, mid, m);

            if (res == 0) return mid;
            else if (res == 2) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(NthRoot(4, 625));
    }
}
