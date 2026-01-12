package org.example.webcrawler;

import java.util.List;

public interface IUrlExplorer {
    List<String> getLinkedUrls(String sourceUrl);
}
