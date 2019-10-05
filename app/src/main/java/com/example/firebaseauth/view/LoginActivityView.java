package com.example.firebaseauth.view;

public interface LoginActivityView extends BaseActivityView {
    void showEmailError(String error);
    void showPasswordError(String error);
    void launchHomeScreen();
}
