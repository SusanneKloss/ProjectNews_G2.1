package at.ac.fhcampuswien.models;

public class Url {
    private final String scheme, host, version, endpoint, category, language,
            country, sortBy, query, source, valueOfPageSize, apiKey;

    /*
    Builder is passed to private constructor of Url class,
    Construcor cannot be accessed from the outside,
    Object creation is delegated to Builder class
     */
    private Url (Builder builder){
        //objects constructor parameter changed to type of Builder class
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
        this.apiKey = builder.apiKey;

    }

    //nested builder class
    public static class Builder {
        private String scheme, host, version, endpoint, category, language,
                country, sortBy, query, source, valueOfPageSize, apiKey;

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
            if(query != null) {
                url.append("q=").append(query).append("&");
            }
            if(source != null){
                url.append("source=").append(source).append("&");
            }
                   url
                           .append("pageSize=").append(String.valueOf(100)).append("&")
                           .append("apiKey=").append(apiKey);

            return url.toString();
        }
        //each function needs to return the Builder itself
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
        public Builder addApiKey(String apiKey){
            this.apiKey = apiKey;
            return this;
        }
        /*
        build() method calls constructor of base class Url,
        returns new object of Url
         */
        public Url build() {
            return new Url(this);
        }
    }
}

