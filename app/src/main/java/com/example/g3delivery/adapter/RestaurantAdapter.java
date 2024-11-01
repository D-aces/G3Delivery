package com.example.g3delivery.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.g3delivery.R;
import com.example.g3delivery.data.model.Restaurant;
import java.util.List;


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
        holder.restaurant_rating.setText(String.format("%.1f", restaurant.getRating()));

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(restaurant.getLogoImage()) // Replace getImageUrl with your method for fetching the image link
                .into(holder.iconImage);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        LinearLayout restaurantCard;
        TextView restaurant_name, restaurant_address, description, labels, restaurant_rating;
        ImageView iconImage;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantCard = itemView.findViewById(R.id.restaurant_wrapper);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            restaurant_address = itemView.findViewById(R.id.restaurant_address);
            description = itemView.findViewById(R.id.description);
            labels = itemView.findViewById(R.id.labels);
            iconImage = itemView.findViewById(R.id.IconImage);
            restaurant_rating = itemView.findViewById(R.id.restaurant_rating);
        }

    }
}