package at.ac.fhcampuswien;

public enum Category {
    //for endpoint Top-Headlines
    ///https://www.baeldung.com/java-enum-values

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology");

    public final String label;

    Category(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
