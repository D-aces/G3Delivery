package com.example.g3delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContentScrollingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);

        // Find the button and set its onClick listener
        Button checkoutButton = findViewById(R.id.picture);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ScrollingActivity
                Intent intent = new Intent(ContentScrollingActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });





    }
}
