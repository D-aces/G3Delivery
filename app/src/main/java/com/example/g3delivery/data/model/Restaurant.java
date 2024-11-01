package com.example.g3delivery.data.model;

public class Restaurant {
    private String name;
    private String description;
    private String address;
    private double[] geolocation;
    private String[] labels;
    private Menu menu;
    private double rating;

    public String getName(){
        return name;
    }
    public String getDescription(){return description;}
    public String getAddress(){
        return address;
    }
    public double[] getGeolocation(){
        return geolocation;
    }
    public String[] getLabels(){
        return labels;
    }
    public Menu getMenu(){
        return menu;
    }
    public double getRating(){return rating;}

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setAddress(String address) {this.address = address;}
    public void setGeolocation(double[] geolocation) {this.geolocation = geolocation;}
    public void setLabels(String[] labels) {this.labels = labels;}
    public void setMenu(Menu menu) {this.menu = menu;}
    public void setRating(double rating) {this.rating = rating;}
}
