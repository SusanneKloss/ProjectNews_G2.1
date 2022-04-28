## 25.02.2022 - 18:30 ##

created Maven project & github repository

next meeting ~~04.03. - 13:30~~ 11.03. 09:30


## 11.03.2022 - 09:30 ##

integrated JavaFX dependencies, troubleshooting concerning the JavaFX running-configuration

talked about the testcases for the methods in AppController (see testlist)

next meeting 17.03. 09:30


## 17.03.2022 - 09:30 ##

discused the testcases everyone wrote

next meeting: 25.03. 14:00 (Scene Builder, getAllNewsBitcoin test cases)

## 21.4.2022 - 16.15 ##

group members present their own code and their ideas for solutions, discussion of the different approaches

the following points were agreed upon:
- use of HttpUrl.Builder instead of Stringbuilder in NewsApi class
- use of static method for request/response and deserialization in NewsApi class, plus try and catch(plus error message from response object)
- additional class NewsSource (according to NewsAPI-documentation)
- two constructors in NewsResponse class: one for valid response object, one for error cases
- Enums in all caps plus label according to requirement from NewsAPI documentation

To dos for the next meeting:
each team member finishes her/his task, we will then push together into main and connect the code
- Article, NewsSource, enum Country: Magdalena
- NewsResponse, enums Category, Language, Endpoint, SortBy, (getNews try and catch): Susi
- NewsApi: getUrl with HttpUrl.Builder, getNews: Hamza
- Menu and GUI: Lena
- AppController: will be adapted together in the next meeting 

Next meeting: 28.04. 10.00
