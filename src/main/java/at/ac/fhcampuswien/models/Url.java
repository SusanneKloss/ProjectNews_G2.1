package at.ac.fhcampuswien.models;

public class Url {
    private final String scheme;
    private final String host;
    private final String version;
    private final String endpoint;
    private final String questionMark;
    private final String categoryString;
    private final String category;
    private final String languageString;
    private final String language;
    private final String countryString;
    private final String country;
    private final String sortByString;
    private final String sortBy;
    private final String query;
    private final String quer;
    private final String source;
    private final String sources;
    private final String pageSize;
    private final String valueOfPageSize;
    private final String apiKey;
    private final String API_KEY;
    private final String apersand;

    private Url (Builder builder){
        this.scheme = builder.scheme;
        this.host = builder.host;
        this.version = builder.version;
        this.endpoint = builder.endpoint;
        this.questionMark = builder.questionMark;
        this.categoryString = builder.categoryString;
        this.category = builder.category;
        this.languageString = builder.languageString;
        this.language = builder.language;
        this.countryString = builder.countryString;
        this.country = builder.country;
        this.sortByString = builder.sortByString;
        this.sortBy = builder.sortBy;
        this.query = builder.query;
        this.quer = builder.quer;
        this.source = builder.source;
        this.sources = builder.sources;
        this.pageSize = builder.pageSize;
        this.valueOfPageSize = builder.valueOfPageSize;
        this.apiKey = builder.apiKey;
        this.API_KEY = builder.API_KEY;
        this.apersand = builder.apersand;
    }

    //nestes builder class
    public static class Builder {

        private String scheme;
        private String host;
        private String version;
        private String endpoint;
        private String questionMark;
        private String categoryString;
        private String category;
        private String languageString;
        private String language;
        private String countryString;
        private String country;
        private String sortByString;
        private String sortBy;
        private String query;
        private String quer;
        private String source;
        private String sources;
        private String pageSize;
        private String valueOfPageSize;
        private String apiKey;
        private String API_KEY;
        private String apersand;

        public Builder(){
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
        public Builder addEndpoint(String endpoint, String questionMark){
            this.endpoint = endpoint;
            this.questionMark = questionMark;
            return this;
        }
        public Builder addCategory(String categoryString, String category, String apersand){
            this.categoryString = categoryString;
            this.category = category;
            this.apersand = apersand;
            return this;
        }
        public Builder addLanguage(String languageString, String language, String apersand){
            this.languageString = languageString;
            this.language = language;
            this.apersand = apersand;
            return this;
        }
        public Builder addCountry(String countryString, String country, String apersand){
            this.countryString = countryString;
            this.country = country;
            this.apersand = apersand;
            return this;
        }
        public Builder addSortBy(String sortByString, String sortBy, String apersand){
            this.sortByString = sortByString;
            this.sortBy = sortBy;
            this.apersand = apersand;
            return this;
        }
        public Builder addQuery(String quer, String query, String apersand){
            this.quer = quer;
            this.query = query;
            this.apersand = apersand;
            return this;
        }
        public Builder addSource(String sources, String source, String apersand){
            this.sources = sources;
            this.source = source;
            this.apersand = apersand;
            return this;
        }
        public Builder addPageSize(String pageSize, String valueOfPageSize, String apersand){
            this.pageSize = pageSize;
            this.valueOfPageSize = valueOfPageSize;
            this.apersand = apersand;
            return this;
        }
        public Builder addApiKey(String apiKey, String API_KEY){
            this.apiKey = apiKey;
            this.API_KEY = API_KEY;
            return this;
        }

        public Url build() {
            return new Url(this);
        }
    }
}

