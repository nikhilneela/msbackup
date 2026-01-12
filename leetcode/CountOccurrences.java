public class CountOccurrences {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 4, 4, 5};
        System.out.println(count(arr, arr.length, 5));
    }

    public static int count(int arr[], int n, int x) {
        int result = 0;
        int low = 0, high = n - 1;
        int first = -1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == x) {
                first = mid;
                high = mid - 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (first == -1) return 0;

        int last = -1;
        low = 0; high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == x) {
                last = mid;
                low = mid + 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return last - first + 1;
    }
}
