package com.example.g3delivery.data.model;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private String menuName; // For example, "Tim Hortons Menu"
    private Map<String, FoodItem> items; // Maps each item name to a FoodItem object

    // Empty constructor for Firestore deserialization
    public Menu() {
        this.items = new HashMap<>();
    }

    // Constructor
    public Menu(String menuName, Map<String, FoodItem> items) {
        this.menuName = menuName;
        this.items = items;
    }

    // Getters and Setters
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Map<String, FoodItem> getItems() {
        return items;
    }

    public void setItems(Map<String, FoodItem> items) {
        this.items = items;
    }

    // Method to add an item to the menu
    public void addItem(String itemName, FoodItem foodItem) {
        this.items.put(itemName, foodItem);
    }
}
