package at.ac.fhcampuswien;

public enum Language {

    ENGLISH("en"),
    FRENCH("fr"),
    GERMAN("de"),
    ITALIAN("it");

    public final String label;

    private Language(String label){
        this.label = label;
    }
}
