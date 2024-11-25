package com.example.g3delivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.g3delivery.data.model.Order;

public class CheckoutActivity extends AppCompatActivity {
    private TextView restaurantName, subtotal, tax, delivery, total;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
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

        // Append the values
        restaurantName.append(order.getRestaurant().getName());
        subtotal.append(Double.toString(order.getSubtotal()));
        tax.append(Double.toString(order.getCalculatedTax()));
        delivery.append(Double.toString(order.getDeliveryFees()));
        total.append(Double.toString(order.getTotal()));
    }
}