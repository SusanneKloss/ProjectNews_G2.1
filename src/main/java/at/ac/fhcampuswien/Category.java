package at.ac.fhcampuswien;

public enum Category {
    //endpoint Top-Headlines
    // business, entertainment, general, health, science, sports, technology

    BUSINESS("business");

    public final String label;

    private Category(String label){
        this.label = label;
    }
}
