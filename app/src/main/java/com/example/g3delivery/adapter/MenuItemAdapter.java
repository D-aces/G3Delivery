package com.example.g3delivery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.R;
import com.example.g3delivery.data.model.FoodItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    private List<FoodItem> foodItems;

    // Adapter constructor now takes a Map of food items
    public MenuItemAdapter(Map<String, FoodItem> items) {
        this.foodItems = new ArrayList<>(items.values());
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        FoodItem item = foodItems.get(position);
        holder.foodName.setText(item.getName());
        holder.foodPrice.setText("$" + item.getPrice());
        //holder.foodCategory.setText(item.getCategory());

        // Format customizations as a string and set it in the TextView
//        holder.foodDescription.setText(formatCustomizations(item.getCustomizations()));
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    // Helper method to format customizations
//    private String formatCustomizations(Map<String, Double> customizations) {
//        if (customizations == null || customizations.isEmpty()) {
//            return "No customizations available";
//        }
//
//        return customizations.entrySet().stream()
//                .map(entry -> entry.getKey() + " - $" + entry.getValue())
//                .collect(Collectors.joining(", "));
//    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodPrice, foodCategory, foodDescription;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
            //foodCategory = itemView.findViewById(R.id.food_category);
            foodDescription = itemView.findViewById(R.id.food_description);
        }
    }
}
