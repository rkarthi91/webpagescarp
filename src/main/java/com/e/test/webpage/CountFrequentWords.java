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

@SpringBootApplication
public class CountFrequentWords implements CommandLineRunner {

    Map<String, Integer> singleWordMap = new HashMap<>();
    Map<String, Integer> twoWordPairMap = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(CountFrequentWords.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Document doc = Jsoup.connect("https://www.314e.com").get();

//		System.out.println(doc);

        Set<String> hyperlinkSet =
                doc.select("a[href*=www.314e.com/]").stream().filter(element -> {
                    return !element.attr("abs:href").toString().equals("https://www.314e.com/");
                }).map(element -> element.attr("href"))
                        .collect(Collectors.toSet());
//		hyperlinkSet.forEach(new Consumer<String>() {
//			@Override
//			public void accept(String string) {
//				System.out.println(string);
//			}
//		});

        for (String s : hyperlinkSet) {
			populateSingleWordFromWebPageContent(s);
		}

		for (String s : hyperlinkSet) {
			populateTwoWordPairFromWebPageContent(s);
		}

		System.out.println("----------- Single word Pair -----------------");

        printTop10FrequentWords(singleWordMap);

        System.out.println("----------- Two word Pair -----------------");

        printTop10FrequentWords(twoWordPairMap);

    }

    private void printTop10FrequentWords(Map<String, Integer> inputWordMap) {
        sortByValue(inputWordMap).stream().limit(10).forEach(new Consumer<Map.Entry<String, Integer>>() {
            @Override
            public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
                if (stringIntegerEntry.getKey() != null && stringIntegerEntry.getKey().trim().length() > 0)
                    System.out.println(stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue());
            }
        });
    }

    private void populateSingleWordFromWebPageContent(String url) throws IOException {

		System.out.println(url);
		Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();
        Arrays.stream(document.html().split(" ")).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                if(!ignoreNullEmptyNewLine(s)) {
                    if (singleWordMap.containsKey(s)) {
                        singleWordMap.put(s, singleWordMap.get(s) + 1);
                    } else {
                        singleWordMap.put(s, 1);
                    }
                }
            }
        });

    }

    private void populateTwoWordPairFromWebPageContent(String url) throws IOException {

		Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();

		String[] contentArr = document.html().split(" ");

		for (int i = 1, len = contentArr.length; i < len; i++) {
		    if(!ignoreNullEmptyNewLine(contentArr[i-1]) && !ignoreNullEmptyNewLine(contentArr[i])) {
                String twoPairedWord = contentArr[i - 1] +" "+ contentArr[i];

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
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        }.reversed());

        return list;
    }

}
