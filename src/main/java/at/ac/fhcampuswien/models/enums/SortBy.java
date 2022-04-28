package at.ac.fhcampuswien.models.enums;

public enum SortBy {
    //for endpoint everything
    ///https://www.baeldung.com/java-enum-values

    POPULARITY("popularity"),
    PUBLISHED_AT("publishedAt"),
    RELEVANCY("relevancy");

    public final String label;

    private SortBy(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
