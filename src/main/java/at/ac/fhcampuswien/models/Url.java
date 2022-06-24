package at.ac.fhcampuswien.models;

import io.github.cdimascio.dotenv.Dotenv;

public class Url {
    private final String scheme;
    private final String host;
    private final String version;
    private final String endpoint;
    private final String category;
    private final String language;
    private final String country;
    private final String sortBy;
    private final String query;
    private final String source;
    private final String valueOfPageSize;
    //private final String API_KEY;
    private static String API_KEY = Dotenv.load().get("API_TOKEN");


    private Url (Builder builder){
        this.scheme = builder.scheme;
        this.host = builder.host;
        this.version = builder.version;
        this.endpoint = builder.endpoint;
        this.category = builder.category;
        this.language = builder.language;
        this.country = builder.country;
        this.sortBy = builder.sortBy;
        this.query = builder.query;
        this.source = builder.source;
        this.valueOfPageSize = builder.valueOfPageSize;
        this.API_KEY = builder.API_KEY;

    }

    //nestes builder class
    public static class Builder {
        private String scheme;
        private String host;
        private String version;
        private String endpoint;
        private String category;
        private String language;
        private String country;
        private String sortBy;
        private String query;
        private String source;
        private String valueOfPageSize;
        private String API_KEY;

        public Builder(){
        }
        @Override
        public String toString(){
            StringBuilder url = new StringBuilder()
                    .append(scheme)
                    .append(host)
                    .append(version)
                    .append(endpoint)
                    .append("?");
            if(category != null){
                url.append("category=").append(category).append("&");
            }
            if(language != null){
                url.append("language=").append(language).append("&");
            }
            if(country != null){
                url.append("country=").append(country).append("&");
            }
            if(sortBy != null){
                url.append("SortBy=").append(sortBy).append("&");
            }
                    url.append("q=").append(query).append("&");
            if(source != null){
                url.append("source=").append(source).append("&");
            }
                   url.append("pageSize=").append(String.valueOf(100)).append("&")
                           .append("apiKey=").append(API_KEY);

            return url.toString();
        }

        public Builder addScheme (String scheme) {
            this.scheme = scheme;
            return this;
        }

        public Builder addHost(String host){
            this.host = host;
            return this;
        }

        public Builder addVersion(String version){
            this.version = version;
            return this;
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
        public Builder addPageSize(String valueOfPageSize){

            this.valueOfPageSize = valueOfPageSize;
            return this;
        }
        public Builder addApiKey(String API_KEY){

            this.API_KEY = API_KEY;
            return this;
        }

        public Url build() {
            return new Url(this);
        }

    }
}

