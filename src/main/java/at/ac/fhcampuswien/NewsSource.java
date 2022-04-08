package at.ac.fhcampuswien;

public class NewsSource {
    private String id;
    private String name;

    public NewsSource(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
