package org.example.webcrawler;/*
 * ComplexGraphUrlExplorer.java
 *
 * A richer IUrlExplorer implementation that exposes a deliberately complicated
 * precomputed graph to help stress-test crawlers. The graph includes:
 *  - deep chains (depth > 10)
 *  - high branching nodes (fan-out > 10)
 *  - cycles (A->B->C->A)
 *  - self-loops (D->D)
 *  - duplicate links (same target listed multiple times)
 *  - cross-domain links and external domains
 *  - subdomains (maps.google.com, mail.google.com)
 *  - different schemes and ports (http vs https, explicit ports)
 *  - URLs with query parameters and fragments
 *  - dead-ends (nodes with no outgoing links)
 *
 * No main() method is provided — this is intended as a library/fixture class.
 */

import java.util.*;

public class ComplexGraphUrlExplorer implements IUrlExplorer {

    private final Map<String, List<String>> preComputedLinkedUrlMap;

    public ComplexGraphUrlExplorer() {
        preComputedLinkedUrlMap = new HashMap<>();

        // Helper to populate map succinctly
        // Note: Arrays.asList produces a fixed-size list; that's fine for static fixtures.
        addEdges("https://www.google.com",
                "https://www.google.com/a/b/c",
                "https://www.google.com/p/q/r",
                "https://www.google.com/x",
                "https://maps.google.com/place/123",
                "https://mail.google.com/mail/u/0/#inbox",
                "http://www.google.com:8080/legacy", // different scheme/port
                "https://www.yahoo.com/p/",
                "https://www.google.com?a=1&b=2", // query params
                "https://www.google.com/a/b/c#section-2", // fragment
                "https://www.google.com/self-loop"); // will link back to itself below

        // Deep chain: chain-0 -> chain-1 -> ... -> chain-12
        for (int i = 0; i <= 12; i++) {
            String src = String.format("https://deep.example.com/chain-%d", i);
            String dst = (i < 12) ? String.format("https://deep.example.com/chain-%d", i + 1)
                    : "https://deep.example.com/terminal"; // terminal node
            addEdges(src, dst);
        }
        // terminal node with a cross-domain link back to google
        addEdges("https://deep.example.com/terminal", "https://www.google.com/a/b/c", "https://dead.end/empty");

        // High branching hub
        List<String> hubTargets = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            hubTargets.add("https://hub.example.com/item/" + i);
        }
        // include some cross domains and duplicates deliberately
        hubTargets.add("https://www.yahoo.com/q/r");
        hubTargets.add("https://hub.example.com/item/3"); // duplicate
        addEdges("https://hub.example.com/", hubTargets.toArray(new String[0]));

        // Cycles A -> B -> C -> A
        addEdges("https://cycle.example.com/A", "https://cycle.example.com/B", "https://cross.example.com/out");
        addEdges("https://cycle.example.com/B", "https://cycle.example.com/C");
        addEdges("https://cycle.example.com/C", "https://cycle.example.com/A");

        // Self-loop
        addEdges("https://selfloop.example.com/", "https://selfloop.example.com/");

        // Nodes with multiple cross-domain outgoing links
        addEdges("https://outgoing.example.com/", "https://www.google.com/x/y", "https://www.yahoo.com/w/z", "http://external.net/path");

        // A node with no outgoing links (dead-end)
        addEdges("https://dead.end/empty");

        // Another site with a few complex urls (ports, subdomains, params)
        addEdges("https://www.yahoo.com/p/",
                "https://www.google.com/a/b/c",
                "https://www.yahoo.com/w/z",
                "https://www.google.com/c/m",
                "https://www.yahoo.com/q/",
                "https://www.yahoo.com/q/r");

        // Some nodes pointing to subpaths and mixing trailing slashes
        addEdges("https://www.google.com/a/b/c", "https://www.google.com/a/b/c/d", "https://www.google.com/a/b/c#frag");
        addEdges("https://www.google.com/p/q/r", "https://www.google.com/a/b/c", "https://www.google.com/m/n", "https://www.google.com/x/y", "https://www.yahoo.com/p/");

        // Introduce a self-referential but slightly different URL to test normalization needs
        addEdges("https://www.google.com/x", "https://www.google.com/x/", "https://www.google.com/X");

