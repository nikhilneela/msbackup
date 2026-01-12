Implement a multi-threaded web crawler. The crawler starts from a given URL and uses multiple threads to explore all pages accessible from this starting point. Each URL points to a webpage, and the crawling process retrieves all URLs present on a page to visit them recursively.

The system will call the crawl with startUrl and you will have to block the calling thread until all the URLs are parsed and then return those URLs.

Signature:

class WebCrawler {
List<String> crawl(String startUrl) {
// Your code here
}
}
Requirements:
The crawling process should:

Avoid revisiting the same URL more than once.
Crawl only pages belonging to the same hostname as the starting URL.
Multithreading:

Utilize multiple threads to speed up the crawling process.
Ensure thread safety to handle shared data structures (e.g., visited URLs).
Constraints:

startUrl is guaranteed to belong to the same hostname for the crawling process.
URL comparisons should be case-sensitive and exact.
Key Considerations:
Thread Safety: Design the solution to avoid race conditions while accessing shared resources like the visited URLs set.
Hostname Validation: Use the hostname of the startUrl to filter URLs that do not belong to the same domain.
Concurrency: Efficiently distribute the crawling workload across threads to optimize performance.