package com.example.g3delivery.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order implements Parcelable {
    // private user
    private Restaurant restaurant;
    private Menu menu;
    private HashMap<FoodItem, Integer> selectedFoodItems = new HashMap<>();
    private double subtotal;
    // TODO: Alter TAX_RATE later
    private final double TAX_RATE = 0.13;
    private double calculatedTax;
    private double deliveryFees;
    private String status;
    private double total;
    private String orderStatus;

    public Order(){}

    protected Order(Parcel in) {
        restaurant = in.readParcelable(Restaurant.class.getClassLoader());
        menu = in.readParcelable(Menu.class.getClassLoader());
        subtotal = in.readDouble();
        deliveryFees = in.readDouble();
        status = in.readString();
        total = in.readDouble();
        orderStatus = in.readString();

        // Read map size
        int mapSize = in.readInt();
        selectedFoodItems = new HashMap<>();
        for (int i = 0; i < mapSize; i++) {
            FoodItem key = in.readParcelable(FoodItem.class.getClassLoader());
            int value = in.readInt();
            selectedFoodItems.put(key, value);
        }
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(restaurant, flags);
        dest.writeParcelable(menu, flags);
        dest.writeDouble(subtotal);
        dest.writeDouble(deliveryFees);
        dest.writeString(status);
        dest.writeDouble(total);
        dest.writeString(orderStatus);

        // Write map size
        dest.writeInt(selectedFoodItems.size());
        for (Map.Entry<FoodItem, Integer> entry : selectedFoodItems.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags); // Serialize key
            dest.writeInt(entry.getValue()); // Serialize value
        }
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public HashMap<FoodItem, Integer> getFoodMap(){return selectedFoodItems;}

    public double getSubtotal(){
        return subtotal;
    }

    public double getTaxRate(){
        return TAX_RATE;
    }

    public double getDeliveryFees(){
        return deliveryFees;
    }

    public String getStatus(){
        return status;
    }

    public double getTotal(){
        return total;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }


    public double calculateSubtotal() {
        subtotal = 0.0; // Reset subtotal
        for (Map.Entry<FoodItem, Integer> entry : selectedFoodItems.entrySet()) {
            // Accumulate subtotal as price * quantity
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }


    public void setDeliveryFees(double deliveryFees){
        this.deliveryFees = deliveryFees;
    }

    // Multiple by the tax rate and add the total and delivery fees to get the total due
    public double calculateTotal(){
        return (total * TAX_RATE) + total + deliveryFees;
    }

    // TODO Add order status logic
    public void setOrderStatus(){

    }
    public void removeItemFromOrder(FoodItem foodItem, int quantity) {
        // Error check
        if(quantity < 0){
            return;
        }
        // Check if the item exists in the map
        if(selectedFoodItems.containsKey(foodItem)){
            // Check if the item value is greater than 0
            if(selectedFoodItems.get(foodItem) > 0){
                // Subtract from quantity given the current food item object
                selectedFoodItems.replace(foodItem, selectedFoodItems.get(foodItem), quantity);
            }
            else {
                // Remove the item if the quantity is 0 (or less --> shouldn't happen)
                selectedFoodItems.remove(foodItem);
            }
        }
    }

    public void addItemToOrder(FoodItem foodItem, int quantity){
        // Error check
        if(quantity <= 0){
            return;
        }
        // Check if the item exists in the map
        if(selectedFoodItems.containsKey(foodItem)){
                // Subtract from quantity given the current food item object
                selectedFoodItems.replace(foodItem, selectedFoodItems.get(foodItem), quantity);
        } else {
            selectedFoodItems.put(foodItem, quantity);
        }
    }

    public double getCalculatedTax(){
        return calculatedTax = subtotal * TAX_RATE;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}