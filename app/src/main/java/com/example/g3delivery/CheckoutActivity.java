package com.example.g3delivery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.g3delivery.data.model.FoodItem;
import com.example.g3delivery.data.model.Order;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CheckoutActivity extends AppCompatActivity {
    private TextView restaurantName, subtotal, tax, delivery, total;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);

        // Set window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Textviews
        restaurantName = findViewById(R.id.restaurant_name);
        subtotal = findViewById(R.id.subtotal_text);
        tax = findViewById(R.id.taxes_text);
        delivery = findViewById(R.id.delivery_text);
        total = findViewById(R.id.total_text);
        Intent intent = getIntent();
        order = intent.getParcelableExtra("Order");

        // Calculate Amounts
        order.calculateSubtotal();
        order.setDeliveryFees(2);
        order.calculateTotal();

        HashMap<FoodItem, Integer> food = order.getFoodMap();
        for(FoodItem fd : food.keySet()){
            System.out.println(fd.getName());
            System.out.println(food.get(fd));
        }

        // Append the values
        DecimalFormat df = new DecimalFormat("#.00");

        restaurantName.append(order.getRestaurant().getName());
        subtotal.append(df.format(order.getSubtotal()));
        tax.append(df.format(order.getCalculatedTax()));
        delivery.append(df.format(order.getDeliveryFees()));
        total.append(df.format(order.getTotal()));

        // Find the button and set its onClick listener
        Button checkoutButton = findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ScrollingActivity
                Intent intent = new Intent(CheckoutActivity.this, ContentScrollingActivity.class);
                startActivity(intent);
            }
        });
    }
}
