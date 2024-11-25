package com.example.g3delivery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.R;
import com.example.g3delivery.data.model.FoodItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private List<Map.Entry<FoodItem, Integer>> foodItemList;

    public OrderItemAdapter(HashMap<FoodItem, Integer> foodMap) {
        this.foodItemList = List.copyOf(foodMap.entrySet());
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        Map.Entry<FoodItem, Integer> entry = foodItemList.get(position);
        FoodItem foodItem = entry.getKey();
        int quantity = entry.getValue();

        holder.itemName.setText(foodItem.getName() + " x" + quantity);
        holder.itemPrice.setText(String.format("$%.2f", foodItem.getPrice() * quantity));
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.order_item_name);
            itemPrice = itemView.findViewById(R.id.order_item_price);
        }
    }
}
