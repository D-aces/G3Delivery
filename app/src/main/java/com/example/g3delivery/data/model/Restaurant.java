package com.example.g3delivery.data.model;

public class Restaurant {
    private String name;
    private String description;
    private String address;
    private double[] geolocation;
    private String[] labels;
    private Menu menu;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

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
}
