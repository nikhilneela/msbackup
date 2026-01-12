package org.example.webcrawler;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class WebCrawlerService {

    private final WebCrawlerState state = new WebCrawlerState();
    private final WorkerRunningState workerRunningState;


    public WebCrawlerService(int numThreads) {
        workerRunningState = new WorkerRunningState(numThreads);
        for (int i = 0; i < numThreads; ++i) {
            //new Thread(new WebCrawlerExecutor(new SameDomainUrlExplorer(), state, workerRunningState)).start();
            new Thread(new WebCrawlerWorker(new ComplexGraphUrlExplorer(), state, workerRunningState)).start();
        }
    }


    @SneakyThrows
    public List<String> crawl(final String startUrl) {
        synchronized (state) {
            state.getUrlsToExplore().add(startUrl);
            state.notifyAll();
        }

        synchronized (workerRunningState) {
            while (!workerRunningState.allWorkersWaiting()) {
                workerRunningState.wait();
            }
        }

        synchronized (state) {
            state.notifyAll();
        }

        return new ArrayList<>(state.getUrlsExplored());
    }
}
