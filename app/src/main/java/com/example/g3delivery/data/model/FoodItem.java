package com.example.g3delivery.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodItem implements Parcelable {

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

    // Parcelable Implementation
    protected FoodItem(Parcel in) {
        category = in.createStringArrayList(); // Reading List<String>
        name = in.readString();
        price = in.readDouble();
        description = in.readString();
        // Read Map<String, Object> using a HashMap
        int customizationSize = in.readInt();
        customization = new HashMap<>();
        for (int i = 0; i < customizationSize; i++) {
            String key = in.readString();
            Object value = in.readValue(getClass().getClassLoader());
            customization.put(key, value);
        }
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(category); // Writing List<String>
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(description);
        // Write Map<String, Object> using a HashMap
        if (customization != null) {
            dest.writeInt(customization.size());
            for (Map.Entry<String, Object> entry : customization.entrySet()) {
                dest.writeString(entry.getKey());
                dest.writeValue(entry.getValue());
            }
        } else {
            dest.writeInt(0);
        }
    }
}
