import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.WordSearchUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class HtmlCrawler implements Crawler{

    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;

    public void crawl(String url) {
        try{
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document currentDocument = connection.get();

            this.htmlDocument = currentDocument;

            if(200 == connection.response().statusCode() && connection.response().contentType().contains("text/html")){
                // find frequent words and pairs

                // find links on the current page
                Elements linksOnPage = htmlDocument.select("a[href]");
                for(Element link : linksOnPage)
                {
                    this.links.add(link.absUrl("href"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchFrequentWords(int k) {
        if(null != this.htmlDocument){
            String body = this.htmlDocument.body().text();
            return WordSearchUtil.topKFrequent(body, k);
        }
        return Collections.emptyList();
    }

    //TODO: following function is incomplete
    public List<String[]> searchFrequentWordPairs() {
        if(null != this.htmlDocument){
            String body = this.htmlDocument.body().text();

        }
        return Collections.emptyList();
    }

    public List<String> fetchAllLinks() {
        return this.links;
    }
}
