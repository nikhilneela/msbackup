package org.example;

import java.util.*;

public class IsReachableAtTime {
    static int[] xDirs = new int[] { 0, 0, -1, 1, 1, -1, 1, -1};
    static int[] yDirs = new int[] {-1, 1,  0, 0, 1, -1, -1, 1};

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        Queue<Pair> bfsQueue = new LinkedList<>();
        HashSet<Pair> visited = new HashSet<>();

        Pair start = new Pair(sx, sy);
        bfsQueue.add(start);
        visited.add(start);
        int level = 0;

        while (!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();
            boolean reachedTarget = false;
            for (int i = 0; i < size; ++i) {
                Pair queueItem = bfsQueue.poll();

                //reached target
                if (fx == queueItem.x && fy == queueItem.y) {
                    reachedTarget = true;
                    break;
                }

                for (int k = 0; k < xDirs.length; ++k) {
                    Pair nextPos = new Pair(queueItem.x + xDirs[k], queueItem.y + yDirs[k]);
                    if (!visited.contains(nextPos)) {
                        visited.add(nextPos);
                        bfsQueue.add(nextPos);
                    }
                }
            }
            if (reachedTarget) {
                //bfs will return the shortest path, anything below this length means we cannot reach
                return t >= level;
            }
            level++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isReachableAtTime(3,1,7,3,10));
        System.out.println(isReachableAtTime(1,1,2,4,3));
    }

}
