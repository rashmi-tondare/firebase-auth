package com.example.firebaseauth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseauth.R;
import com.example.firebaseauth.presenter.HomePresenter;
import com.example.firebaseauth.view.HomeActivityView;

public class HomeActivity extends BaseActivity implements HomeActivityView {

    // Views
    private TextView txtEmail;
    private Button btnSignOut;
    private Button btnResetPassword;

    // Presenter
    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtEmail = findViewById(R.id.txt_email);
        btnSignOut = findViewById(R.id.btn_sign_out);
        btnResetPassword = findViewById(R.id.btn_reset_password);

        mPresenter = new HomePresenter(this);
        mPresenter.fetchUser();

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signOut();
            }
        });
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchResetPassword();
            }
        });
    }

    @Override
    public void displayEmail(String email) {
        txtEmail.setText(getString(R.string.txt_email, email));
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void launchResetPassword() {
        startActivity(new Intent(this, ResetPasswordActivity.class));
    }
}
