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
import com.example.g3delivery.data.datasource.DataLoadCallback;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuItemAdapter menuItemAdapter;
    private Menu menu;

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

        // Retrieve the menuId from the Intent extras
        String menuId = getIntent().getStringExtra("menuId");
        System.out.println(menuId);

        // Initialize RecyclerView and Adapter
        menuRecyclerView = findViewById(R.id.menu_recycler_view);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load menu items from Firestore
        loadMenuItems(menuId);
    }

    private void loadMenuItems(String menuId) {
        AppDataSource db = new AppDataSource();

        // Fetch menu data from Firestore
        db.getMenuForRestaurant(menuId, new DataLoadCallback<Menu>() {
            @Override
            public void onDataLoaded(Menu data) {
                menu = data;
                menuItemAdapter = new MenuItemAdapter(menu.getItems());
                menuRecyclerView.setAdapter(menuItemAdapter);
            }

            @Override
            public void onError(Exception e) {
                System.err.println(e);
            }
        });
    }
}
