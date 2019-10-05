package com.example.firebaseauth.activities;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebaseauth.view.BaseActivityView;

public class BaseActivity extends AppCompatActivity implements BaseActivityView {
    @Override
    public void displayErrorToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
