module ProjectNews {

    requires java.desktop;
    requires java.datatransfer;
    requires okhttp3;
    requires com.google.gson;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires java.dotenv;


    opens at.ac.fhcampuswien;
    opens at.ac.fhcampuswien.models;
    opens at.ac.fhcampuswien.models.enums;
    opens at.ac.fhcampuswien.ui;
    opens at.ac.fhcampuswien.controllers;
}