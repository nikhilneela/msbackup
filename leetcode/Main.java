public class Main {
    public static void main(String[] args) {
        int[] arr = new int[] {2};
        ceilingInSortedArray(arr.length, 23, arr);
    }

    public static int ceilingInSortedArray(int n, int x, int[] arr) {
        int[] result = new int[] {-1, -1};

        int low = 0; int high = n - 1;

        //floor
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= x) {
                result[0] = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        low = 0; high = n - 1;

        //ceil
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= x) {
                result[1] = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.print(result[0] + " " + result[1]);
        return result[1];
    }

}