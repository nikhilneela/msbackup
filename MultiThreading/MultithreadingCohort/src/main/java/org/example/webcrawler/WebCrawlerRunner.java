package org.example.webcrawler;

import java.util.List;

public class WebCrawlerRunner {
    public static void main(String[] args) {
        WebCrawlerService crawlerService = new WebCrawlerService(50);
        List<String> crawledUrls = crawlerService.crawl("https://hub.example.com/");

        crawledUrls.forEach(System.out::println);
    }
}
