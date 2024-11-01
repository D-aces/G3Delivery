package com.example.g3delivery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.g3delivery.R;
import com.example.g3delivery.data.model.FoodItem;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    private List<FoodItem> foodItems;

    public MenuItemAdapter(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
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
        holder.price.setText(String.format("$%.2f", item.getPrice()));
        holder.foodDescription.setText(formatCustomizations(item.getCustomizations()));

        // Uncomment and set up image loading with Glide or another library if you have food images
        // Glide.with(holder.itemView.getContext())
        //      .load(item.getImageUrl())
        //      .into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    private String formatCustomizations(String[] customizations) {
        if (customizations == null || customizations.length == 0) return "No customizations";
        return String.join(", ", customizations);
    }

    static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, price, foodDescription;
        ImageView foodImage;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_Name);
            price = itemView.findViewById(R.id.price);
            foodDescription = itemView.findViewById(R.id.food_description);
            foodImage = itemView.findViewById(R.id.FoodImage);
        }
    }
}
