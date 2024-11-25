package com.example.g3delivery.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Menu implements Parcelable {

    // Maps each item name to a FoodItem object
    private Map<String, FoodItem> items;

    // Empty constructor for Firestore deserialization
    public Menu() {
        this.items = new HashMap<>();
    }

    // Constructor
    public Menu(Map<String, FoodItem> items) {
        this.items = items;
    }

    protected Menu(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(items.size());
        for (Map.Entry<String, FoodItem> entry : items.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    // Getters and Setters
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