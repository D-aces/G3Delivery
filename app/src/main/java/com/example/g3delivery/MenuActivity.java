package com.example.g3delivery;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.adapter.MenuItemAdapter;
import com.example.g3delivery.data.datasource.AppDataSource;
import com.example.g3delivery.data.model.FoodItem;
import com.example.g3delivery.data.model.Menu;

import java.util.ArrayList;
import java.util.List;

import com.example.g3delivery.data.datasource.DataLoadCallback;


public class MenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuItemAdapter menuItemAdapter;
    private List<FoodItem> foodItems = new ArrayList<>(); // Initialize to avoid null references

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Set up system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the MenuId from the Intent extras
        String menuId = getIntent().getStringExtra("MenuId");
        String restaurantId = getIntent().getStringExtra("RestaurantId"); // Assuming you also pass RestaurantId

        // Initialize RecyclerView and Adapter
        menuRecyclerView = findViewById(R.id.menu_recycler_view);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuItemAdapter = new MenuItemAdapter(foodItems);
        menuRecyclerView.setAdapter(menuItemAdapter);

        // Load menu items from Firestore
        loadMenuItems(restaurantId, menuId);
    }

    private void loadMenuItems(String restaurantId, String menuId) {
        AppDataSource db = new AppDataSource();

        // Fetch menu data from Firestore
        db.getMenuForRestaurant(restaurantId, menuId, new DataLoadCallback<Menu>() {
            @Override
            public void onDataLoaded(Menu menu) {
                // Update the list of food items
                foodItems.clear();
                foodItems.addAll(menu.getItems().values());
                menuItemAdapter.notifyDataSetChanged(); // Notify adapter of data change
            }

            @Override
            public void onError(Exception e) {
                // Handle errors, e.g., show a message to the user
                System.err.println("Error loading menu items: " + e.getMessage());
            }
        });
    }

}
