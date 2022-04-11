package at.ac.fhcampuswien;

public enum SortBy {
    //Endpoint everything
    //relevancy, popularity, publishedAt

    RELEVANCY("relevancy");

    public final String label;

    private SortBy(String label){
        this.label = label;
    }
}
