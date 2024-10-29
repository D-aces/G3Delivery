package com.example.g3delivery.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.g3delivery.data.LoginRepository;
import com.example.g3delivery.data.Result;
import com.example.g3delivery.data.model.LoggedInUser;
import com.example.g3delivery.R;
import com.google.android.gms.tasks.Task;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>(false); // Track login status
    private LoginRepository loginRepository;
    public static final int ERROR_INVALID_CREDENTIALS = 1;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        checkLoginStatus(); // Check login status during initialization
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

   public LiveData<Boolean> isLoggedIn() {
        return loggedIn;
    }

    public void login(String email, String password) {
        // Initiate the login request through LoginRepository
        Task<LoggedInUser> task = loginRepository.login(email, password);

        // Add a listener to handle the result when the login task is complete
        task.addOnCompleteListener(loginTask -> {
            if (loginTask.isSuccessful()) {
                LoggedInUser data = loginTask.getResult();
                loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
                loggedIn.setValue(true); // Set login status to true
            } else {
                loginResult.setValue(new LoginResult(ERROR_INVALID_CREDENTIALS));
                loggedIn.setValue(false); // Set login status to false
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    // Method to check the login status
    private void checkLoginStatus() {
        // Here you would check if the user is already logged in.
        // This is just a placeholder. Replace it with your actual check.
        loggedIn.setValue(loginRepository.isLoggedIn());
    }
}
