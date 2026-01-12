package org.example.mergesort;

public class MergeSortRunner {
    public static void main(String[] args) {
        IMergeSortService service = new MultiThreadedMergeSortService(new int[] {-3, 4, 8, 2, 6, 12, 19, 7, 18, 14, 32, 12, 6, 9, 10});
        service.sort();
        service.print();
    }
}
