# webpagescarp

Implemented in Spring boot - Command Line Runner

-- Used JSoup Library to handle html contents.
Reference - https://jsoup.org/ 

Implemented two spring boot runner class.. 

 - CountFrequentWords.java -- main class, contains source code to extract top 10 frequently used words.
 - CountFrequentWords1.java -- another main class -- commented SpringBootApplication -- here we have handled important point mentioned in problem description. i.e expand for urls within a url as 4
 
 To read webpage content use below JSoup code.

```java 
Jsoup.connect("https://www.314e.com").get();
```

To Compile this Spring boot project use below maven command

{{PROJECT_DIRECTORY}}> mvn clean install

To run this spring boot project 

{{PROJECT_DIRECTORY}}> mvn spring-boot:run

```java
Sample output - 

----------- Single word Pair -----------------
<div - 11329
</div> - 6675
</div>\n- 3946
.color_alternate - 3168
<li - 2791
class="menu-item - 2664
menu-item-object-page - 2052
menu-item-type-post_type - 2052
0 - 1525
.no-touch - 1512

 ----------- Two word Pair -----------------
class="menu-item menu-item-type-post_type - 2052
menu-item-type-post_type menu-item-object-page - 2052
<div class="elementor-element - 1250
<div class="elementor-widget-container"> - 1250
menu-item-object-page w-nav-item - 1082
w-nav-item level_3 - 828
class="w-nav-anchor level_3" - 828
menu-item-type-custom menu-item-object-custom - 612
class="menu-item menu-item-type-custom - 612
menu-item-object-custom menu-item-has-children - 586

```


Notes - 

 - Copied spring boot project compile and execution logs into **./output.log** file
 - Used Recursive algorithm in CountFrequentWords1.java .. to expand urls to max of 4 levels.. -- This operation is very heavy to commented, please verify the code.
 
 