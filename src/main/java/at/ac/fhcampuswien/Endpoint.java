package at.ac.fhcampuswien;

public enum Endpoint {
    //https://www.baeldung.com/java-enum-values

    EVERYTHING("everything"),
    TOP_HEADLINES("top-headlines");

    public final String label;

    private Endpoint(String label){
        this.label = label;
    }
}
