public class FirstAndLast {
    public static void main(String[] args) {
        int[] arr = new int[] {2,2};
        int[] result = searchRange(arr, 3);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        if (nums.length == 0) return res;

        res[0] = lowerBound(nums, nums.length, target);
        res[1] = upperBound(nums, nums.length, target);

        if (res[0] != target || res[1] == nums.length) return new int[] {-1, -1};
        return res;
    }

    static int lowerBound(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    static int upperBound(int[] arr, int n, int x) {
        int low = 0, high = n -1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
