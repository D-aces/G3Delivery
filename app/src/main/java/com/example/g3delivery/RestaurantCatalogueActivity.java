package com.example.g3delivery;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.adapter.RestaurantAdapter;
import com.example.g3delivery.data.datasource.AppDataSource;
import com.example.g3delivery.data.datasource.DataLoadCallback;
import com.example.g3delivery.data.model.Restaurant;

import java.util.List;

public class RestaurantCatalogueActivity extends AppCompatActivity {
    private RecyclerView listRestaurants;
    private RestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Set up the list of restaurants with a recycler view
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_catalogue);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up recycler view
        listRestaurants = findViewById(R.id.listRestaurant);
        listRestaurants.setLayoutManager(new LinearLayoutManager(this));

        AppDataSource appDataSource = new AppDataSource();
        appDataSource.getRestaurants(new DataLoadCallback() {
            @Override
            public void onDataLoaded(List<Restaurant> restaurantList) {
                if (restaurantList != null && !restaurantList.isEmpty()) {
                    Log.d("RestaurantCatalogueActivity", "Restaurants loaded: " + restaurantList.size());
                } else {
                    Log.d("RestaurantCatalogueActivity", "No restaurants loaded");
                }

                restaurantAdapter = new RestaurantAdapter(restaurantList);
                listRestaurants.setAdapter(restaurantAdapter);
            }
        });
    }
}