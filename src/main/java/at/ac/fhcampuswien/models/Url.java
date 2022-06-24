package at.ac.fhcampuswien.models;

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
    private final String quer;

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
        this.quer = builder.quer;
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
        private String quer;

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
        public Builder addQuery(String quer, String query){
            this.quer = quer;
            this.query = query;
            return this;
        }

        public Url build() {
            return new Url(this);
        }
    }
}
