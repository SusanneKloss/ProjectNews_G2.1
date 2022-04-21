package at.ac.fhcampuswien;

import java.util.Locale;

public enum Country {
    //for endpoint Top-Headlines
    //https://www.baeldung.com/java-enum-values

    UNITED_ARAB_EMIRATES("ae"),
    ARGENTINA("ar"),
    AUSTRIA("at");


    public final String label;

    private Country(String label){
        this.label = label;
    }

}
