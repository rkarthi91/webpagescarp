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

To Compile this Spring boot project use below maven command

{{PROJECT_DIRECTORY}}> mvn clean install

To run this spring boot project 

{{PROJECT_DIRECTORY}}> mvn spring-boot:run

Sample output - 

2020-11-22 00:49:17.864  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : ----------- Single word Pair -----------------
2020-11-22 00:49:17.896  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : <div - 11329
2020-11-22 00:49:17.896  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : </div> - 6675
2020-11-22 00:49:17.896  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : </div>
 - 3946
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : .color_alternate - 3168
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : <li - 2791
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : class="menu-item - 2664
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : menu-item-object-page - 2052
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : menu-item-type-post_type - 2052
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : 0 - 1525
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : .no-touch - 1512
2020-11-22 00:49:17.904  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : ----------- Two word Pair -----------------
2020-11-22 00:49:17.944  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : class="menu-item menu-item-type-post_type - 2052
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : menu-item-type-post_type menu-item-object-page - 2052
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : <div class="elementor-element - 1250
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : <div class="elementor-widget-container"> - 1250
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : menu-item-object-page w-nav-item - 1082
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : w-nav-item level_3 - 828
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : class="w-nav-anchor level_3" - 828
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : menu-item-type-custom menu-item-object-custom - 612
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : class="menu-item menu-item-type-custom - 612
2020-11-22 00:49:17.952  INFO 15764 --- [           main] com.e.test.webpage.CountFrequentWords    : menu-item-object-custom menu-item-has-children - 586
[INFO] ------------------------------------------------------------------------


Notes - 

 - Copied spring boot project compile and execution logs into **./output.log** file
 - Used Recursive algorithm in CountFrequentWords1.java .. to expand urls to max of 4 levels.. -- This operation is very heavy to commented, please verify the code.
 
 