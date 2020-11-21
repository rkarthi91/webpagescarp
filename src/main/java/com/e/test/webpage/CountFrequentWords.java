package com.e.test.webpage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SpringBootApplication
public class CountFrequentWords implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(CountFrequentWords.class);

    Map<String, Integer> singleWordMap = new HashMap<>();
    Map<String, Integer> twoWordPairMap = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(CountFrequentWords.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Read website content using jsoup library.
        Document doc = Jsoup.connect("https://www.314e.com").get();

        // Extract 314e related hyperlink from this webpage using jsoup selector to ignore external resources
        Set<String> hyperlinkSet =
                doc.select("a[href*=www.314e.com/]").stream().filter(element -> {
                    return !element.attr("abs:href").toString().equals("https://www.314e.com/");
                }).map(element -> element.attr("href"))
                        .collect(Collectors.toSet());

        // Scrap each webpage content, split words and load into hashmap to maintain word count
        for (String s : hyperlinkSet) {
            populateSingleWordFromWebPageContent(s);
        }

        // two word pairs
        for (String s : hyperlinkSet) {
            populateTwoWordPairFromWebPageContent(s);
        }

        // Print single word and two pair words top 10 frequently repeated words
        System.out.println("----------- Single word Pair -----------------");
        printTop10FrequentWords(singleWordMap);

        System.out.println("----------- Two word Pair -----------------");
        printTop10FrequentWords(twoWordPairMap);

    }

    /*
    Print top 10 frequently repeated words
     */
    private void printTop10FrequentWords(Map<String, Integer> inputWordMap) {

        // Java 8 stream to help limit top 10 items
        sortByValue(inputWordMap).stream().limit(10).forEach(new Consumer<Map.Entry<String, Integer>>() {
            @Override
            public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
                if (stringIntegerEntry.getKey() != null && stringIntegerEntry.getKey().trim().length() > 0)
                    LOG.debug("{} - {}", stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            }
        });
    }

    /*
    Populate single word into hashmap to maintain repeated counts.
    */
    private void populateSingleWordFromWebPageContent(String url) throws IOException {

        System.out.println(url);
        LOG.debug("Url - {}", url);

        // Ignore http erros to avoid http client and server side erros..  ../blog results 404 error so added to ignore it.
        Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();

        String[] contentArr = document.html().split(" ");

        for (int i = 0, len = contentArr.length; i < len; i++) {
            String s = contentArr[i];
            if (!ignoreNullEmptyNewLine(s)) {
                if (singleWordMap.containsKey(s)) {
                    singleWordMap.put(s, singleWordMap.get(s) + 1);
                } else {
                    singleWordMap.put(s, 1);
                }
            }
        }

    }

    /*
    Populate two word pairs into hashmap to maintain repeated counts.
     */
    private void populateTwoWordPairFromWebPageContent(String url) throws IOException {

        Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();

        String[] contentArr = document.html().split(" ");

        for (int i = 1, len = contentArr.length; i < len; i++) {
            if (!ignoreNullEmptyNewLine(contentArr[i - 1]) && !ignoreNullEmptyNewLine(contentArr[i])) {
                String twoPairedWord = contentArr[i - 1] + " " + contentArr[i];

                if (twoWordPairMap.containsKey(twoPairedWord)) {
                    twoWordPairMap.put(twoPairedWord, twoWordPairMap.get(twoPairedWord) + 1);
                } else {
                    twoWordPairMap.put(twoPairedWord, 1);
                }
            }
        }
    }

    /*Ignore Empty String and string with new line
     */
    private boolean ignoreNullEmptyNewLine(String s) {
        return null == s || s.trim().length() <= 0 || s.equals("\n");
    }

    public static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<>(hm.entrySet());

        // Sort the list and reverse it to maintain descending order.
        Collections.sort(list, ((Comparator<Map.Entry<String, Integer>>) (o1, o2) -> {
            return (o1.getValue()).compareTo(o2.getValue());
        }).reversed());

        return list;
    }

}
