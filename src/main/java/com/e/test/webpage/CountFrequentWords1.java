package com.e.test.webpage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

//@SpringBootApplication
public class CountFrequentWords1 implements CommandLineRunner {

    Map<String, Integer> singleWordMap = new HashMap<>();
    Map<String, Integer> twoWordPairMap = new HashMap<>();
    String rootUrl = "https://www.314e.com/";

    public static void main(String[] args) {
        SpringApplication.run(CountFrequentWords1.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Important point mentioned in problem description - You will have to assume that the maximum number of levels you have to expand for urls within a url as 4
        // if we expand url's url in 4 times its very time consuming.. and problem is not very clear.. but check my sudo solution..
        ripWebContent(rootUrl, 4);

        // After populated word's repeated counts, sort highest and print top 10.
//        System.out.println("----------- Single word Pair -----------------");
//        printTop10FrequentWords(singleWordMap);
    }

    /*
    Recursive method to expand urls, hyperlink url for n "level" times
     */
    private void ripWebContent(String url, int level) throws IOException {

        Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();

        // here to populate words of the webpages.. for mock i have just printed url..since its not clear.
        populateSingleWord(url, level);

        if(level == 0)
            return;

        // decrement level to expand url
        int tempLevel = level - 1;

        // Extract hyperlinks in current webpages.
        Set<String> hyperlinkSet =
                doc.select("a[href*="+rootUrl+"]").stream().filter(element -> {
                    return !element.attr("abs:href").toString().equals(rootUrl);
                }).map(element -> element.attr("href"))
                        .collect(Collectors.toSet());

        for (String link : hyperlinkSet) {

            ripWebContent(link, tempLevel);

        }

    }

    private void populateSingleWord(String url, int level) {
        System.out.println(level + " --> " + url);
    }

    private void populateSingleWord(Document document) {
        String[] contentArr = document.html().split(" ");

        // Collect values in map, if repeated increment its count as below.
        for (int i = 0, len = contentArr.length; i < len; i++) {
            if(ignoreNullEmptyNewLine(contentArr[i])) {
                if (singleWordMap.containsKey(contentArr[i])) {
                    singleWordMap.put(contentArr[i], singleWordMap.get(contentArr[i]) + 1);
                } else {
                    singleWordMap.put(contentArr[i], 1);
                }
            }
        }

    }

    /*
        Ignore Empty String and string with new line
     */
    private boolean ignoreNullEmptyNewLine(String s) {
        return null == s || s.trim().length() <= 0 || s.equals("\n");
    }

    public static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list by ascending order and reverse it for descending order. (top to bottom)
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        }.reversed());

        return list;
    }

    private void printTop10FrequentWords(Map<String, Integer> inputWordMap) {

        List sortedList = sortByValue(inputWordMap);

        // Limit first 10 values in list and print them.
        sortedList.stream().limit(10).forEach(new Consumer<Map.Entry<String, Integer>>() {
            @Override
            public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
                if (stringIntegerEntry.getKey() != null && stringIntegerEntry.getKey().trim().length() > 0)
                    System.out.println(stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue());
            }
        });
    }

}
