package com.example.g3delivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g3delivery.adapter.OrderItemAdapter;
import com.example.g3delivery.data.model.Order;

import java.text.DecimalFormat;

public class OrderActivity extends AppCompatActivity {

    private TextView subtotalText, taxText, deliveryText, totalText;
    private RecyclerView orderItemsRecyclerView;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize Views
        subtotalText = findViewById(R.id.subtotal_text);
        taxText = findViewById(R.id.tax_text);
        deliveryText = findViewById(R.id.delivery_text);
        totalText = findViewById(R.id.total_text);
        orderItemsRecyclerView = findViewById(R.id.order_items_recycler_view);

        // Retrieve Order object from Intent
        Intent intent = getIntent();
        order = intent.getParcelableExtra("Order");

        System.out.println(order.getFoodMap());

        // Calculate and display amounts
        order.calculateSubtotal();
        order.setDeliveryFees(2); // Fixed delivery fee
        order.calculateTotal();

        DecimalFormat df = new DecimalFormat("#.00");
        subtotalText.setText(String.format("Subtotal: $%s", df.format(order.getSubtotal())));
        taxText.setText(String.format("Tax: $%s", df.format(order.getCalculatedTax())));
        deliveryText.setText(String.format("Delivery: $%s", df.format(order.getDeliveryFees())));
        totalText.setText(String.format("Total: $%s", df.format(order.getTotal())));

        // Set up RecyclerView
        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderItemAdapter adapter = new OrderItemAdapter(order.getFoodMap());
        orderItemsRecyclerView.setAdapter(adapter);
    }
}
