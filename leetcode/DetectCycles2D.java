package org.example;

public class DetectCycles2D {
    private int[] parents, sizes;
    private int rows, cols;

    private boolean isSafe(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return false;
        }
        return true;
    }

    private int transform(int x, int y) {
        return x * cols + y;
    }

    private boolean union(int u, int v) {
        int up_u = findParent(u);
        int up_v = findParent(v);

        if (up_u == up_v) {
            return false;
        }

        int size_u = sizes[up_u];
        int size_v = sizes[up_v];

        if (size_u < size_v) {
            parents[up_u] = up_v;
            sizes[up_v] += sizes[up_u];
        } else {
            parents[up_v] = up_u;
            sizes[up_u] += sizes[up_v];
        }
        return true;
    }

    private int findParent(int node) {
        if (node == parents[node]) {
            return node;
        }
        return parents[node] = findParent(parents[node]);
    }

    private void printParentsArray() {
        for (int i = 0; i < parents.length; ++i) {
            System.out.print(parents[i] + " ");
        }
    }

    public boolean containsCycle(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        parents = new int[rows * cols];
        sizes = new int[rows * cols];

        for (int i = 0; i < parents.length; ++i) {
            parents[i] = i;
            sizes[i] = 1;
        }

        int[] xDirs = new int[] {1, 0};
        int[] yDirs = new int[] {0, 1};

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {

                for (int k = 0; k < xDirs.length; ++k) {
                    int nx = i + xDirs[k];
                    int ny = j + yDirs[k];

                    if (isSafe(nx, ny) && grid[nx][ny] == grid[i][j]) {
                        System.out.println("Union (" + i + "," + j + ")" + " (" + nx + "," + ny + ")");
                        if (!union(transform(i,j), transform(nx,ny))) {
                            System.out.println("Union found cycle");
                            printParentsArray();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
