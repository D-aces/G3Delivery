package com.example.g3delivery;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.g3delivery.adapter.MenuItemAdapter;
import com.example.g3delivery.data.model.FoodItem;
import com.example.g3delivery.data.model.Menu;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuItemAdapter menuItemAdapter;
    private List<FoodItem> foodItems;

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

        // Retrieve the Menu data passed from the previous activity
        Menu menu = (Menu) getIntent().getSerializableExtra("menuData");
        if (menu != null) {
            foodItems = menu.getMenu();
        }

        // Set up the RecyclerView
        menuRecyclerView = findViewById(R.id.menu_recycler_view);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuItemAdapter = new MenuItemAdapter(foodItems);
        menuRecyclerView.setAdapter(menuItemAdapter);
    }
}
