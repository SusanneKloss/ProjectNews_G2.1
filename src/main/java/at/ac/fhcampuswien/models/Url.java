package at.ac.fhcampuswien.models;

import at.ac.fhcampuswien.models.enums.Category;
import at.ac.fhcampuswien.models.enums.Country;
import at.ac.fhcampuswien.models.enums.Language;
import at.ac.fhcampuswien.models.enums.SortBy;

public class Url {
    private static final String API_KEY = "729cec6715634598a41f22bd23f552e2";
    private static final String base = "https://newsapi.org/v2/";
    private static final String pageSize = "100";
    private final String endpoint, category, language, country, sortBy, query, source;


    private Url (Builder builder){

        this.endpoint = builder.endpoint;
        this.category = builder.category;
        this.language = builder.language;
        this.country = builder.country;
        this.sortBy = builder.sortBy;
        this.query = builder.query;
        this.source = builder.source;

    }

    //nestes builder class
    public static class Builder {

        private String endpoint, category, language, country, sortBy, query, source;

        public Builder(){
        }


        public Builder addEndpoint(String endpoint){
            this.endpoint = endpoint;
            return this;
        }
        public Builder addCategory(String category){
            this.category = category;
            return this;
        }
        public Builder addLanguage(String language){
            this.language = language;
            return this;
        }
        public Builder addCountry(String country){
            this.country = country;
            return this;
        }
        public Builder addSortBy(String sortBy){
            this.sortBy = sortBy;
            return this;
        }
        public Builder addQuery(String query){
            this.query = query;
            return this;
        }
        public Builder addSource(String source){
            this.source = source;
            return this;
        }


        public Url build() {
            return new Url(this);
        }

    }


    //@Override
    public String toString(Url url) {
        StringBuilder stringUrl = new StringBuilder()
                .append(base)
                .append("&endpoint=")
                .append(endpoint)
                .append("&category=")
                .append(category)
                .append("&language=")
                .append(language)
                .append("&country=")
                .append(country)
                .append("&SortBy=")
                .append(sortBy)
                .append("&q=")
                .append(query)
                .append("&source=")
                .append(source)
                .append("&pageSize=")
                .append(pageSize)
                .append("&api_key=")
                .append(API_KEY);

        return stringUrl.toString();




    }

}

