package at.ac.fhcampuswien.models.enums;

public enum Endpoint {
    //https://www.baeldung.com/java-enum-values

    EVERYTHING("everything"),
    TOP_HEADLINES("top-headlines");

    public final String label;

    private Endpoint(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
