package com.example.g3delivery.data.model;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class Order {
    // TODO Add user specific logic here
    // private user
    private Restaurant restaurant;
    private Menu menu;
    private ArrayList<FoodItem> selectedFoodItems = new ArrayList<FoodItem>();
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

    public ArrayList<FoodItem> getSelectedFoodItems(){
        return selectedFoodItems;
    }

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

    public void selectFoodItem(String name){

        //selectedFoodItems.add(menu.getFoodItem(name));

    }

    // TODO Add calculation logic for the order subtotal method
    public double calculateSubtotal() {
        // Initialize the subtotal to 0
        total = 0.0;

        // Loop through each selected food item
        for (FoodItem foodItem : selectedFoodItems) {

            // Add the price of the current food item to the total
            total += foodItem.getPrice();
        }

        // Return the calculated subtotal
        return total;
    }

    public void setDeliveryFees(double deliveryFees){
        this.deliveryFees = deliveryFees;
    }

    // TODO Add calculation logic for the order total method
    public void calculateTotal(){

    }

    // TODO Add order status logic
    public void setOrderStatus(){

    }
    public void removeItemToOrder(FoodItem foodItem) {
        // Check if the item exists in the list and remove it
        if (selectedFoodItems.contains(foodItem)) {
            selectedFoodItems.remove(foodItem);
        } else {
            System.out.println("Item not in the order!");
        }
    }

    public void addItemToOrder(FoodItem foodItem){
        selectedFoodItems.add(foodItem);

    }

}