package org.example.mergesort;

public class SingleThreadedMergeSortService implements IMergeSortService {
    private final int[] numbers;
    private final int[] auxNumbers;

    public SingleThreadedMergeSortService(final int[] numbers) {
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

    private void sort(int[] numbers, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(numbers, start, mid);
        sort(numbers, mid + 1, end);
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
