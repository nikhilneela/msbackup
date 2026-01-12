package org.example.webcrawler;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@AllArgsConstructor
public class WebCrawlerWorker implements Runnable {
    private final IUrlExplorer explorer;
    private final WebCrawlerState state;
    private final WorkerRunningState workerRunningState;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            String urlToExplore;
            synchronized (state) {
                while (state.getUrlsToExplore().isEmpty()) {
                    synchronized (workerRunningState) {
                        workerRunningState.setWaiting();
                        workerRunningState.notifyAll();
                    }
                    if (workerRunningState.allWorkersWaiting()) {
                        return;
                    }
                    state.wait();
                    synchronized (workerRunningState) {
                        workerRunningState.setRunning();
                    }
                }

                urlToExplore = state.getUrlsToExplore().poll();
            }

            List<String> linkedUrls = explorer.getLinkedUrls(urlToExplore);

            synchronized (state) {
                List<String> dedupedUrls = linkedUrls.stream().filter(linkedUrl -> !state.getUrlsExplored().contains(linkedUrl)).toList();
                state.getUrlsExplored().addAll(dedupedUrls);
                state.getUrlsToExplore().addAll(dedupedUrls);
                state.notifyAll();
            }
        }
    }
}
