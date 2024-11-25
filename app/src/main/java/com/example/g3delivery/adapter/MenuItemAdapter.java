package com.example.g3delivery.adapter;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.R;
import com.example.g3delivery.data.model.FoodItem;
import com.example.g3delivery.data.model.Order;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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

    @SuppressLint("DiscouragedApi")
    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        FoodItem item = foodItems.get(position);
        holder.foodName.setText(item.getName());
        holder.foodPrice.setText("$" + item.getPrice());
        // The New Order :D
        Order order = new Order();
        AtomicInteger quantity = new AtomicInteger();

        String foodName = item.getName().toLowerCase().replace(" ", "_"); // Convert to drawable-friendly format
        // Retrieve the resource ID for the corresponding drawable
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(
                foodName, "drawable", holder.itemView.getContext().getPackageName());

        if (imageResId != 0) {
            // If the image exists, decode it to a Bitmap and set it
            Bitmap bitmap = BitmapFactory.decodeResource(holder.itemView.getContext().getResources(), imageResId);
            holder.foodImage.setImageBitmap(bitmap);
        } else {
            // If the image doesn't exist, set a default image
            holder.foodImage.setImageResource(R.drawable.cookie);
        }

        // Set the food description
        holder.foodDescription.setText(item.getDescription());

        // Convert the value of the counter to an integer and check if it's greater than 0
        holder.minusButton.setOnClickListener(v -> {
            if (quantity.intValue() > 0) {
                quantity.getAndDecrement();
                holder.itemQuantity.setText(String.valueOf(quantity.get()));
                // Get value of the atomic integer and passes it to addItemToOrder
                order.removeItemFromOrder(item, quantity.intValue());
            }
        });


        holder.plusButton.setOnClickListener(v -> {
            quantity.getAndIncrement();
            holder.itemQuantity.setText(String.valueOf(quantity.get()));
            // Get value of the atomic integer and passes it to addItemToOrder
            order.addItemToOrder(item, quantity.intValue());
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodPrice, foodDescription, itemQuantity;
        ImageView foodImage;
        Button plusButton, minusButton;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
            //foodCategory = itemView.findViewById(R.id.food_category);
            foodDescription = itemView.findViewById(R.id.food_description);
            foodImage = itemView.findViewById(R.id.food_image);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            plusButton = itemView.findViewById(R.id.button_plus);
            minusButton = itemView.findViewById(R.id.button_minus);
        }
    }
}
