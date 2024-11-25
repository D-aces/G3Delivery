package com.example.g3delivery.data.model;

import java.util.List;
import java.util.Map;

public class FoodItem {

    private List<String> category; // List of categories
    private Map<String, Object> customization; // Change to Map<String, Object>
    private String name; // Food item name
    private double price; // Food item price
    private String description;

    // Empty constructor for Firestore deserialization
    public FoodItem() {}

    // Constructor
    public FoodItem(List<String> category, Map<String, Object> customization, String name, double price, String description) {
        this.category = category;
        this.customization = customization;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Map<String, Object> getCustomization() {
        return customization;
    }

    public void setCustomization(Map<String, Object> customization) {
        this.customization = customization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
}
