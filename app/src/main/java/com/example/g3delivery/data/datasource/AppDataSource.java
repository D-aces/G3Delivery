package com.example.g3delivery.data.datasource;

import com.example.g3delivery.data.model.FoodItem;
import com.example.g3delivery.data.model.Menu;
import com.example.g3delivery.data.model.Order;
import com.example.g3delivery.data.model.Restaurant;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AppDataSource {
    private final FirebaseFirestore db;
    private final DocumentReference restaurantsCollection;

    public AppDataSource() {
        db = FirebaseFirestore.getInstance();
        restaurantsCollection = db.collection("restaurants").document();  // Set base collection reference
    }

    // Restaurants Collection Operations
    public void createRestaurant(Restaurant restaurant) {
        // Auto-generate ID for each restaurant
        DocumentReference restaurantDocRef = db.collection("restaurants").document();

        // Map Restaurant model fields to Firestore document fields
        Map<String, Object> restaurantData = new HashMap<>();
        restaurantData.put("name", restaurant.getName());
        restaurantData.put("description", restaurant.getDescription());
        restaurantData.put("address", restaurant.getAddress());
        restaurantData.put("geolocation", restaurant.getGeolocation());
        restaurantData.put("labels", restaurant.getLabels());

        // Add data to Firestore
        restaurantDocRef.set(restaurantData)
                .addOnSuccessListener(aVoid -> {
                    // Successfully created restaurant
                    System.out.println("Restaurant added with ID: " + restaurantDocRef.getId());
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                    System.err.println("Error adding restaurant: " + e.getMessage());
                });
    }

    public void updateRestaurant() {}
    public void deleteRestaurant() {}

    // Menus Collection Operations
    public void createMenu(Menu menu, DocumentReference restaurantDocRef) {
        DocumentReference menuDocRef = restaurantDocRef.collection("menus").document();

        // Iterate through each FoodItem in the menu and add to Firestore
        for (FoodItem foodItem : menu.getMenu()) {
            createFoodItem(foodItem, menuDocRef);
        }
    }
    public void updateMenu() {}
    public void deleteMenu() {}

    // FoodItems Collection Operations
    public void createFoodItem(FoodItem foodItem, DocumentReference menuDocRef) {
        DocumentReference foodItemDocRef = menuDocRef.collection("foodItems").document();

        Map<String, Object> foodItemData = new HashMap<>();
        foodItemData.put("name", foodItem.getName());
        foodItemData.put("category", foodItem.getCategory());
        foodItemData.put("customizations", foodItem.getCustomizations());
        foodItemData.put("price", foodItem.getPrice());

        foodItemDocRef.set(foodItemData)
                .addOnSuccessListener(aVoid -> System.out.println("Food item added with ID: " + foodItemDocRef.getId()))
                .addOnFailureListener(e -> System.err.println("Error adding food item: " + e.getMessage()));
    }

    // Orders Collection Operations
    public void createOrder(Order order) {
        DocumentReference orderDocRef = db.collection("orders").document();

        Map<String, Object> orderData = new HashMap<>();
        orderData.put("restaurant", order.getRestaurant().getName());
        orderData.put("subtotal", order.getSubtotal());
        orderData.put("taxRate", order.getTaxRate());
        orderData.put("deliveryFees", order.getDeliveryFees());
        orderData.put("status", order.getStatus());
        orderData.put("total", order.getTotal());

        orderDocRef.set(orderData)
                .addOnSuccessListener(aVoid -> System.out.println("Order created with ID: " + orderDocRef.getId()))
                .addOnFailureListener(e -> System.err.println("Error creating order: " + e.getMessage()));

        // Add selected food items as sub-collection within this order
        for (FoodItem item : order.getSelectedFoodItems()) {
            addFoodItemToOrder(item, orderDocRef);
        }
    }

    private void addFoodItemToOrder(FoodItem item, DocumentReference orderDocRef) {
        Map<String, Object> foodItemData = new HashMap<>();
        foodItemData.put("name", item.getName());
        foodItemData.put("category", item.getCategory());
        foodItemData.put("price", item.getPrice());
        foodItemData.put("customizations", item.getCustomizations());

        orderDocRef.collection("foodItems").document().set(foodItemData)
                .addOnSuccessListener(aVoid -> System.out.println("Food item added to order"))
                .addOnFailureListener(e -> System.err.println("Error adding food item to order: " + e.getMessage()));
    }
    public void deleteOrder() {}
}
