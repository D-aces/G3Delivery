package com.example.g3delivery.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.R;
import com.example.g3delivery.data.model.Restaurant;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList; // List to hold restuarants

    public RestaurantAdapter(List<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);  // Get the current restaurant
        holder.restaurant_name.setText(restaurant.getName());
        holder.restaurant_address.setText(restaurant.getAddress());
        holder.description.setText(restaurant.getDescription());
        //holder.labels.setText(restaurant.getLabels()); // A bit strange to implement O>O TODO
        holder.restaurant_rating.setText(String.format("%.1f", restaurant.getRating()));
        //holder.restaurant_name.setText(restaurant.getName()); for the image
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        LinearLayout restaurantCard;
        TextView restaurant_name, restaurant_address, description, labels, restaurant_rating;
        // ImageView IconImage; maybe later TODO

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            restaurant_address = itemView.findViewById(R.id.restaurant_address);
            description = itemView.findViewById(R.id.description);
            labels = itemView.findViewById(R.id.labels);
            restaurant_rating = itemView.findViewById(R.id.restaurant_rating);
        }

    }
}