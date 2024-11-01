package com.example.g3delivery.data.model;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.GeoPoint;

import org.w3c.dom.Document;

import java.util.List;

public class Restaurant {
    private String name;
    private String description;
    private String address;
    private GeoPoint geoLocation;
    private List<String> labels;
    private DocumentReference menu;
    private String logoImage;
    private double rating;

    public String getName(){
        return name;
    }
    public String getDescription(){return description;}
    public String getAddress(){
        return address;
    }
    public GeoPoint getGeolocation(){
        return geoLocation;
    }
    public List<String> getLabels(){
        return labels;
    }
    public String getLogoImage(){return logoImage;}
    public DocumentReference getMenu(){
        return menu;
    }
    public double getRating(){return rating;}

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setAddress(String address) {this.address = address;}
    public void setGeolocation(GeoPoint geolocation) {this.geoLocation = geolocation;}
    public void setLabels(List<String> labels) {this.labels = labels;}
    public void setMenu(DocumentReference menu) {this.menu = menu;}
    public void setRating(double rating) {this.rating = rating;}
    public void setLogoImage(String logoImage){this.logoImage = logoImage;}
}
