package com.example.g3delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.adapter.OrderItemAdapter;
import com.example.g3delivery.data.model.Order;

import java.text.DecimalFormat;

public class CheckoutActivity extends AppCompatActivity {
    private TextView restaurantName, subtotal, tax, delivery, total;
    private RecyclerView orderItemsRecyclerView;
    private Order order;

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

        // Initialize Views
        restaurantName = findViewById(R.id.restaurant_name);
        subtotal = findViewById(R.id.subtotal_text);
        tax = findViewById(R.id.taxes_text);
        delivery = findViewById(R.id.delivery_text);
        total = findViewById(R.id.total_text);
        orderItemsRecyclerView = findViewById(R.id.order_items_recycler_view);

        // Retrieve Order object from Intent
        Intent intent = getIntent();
        order = intent.getParcelableExtra("Order");

        // Calculate Amounts
        order.calculateSubtotal();
        order.setDeliveryFees(2);
        order.calculateTotal();

        // Update the text views
        DecimalFormat df = new DecimalFormat("#.00");
        restaurantName.setText(order.getRestaurant().getName());
        subtotal.setText(String.format("Subtotal: $%s", df.format(order.getSubtotal())));
        tax.setText(String.format("Tax: $%s", df.format(order.getCalculatedTax())));
        delivery.setText(String.format("Delivery: $%s", df.format(order.getDeliveryFees())));
        total.setText(String.format("Total: $%s", df.format(order.getTotal())));

        // Initialize RecyclerView
        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderItemAdapter adapter = new OrderItemAdapter(order.getFoodMap());
        orderItemsRecyclerView.setAdapter(adapter);

        // Checkout button
        Button checkoutButton = findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> {
            // Handle checkout logic or navigate to another activity
            Intent nextIntent = new Intent(CheckoutActivity.this, OrderActivity.class);
            intent.putExtra("Order", order);
            startActivity(nextIntent);
        });
    }
}
