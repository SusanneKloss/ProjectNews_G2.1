package at.ac.fhcampuswien;

public enum Language {
    //Endpoint everything
    //Possible options: ar de en es fr he i tnl no pt ru se ud zh

    ENGLISH("en"),
    FRENCH("fr"),
    GERMAN("de"),
    ITALIAN("it");

    public final String label;

    private Language(String label){
        this.label = label;
    }
}
