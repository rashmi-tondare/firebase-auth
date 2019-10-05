package com.example.firebaseauth.view;

public interface HomeActivityView extends BaseActivityView {
    void displayEmail(String email);
    void finishActivity();
    void launchResetPassword();
}
