package at.ac.fhcampuswien;
//https://newsapi.org/docs/endpoints/everything
//source is object, part of response object
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
