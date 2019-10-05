package com.example.firebaseauth.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.firebaseauth.view.LoginActivityView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginActivityView mView;
    private FirebaseAuth mAuth;

    public LoginPresenter(LoginActivityView mView) {
        this.mView = mView;
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void signIn(String email, String password) {
        if (!validateData(email, password)) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mView, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            mView.launchHomeScreen();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            mView.displayErrorToast("Authentication failed. Try signing up first.");
                        }
                    }
                });
    }

    public void signUp(String email, String password) {
        if (!validateData(email, password)) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mView, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            mView.launchHomeScreen();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            mView.displayErrorToast("Sign up failed");
                        }
                    }
                });
    }

    private boolean validateData(String email, String password) {
        if (!isValidEmail(email)) {
            mView.showEmailError("Please enter a valid email id");
            return false;
        } else {
            mView.showEmailError("");
        }
        if (!isValidPassword(password)) {
            mView.showPasswordError("Please enter a password with minimum 6 characters");
            return false;
        } else {
            mView.showPasswordError("");
        }

        return true;
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return !TextUtils.isEmpty(email.trim()) && email.matches(regex);
    }
}
