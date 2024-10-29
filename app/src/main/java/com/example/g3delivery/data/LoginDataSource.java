package com.example.g3delivery.data;

import androidx.appcompat.app.AppCompatActivity;
import com.example.g3delivery.data.model.LoggedInUser;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private final FirebaseAuth mAuth;

    public LoginDataSource(){
        // Initialize Firebase Auth Instance
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<LoggedInUser> login(String email, String password) {
        TaskCompletionSource<LoggedInUser> taskCompletionSource = new TaskCompletionSource<>();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                String userId = mAuth.getCurrentUser().getUid();
                String displayName = mAuth.getCurrentUser().getDisplayName();
                LoggedInUser loggedInUser = new LoggedInUser(userId, displayName);
                taskCompletionSource.setResult(loggedInUser);
            } else {
                taskCompletionSource.setException(new Exception("Login failed"));
            }
        });

        return taskCompletionSource.getTask();
    }

    public void logout() {
        mAuth.signOut();
    }
}