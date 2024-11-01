package com.example.g3delivery.data.model;

import java.util.Map;

public class FoodItem {

    private String name;
    private String category;
    private Map<String, Double> customizations; // Maps customization names to prices
    private double price;

    // Empty constructor for Firestore deserialization
    public FoodItem() {}

    // Constructor
    public FoodItem(String name, String category, Map<String, Double> customizations, double price) {
        this.name = name;
        this.category = category;
        this.customizations = customizations;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Double> getCustomizations() {
        return customizations;
    }

    public void setCustomizations(Map<String, Double> customizations) {
        this.customizations = customizations;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
