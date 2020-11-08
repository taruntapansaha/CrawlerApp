package util;

import java.util.*;

public class WordSearchUtil {

    public static List<String> topKFrequent(String words, int k) {

        // find frequency of each element using map, the create a max heap, insert element based on frequency
        // retain top k element, return the list

        Map<String, Integer> count = new HashMap();

        for (String word: words.split(" ")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> heap = new PriorityQueue<String>((w1, w2) -> count.get(w1) - count.get(w2));

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> result = new ArrayList();
        while (!heap.isEmpty()) result.add(heap.poll());

        return result;
    }
}
