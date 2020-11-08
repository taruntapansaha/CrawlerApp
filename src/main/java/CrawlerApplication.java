import java.util.*;

public class CrawlerApplication {

    private static int MAX_PAGES_TO_SEARCH = 4;

    //Set because we don't want any duplicates
    // As we craw through any websites we might links that re-directing us to the same page we started before
    // we keep track of visited sites here

    private Set<String> visitedPage = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();

    private Crawler crawler;

    // we can pass different crawlers at the time of creation
    public CrawlerApplication(Crawler crawler) {
        this.crawler = crawler;
    }

    public CrawlerApplication() {
        this.crawler = new HtmlCrawler();
    }


    private String nextUrl() {
        String nextUrl = pagesToVisit.remove(0);

        // iterate till we find a fresh url to visit
        while (visitedPage.contains(nextUrl)) nextUrl = pagesToVisit.remove(0);

        return nextUrl;
    }

    public void findFrequentWords(String url, int count) {

        while (this.visitedPage.size() < MAX_PAGES_TO_SEARCH) {

            String currentUrl;
            if (this.pagesToVisit.isEmpty()) {
                currentUrl = url;
                this.visitedPage.add(url);
            } else {
                currentUrl = this.nextUrl();
            }

            crawler.crawl(currentUrl);
            this.pagesToVisit.addAll(crawler.fetchAllLinks());

            List<String> strings = crawler.searchFrequentWords(count);
            strings.forEach(System.out::println);
        }
    }

    public String[][] findFrequentPairOfWords(int count) {
        return new String[][]{};
    }

}
