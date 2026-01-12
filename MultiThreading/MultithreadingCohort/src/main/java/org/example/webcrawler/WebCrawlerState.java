package org.example.webcrawler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@AllArgsConstructor
@Getter
public class WebCrawlerState {
    private final Queue<String> urlsToExplore = new LinkedList<>();
    private final Set<String> urlsExplored = new HashSet<>();
}
