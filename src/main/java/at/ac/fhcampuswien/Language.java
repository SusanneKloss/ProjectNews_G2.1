package at.ac.fhcampuswien;

public enum Language {
    //for endpoint Everything, 2-letter ISO-639-1 code of the more than 14 possible language options provided by the NewsAPI
    //https://www.baeldung.com/java-enum-values

    ARABIC("ar"),
    BULGARIAN("bg"),
    BOSNIAN("bs"),
    CZECH("cs"),
    GERMAN("de"),
    GREEK("el"),
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    HEBREW("he"),
    CROATIAN("hr"),
    HUNGARIAN("hu"),
    INDONESIAN("id"),
    ITALIAN("it"),
    KOREAN("ko"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    MALAY("ms"),
    DUTCH("nl"),
    NORWEGIAN("no"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    NORTHERN_SAMI("se"),
    SLOVENIAN("sl"),
    SLOVAK("sk"),
    SWEDISH("sv"),
    THAI("th"),
    TURKISH("tr"),
    UKRAINIAN("ur"),
    CHINESE("zh");




    public final String label;

    private Language(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
