package org.example.hitcounter;

import java.util.concurrent.atomic.AtomicInteger;

public class Solution2 implements Q06WebpageVisitCounterInterface {
    private Helper06 helper;
    private AtomicInteger[] counters;

    public Solution2(){
    }

    public void init(int totalPages, Helper06 helper){
        this.helper=helper;
        this.counters = new AtomicInteger[totalPages];
        for (int i = 0; i < this.counters.length; i++) {
            this.counters[i] = new AtomicInteger();
        }
    }

    // increment visit count for pageIndex by 1
    public void incrementVisitCount(int pageIndex) {
        this.counters[pageIndex].incrementAndGet();
    }

    // return total visit count for a given page
    public int getVisitCount(int pageIndex) {
        return this.counters[pageIndex].get();
    }
}
