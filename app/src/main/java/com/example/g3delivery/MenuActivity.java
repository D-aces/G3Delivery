package com.example.g3delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
import com.example.g3delivery.data.datasource.DataLoadCallback;
import com.example.g3delivery.data.model.Order;
import com.example.g3delivery.data.model.Restaurant;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuItemAdapter menuItemAdapter;
    private Menu menu;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Reference the back button
        ImageButton backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(v -> {
            // Navigate back to RestaurantCatalogueActivity
            Intent intent = new Intent(MenuActivity.this, RestaurantCatalogueActivity.class);
            startActivity(intent);
        });

        // Set up system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the menuId from the Intent extras
        String menuId = getIntent().getStringExtra("menuId");
        System.out.println(menuId);

        // Initialize RecyclerView and Adapter
        menuRecyclerView = findViewById(R.id.menu_recycler_view);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Load menu items from Firestore
        loadMenuItems(menuId);
        order = new Order();
        Intent intent = getIntent();
        Restaurant rest = intent.getParcelableExtra("Restaurant");
        order.setRestaurant(rest);
    }

    private void loadMenuItems(String menuId) {
        AppDataSource db = new AppDataSource();

        // Fetch menu data from Firestore
        db.getMenuForRestaurant(menuId, new DataLoadCallback<Menu>() {
            @Override
            public void onDataLoaded(Menu data) {
                menu = data;
                menuItemAdapter = new MenuItemAdapter(menu.getItems(), order);
                menuRecyclerView.setAdapter(menuItemAdapter);
            }

            @Override
            public void onError(Exception e) {
                System.err.println(e);
            }
        });
    }

    public void openCart(View view){
            // Navigate back to Cart Activity
            Intent intent = new Intent(MenuActivity.this, CheckoutActivity.class);
            intent.putExtra("Order", order);
            startActivity(intent);
    }
}
