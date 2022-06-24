package at.ac.fhcampuswien.models;

public class Url {
    private final String scheme;
    private final String host;
    private final String version;
    private final String endpoint;

    private Url (Builder builder){
        this.scheme = builder.scheme;
        this.host = builder.host;
        this.version = builder.version;
        this.endpoint = builder.endpoint;
    }

    //nestes builder class
    public static class Builder {

        private final String scheme;
        private final String host;
        private String version;
        private String endpoint;

        public Builder (String scheme, String host){
            this.scheme = scheme;
            this.host = host;
        }
        public Builder addVersion(String version){
            this.version = version;
            return this;
        }
        public Builder addEndpoint(String endpoint){
            this.endpoint = endpoint;
            return this;
        }

        public Url build() {
            return new Url(this);
        }
    }
}
