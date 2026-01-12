package org.example;

public class DetectCycles2DMain {
    public static void main(String[] args) {
        DetectCycles2D cycles2D = new DetectCycles2D();

        char[][] matrix = new char[][] {
                {'c', 'c', 'c', 'a'},
                {'c', 'd', 'c', 'c'},
                {'c', 'c', 'e', 'c'},
                {'f', 'c', 'c', 'c'}
        };

        System.out.println(cycles2D.containsCycle(matrix));
    }
}