        // A cluster with many internal cross-links and a couple of externals
        addEdges("https://cluster.example.com/home",
                "https://cluster.example.com/about",
                "https://cluster.example.com/products/1",
                "https://cluster.example.com/products/2",
                "https://cluster.example.com/contact",
                "https://external.example.org/partner");

        addEdges("https://cluster.example.com/products/1", "https://cluster.example.com/products/2", "https://cluster.example.com/reviews/1");
        addEdges("https://cluster.example.com/products/2", "https://cluster.example.com/products/1", "https://cluster.example.com/reviews/2");

        // Intentionally create subtle variations that many naive crawlers treat as different
        addEdges("https://case.example.com/Path", "https://case.example.com/path", "https://case.example.com/path/");

        // A node which contains many repeated links so the crawler must handle de-dup
        addEdges("https://repeats.example.com/",
                "https://repeats.example.com/a",
                "https://repeats.example.com/a",
                "https://repeats.example.com/a?utm=1",
                "https://repeats.example.com/a#top",
                "https://repeats.example.com/b");

        // A mix of http and https versions of the same host
        addEdges("http://insecure.example.com/", "https://insecure.example.com/", "http://insecure.example.com/page");

        // Port and query variants
        addEdges("https://ports.example.com/", "https://ports.example.com:8443/secure", "https://ports.example.com:8443/secure?x=1");

        // A small clump for testing redirect-like behavior (simulated)
        addEdges("https://redir.example.com/start", "https://redir.example.com/mid", "https://redir.example.com/end");
        addEdges("https://redir.example.com/mid", "https://redir.example.com/end");
        addEdges("https://redir.example.com/end", "https://www.google.com");

        // Add many small leaf nodes so crawler has plenty of targets to measure
        for (int i = 0; i < 50; i++) {
            addEdges("https://leafsite.example.com/page-" + i);
        }
    }

    private void addEdges(String source, String... targets) {
        preComputedLinkedUrlMap.put(source, Arrays.asList(targets));
    }

    /**
     * Returns only links that are on the same domain as the source (same behavior as your original class).
     * Use getAllLinkedUrls() if you want the full set including external domains.
     */
    @Override
    public List<String> getLinkedUrls(String sourceUrl) {
        List<String> linkedUrls = preComputedLinkedUrlMap.getOrDefault(sourceUrl, Collections.emptyList());
        List<String> filtered = new ArrayList<>();
        for (String url : linkedUrls) {
            if (sameDomain(sourceUrl, url)) {
                filtered.add(url);
            }
        }
        return filtered;
    }

    /**
     * Returns all outgoing links from the node without filtering by domain.
     */
    public List<String> getAllLinkedUrls(String sourceUrl) {
        return preComputedLinkedUrlMap.getOrDefault(sourceUrl, Collections.emptyList());
    }

    public Set<String> getAllKnownUrls() {
        return Collections.unmodifiableSet(preComputedLinkedUrlMap.keySet());
    }

    private boolean sameDomain(String source, String target) {
        return getDomain(source).equals(getDomain(target));
    }

    private String getDomain(final String url) {
        // A simple domain extractor that tolerates scheme, port and paths.
        // It intentionally does not attempt full RFC-compliant parsing to keep the fixture self-contained.
        String lower = url.toLowerCase(Locale.ROOT);
        String schemeSep = "://";
        int start = lower.indexOf(schemeSep);
        int from = (start >= 0) ? (start + schemeSep.length()) : 0;
        int slash = lower.indexOf('/', from);
        int end = (slash >= 0) ? slash : lower.length();
        // include port if present; this makes ports considered part of the domain key on purpose
        return lower.substring(from, end);
    }

    // Useful debug helper for tests: returns a text dump of the graph.
    public String dumpGraphSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph summary:\n");
        sb.append("Total nodes: ").append(preComputedLinkedUrlMap.size()).append('\n');
        for (Map.Entry<String, List<String>> e : preComputedLinkedUrlMap.entrySet()) {
            sb.append("- ").append(e.getKey()).append(" -> ").append(e.getValue().size()).append(" targets\n");
        }
        return sb.toString();
    }
}
