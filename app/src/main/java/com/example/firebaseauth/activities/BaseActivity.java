package com.example.firebaseauth.activities;

import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebaseauth.AppConstants;
import com.example.firebaseauth.view.BaseActivityView;

public class BaseActivity extends AppCompatActivity implements BaseActivityView {

    private static SharedPreferences mSharedPrefs;

    @Override
    public void displayErrorToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public SharedPreferences getSharedPrefs() {
        if (mSharedPrefs == null) {
            mSharedPrefs = getSharedPreferences(AppConstants.SHARED_PREFS, MODE_PRIVATE);
        }
        return mSharedPrefs;
    }
}
