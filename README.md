# webpagescarp

Implemented in Spring boot - Command Line Runner

-- Used JSoup Library to handle html contents.
Reference - https://jsoup.org/ 

Implemented two spring boot runner class.. 

 - CountFrequentWords.java -- main class, contains source code to extract top 10 frequently used words.
 - CountFrequentWords1.java -- another main class -- commented SpringBootApplication -- here we have handled important point mentioned in problem description. i.e expand for urls within a url as 4
 
 To read webpage content use below JSoup code.

` Jsoup.connect("https://www.314e.com").get();
`

Notes - 

 Used Recursive algorithm in CountFrequentWords1.java .. to expand urls to max of 4 levels.. -- This operation is very heavy to commented, please verify the code.
 
 