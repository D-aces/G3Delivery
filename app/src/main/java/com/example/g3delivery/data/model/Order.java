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
    public void calculateSubtotal(){
        //subtotal = ;
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

}