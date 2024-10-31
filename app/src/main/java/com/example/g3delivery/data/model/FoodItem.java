package com.example.g3delivery.data.model;

public class FoodItem {
    private String name;
    private String[] category;
    private String[] customizations;
    private double price;

    public String getName(){
        return name;
    }

    public String[] getCategory(){
        return category;
    }

    public String[] getCustomizations(){
        return customizations;
    }

    public double getPrice() {
        return price;
    }
}
