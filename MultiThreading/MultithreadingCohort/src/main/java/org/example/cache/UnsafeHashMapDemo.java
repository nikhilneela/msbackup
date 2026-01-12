package org.example.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class UnsafeHashMapDemo {
    static final int threads = 2;
    static final int ops = 100000;
    static final Map<Integer,Integer> safeMap = new ConcurrentHashMap<>();
    static final Map<Integer, Integer> unsafeMap = new HashMap<>();
    static final ThreadSafeHashMap threadSafeHashMap = new ThreadSafeHashMap();
    static final StripedHashMap<String, Integer> stripedHashMap = new StripedHashMap<>();


    private static void testSafeMap() throws InterruptedException {
        System.out.println("Start : Testing Safe Map");
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(threads);

        for (int t = 0; t < threads; t++) {
            final int tid = t;
            new Thread(() -> {
                try { start.await(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                for (int i = 0; i < ops; i++) {
                    int key = tid * ops + i;
                    safeMap.put(key, key);
                }
                done.countDown();
            }).start();
        }

        long s = System.currentTimeMillis();
        start.countDown();
        done.await();
        long e = System.currentTimeMillis();

        int expected = threads * ops;
        System.out.println("Expected size: " + expected);
        System.out.println("Actual size:   " + safeMap.size());
        System.out.println("Time(ms):      " + (e - s));
        if (safeMap.size() != expected) {
            System.out.println("Mismatch! lost entries detected.");
            int missing = 0;
            for (int k = 0; k < expected; k++) {
                if (!safeMap.containsKey(k)) missing++;
            }
            System.out.println("Missing count (checked): " + missing);
        } else {
            System.out.println("All entries present.");
        }
        System.out.println("End : Testing Safe Map");
    }

    private static void testUnsafeMap() throws InterruptedException {
        System.out.println("Start : Testing UnSafe Map");
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(threads);

        for (int t = 0; t < threads; t++) {
            final int tid = t;
            new Thread(() -> {
                try { start.await(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                for (int i = 0; i < ops; i++) {
                    int key = tid * ops + i;
                    stripedHashMap.put(String.valueOf(key), key);
                }
                done.countDown();
            }).start();
        }

        long s = System.currentTimeMillis();
        start.countDown();
        done.await();
        long e = System.currentTimeMillis();

        int expected = threads * ops;
        System.out.println("Expected size: " + expected);
        System.out.println("Actual size:   " + stripedHashMap.size());
        System.out.println("Time(ms):      " + (e - s));
        if (stripedHashMap.size() != expected) {
            System.out.println("Mismatch! lost entries detected.");
            int missing = 0;
            for (int k = 0; k < expected; k++) {
                if (!unsafeMap.containsKey(k)) missing++;
            }
            System.out.println("Missing count (checked): " + missing);
        } else {
            System.out.println("All entries present.");
        }
        System.out.println("End : Testing UnSafe Map");
    }

    public static void main(String[] args) throws Exception {
        testUnsafeMap();
        //System.out.println();
        //testSafeMap();

    }
}