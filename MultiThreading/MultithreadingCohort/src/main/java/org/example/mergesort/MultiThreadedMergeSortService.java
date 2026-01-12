package org.example.mergesort;

import lombok.SneakyThrows;

public class MultiThreadedMergeSortService implements IMergeSortService {
    private int[] numbers;
    private int[] auxNumbers;

    public MultiThreadedMergeSortService(final int[] numbers) {
        this.numbers = numbers;
        this.auxNumbers = new int[numbers.length];
    }



    @Override
    public void sort() {
        sort(numbers, 0, numbers.length - 1);
    }

    @Override
    public void print() {
        for (int number : this.numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    @SneakyThrows
    private void sort(int[] numbers, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        Thread t1 = new Thread(() -> sort(numbers, start, mid));
        Thread t2 = new Thread(() -> sort(numbers, mid + 1, end));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        merge(numbers, start, mid, mid + 1, end);
    }

    private void merge(int[] numbers, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int start = leftStart;
        for (int i = leftStart, j = rightStart; i <= leftEnd || j <= rightEnd;) {

            if (i > leftEnd) {
                auxNumbers[start++] = numbers[j++];
            } else if (j > rightEnd) {
                auxNumbers[start++] = numbers[i++];
            } else if (numbers[i] <= numbers[j]) {
                auxNumbers[start++] = numbers[i++];
            } else {
                auxNumbers[start++] = numbers[j++];
            }
        }

        if (rightEnd + 1 - leftStart >= 0) {
            System.arraycopy(auxNumbers, leftStart, numbers, leftStart, rightEnd + 1 - leftStart);
        }
    }
}
