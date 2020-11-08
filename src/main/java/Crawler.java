import java.util.List;

public interface Crawler {

    void crawl(String url);
    List<String> searchFrequentWords(int k);
    List<String[]> searchFrequentWordPairs();
    List<String> fetchAllLinks();

}
