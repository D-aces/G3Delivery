package com.example.g3delivery.data;

import com.example.g3delivery.data.model.LoggedInUser;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private FirebaseAuth mAuth;

    public LoginDataSource(){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(String email, String password) {
        //mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(newComplete
    }

    public void logout() {
        // TODO: revoke authentication
    }
}