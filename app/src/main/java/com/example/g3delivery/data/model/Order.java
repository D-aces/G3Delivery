package com.example.g3delivery.data.model;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    // TODO Add user specific logic here
    // private user
    private Restaurant restaurant;
    private Menu menu;
    private HashMap<FoodItem, Integer> selectedFoodItems = new HashMap<>();
    private double subtotal;
    // TODO: Alter TAX_RATE later
    private final double TAX_RATE = 0.13;
    private double deliveryFees;
    private String status;
    private double total;
    private String orderStatus;


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

}