package org.example.webcrawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SameDomainUrlExplorer implements IUrlExplorer {

    private final Map<String, List<String>> preComputedLinkedUrlMap;


    public SameDomainUrlExplorer() {
        preComputedLinkedUrlMap = new HashMap<>();
        preComputedLinkedUrlMap.put("https://www.google.com", List.of("https://www.google.com/a/b/c", "https://www.google.com/p/q/r", "https://www.google.com/x", "https://www.yahoo.com/p/"));
        preComputedLinkedUrlMap.put("https://www.google.com/p/q/r", List.of("https://www.google.com/a/b/c", "https://www.google.com/m/n", "https://www.google.com/x/y", "https://www.yahoo.com/p/"));
        preComputedLinkedUrlMap.put("https://www.yahoo.com/p", List.of("https://www.google.com/a/b/c", "https://www.yahoo.com/w/z", "https://www.google.com/c/m", "https://www.yahoo.com/q/", "https://www.yahoo.com/q/r"));
    }

    @Override
    public List<String> getLinkedUrls(String sourceUrl) {
        List<String> linkedUrls = preComputedLinkedUrlMap.getOrDefault(sourceUrl, new ArrayList<>());
        return linkedUrls.stream().filter(url -> sameDomain(sourceUrl, url)).toList();
    }

    private boolean sameDomain(String source, String target) {
        return getDomain(source).equals(getDomain(target));
    }

    private String getDomain(final String url) {
        String httpLiteral = "https://";
        int from = url.indexOf(httpLiteral) + httpLiteral.length();
        String temp = url.substring(from);
        if (!temp.contains("/")) {
            return temp;
        } else {
            int to = temp.indexOf("/");
            return temp.substring(0, to);
        }
    }
}
