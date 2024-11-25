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
        subtotal = in.readDouble();
        deliveryFees = in.readDouble();
        status = in.readString();
        calculatedTax = in.readDouble();
        total = in.readDouble();
        orderStatus = in.readString();
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
        // Initialize the subtotal to 0
        total = 0.0;
        FoodItem[] orderList = selectedFoodItems.keySet().toArray(new FoodItem[0]);

        // Loop through each selected food item
        for (int x = 0; x < selectedFoodItems.size(); x++) {
            // Get the cost of the selected food item based on cost and quanity
            total = orderList[x].getPrice() * selectedFoodItems.get(orderList[x]);
        }

        // Return the calculated subtotal
        return total;
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

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(restaurant, flags);
        dest.writeParcelable(menu, flags);
        dest.writeDouble(subtotal);
        dest.writeDouble(deliveryFees);
        dest.writeString(status);
        dest.writeDouble(total);
        dest.writeString(orderStatus);

        dest.writeInt(selectedFoodItems.size());
        for (Map.Entry<FoodItem, Integer> entry : selectedFoodItems.entrySet()) {
            dest.writeParcelable((Parcelable) entry.getKey(), flags);
            dest.writeInt(entry.getValue());
        }

    }
}