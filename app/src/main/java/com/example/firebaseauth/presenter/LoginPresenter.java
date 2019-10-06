package com.example.firebaseauth.presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.firebaseauth.AppConstants;
import com.example.firebaseauth.BuildConfig;
import com.example.firebaseauth.model.User;
import com.example.firebaseauth.network.ApiClient;
import com.example.firebaseauth.network.ApiInterface;
import com.example.firebaseauth.view.LoginActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginActivityView mView;
    private ApiInterface apiInterface;

    public LoginPresenter(LoginActivityView mView) {
        this.mView = mView;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void signIn(String email, String password) {
        if (!validateData(email, password)) {
            return;
        }
        User user = new User(email, password);
        Call<User> signInCall = apiInterface.signIn(BuildConfig.ApiKey, user);
        signInCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    saveIdToken(response.body().idToken);
                    mView.launchHomeScreen();
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "signInWithEmail:failure" + response.errorBody());
                    mView.displayErrorToast("Authentication failed. Try signing up first.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", t);
                mView.displayErrorToast("Authentication failed. Try signing up first.");
            }
        });

    }

    public void signUp(String email, String password) {
        if (!validateData(email, password)) {
            return;
        }
        User user = new User(email, password);
        Call<User> signUpCall = apiInterface.signUp(BuildConfig.ApiKey, user);
        signUpCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Sign up success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    saveIdToken(response.body().idToken);
                    mView.launchHomeScreen();
                }
                else {
                    // If sign up fails, display a message to the user.
                    Log.d(TAG, "createUserWithEmail:failure" + response.errorBody());
                    mView.displayErrorToast("Sign up failed");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // If sign up fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", t);
                mView.displayErrorToast("Sign up failed");
            }
        });
    }

    private void saveIdToken(String idToken) {
        Log.d(TAG, "id TOKEN " + idToken);
        SharedPreferences sharedPrefs = mView.getSharedPrefs();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(AppConstants.USER_ID_TOKEN, idToken);
        editor.apply();
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
