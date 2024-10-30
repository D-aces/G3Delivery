package com.example.g3delivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.g3delivery.ui.login.LoginActivity;
import com.example.g3delivery.ui.login.viewModel.LoginViewModel;
import com.example.g3delivery.ui.login.LoginViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the LoginViewModel
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);

        // Observe the login status
        loginViewModel.isLoggedIn().observe(this, isLoggedIn -> {
            Intent activityIntent;
            if (isLoggedIn) {
                activityIntent = new Intent(MainActivity.this, RestaurantCatalogueActivity.class);
            } else {
                activityIntent = new Intent(MainActivity.this, LoginActivity.class);
            }
            startActivity(activityIntent);
            finish();
        });
    }
}
