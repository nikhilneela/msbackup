package org.example.hitcounter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Solution implements Q06WebpageVisitCounterInterface {
    private Helper06 helper;
    private int[] counters;
    private Object[] locks;

    public Solution(){
    }

    public void init(int totalPages, Helper06 helper){
        this.helper=helper;
        this.counters = new int[totalPages];
        this.locks = new Object[totalPages];
        for (int i = 0; i < this.locks.length; i++) {
            this.locks[i] = new Object();
        }
    }

    // increment visit count for pageIndex by 1
    public void incrementVisitCount(int pageIndex) {
        synchronized (locks[pageIndex]) {
            this.counters[pageIndex]++;
        }
    }

    // return total visit count for a given page
    public int getVisitCount(int pageIndex) {
        synchronized (locks[pageIndex]) {
            return this.counters[pageIndex];
        }
    }
}



