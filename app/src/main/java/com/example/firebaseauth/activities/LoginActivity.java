package com.example.firebaseauth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebaseauth.R;
import com.example.firebaseauth.presenter.LoginPresenter;
import com.example.firebaseauth.view.LoginActivityView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    // Views
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText edtEmail;
    private TextInputEditText edtPassword;
    private Button btnSignUp;
    private Button btnSignIn;

    // Presenter
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignIn = findViewById(R.id.btn_sign_in);

        mPresenter = new LoginPresenter(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText() != null ? edtEmail.getText().toString() : "";
                String password = edtPassword.getText() != null ? edtPassword.getText().toString() : "";
                mPresenter.signIn(email, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText() != null ? edtEmail.getText().toString() : "";
                String password = edtPassword.getText() != null ? edtPassword.getText().toString() : "";
                mPresenter.signUp(email, password);
            }
        });
    }


    @Override
    public void showEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void launchHomeScreen() {
        tilEmail.setError("");
        tilPassword.setError("");
        edtEmail.setText("");
        edtPassword.setText("");
        startActivity(new Intent(this, HomeActivity.class));
    }
}
