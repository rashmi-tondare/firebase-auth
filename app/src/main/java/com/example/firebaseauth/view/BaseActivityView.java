package com.example.firebaseauth.view;

import android.content.SharedPreferences;

public interface BaseActivityView {
    void displayErrorToast(String error);
    SharedPreferences getSharedPrefs();
}
