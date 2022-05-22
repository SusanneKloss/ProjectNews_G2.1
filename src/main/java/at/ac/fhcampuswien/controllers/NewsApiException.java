package at.ac.fhcampuswien.controllers;

public class NewsApiException extends Exception{

    public NewsApiException(String message){
        super(message);
        System.out.println(message);
    }

    public NewsApiException(){
        //String message = getMessage();
    }
}
