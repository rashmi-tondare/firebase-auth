package com.example.firebaseauth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebaseauth.R;
import com.example.firebaseauth.presenter.ResetPasswordPresenter;
import com.example.firebaseauth.view.ResetPasswordActivityView;
import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordActivityView {

    // Views
    private TextInputEditText edtNewPassword;
    private TextInputEditText edtConfirmPassword;
    private Button btnResetPassword;

    // Presenter
    private ResetPasswordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mPresenter = new ResetPasswordPresenter(this);

        edtNewPassword = findViewById(R.id.edt_new_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        btnResetPassword = findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = edtNewPassword.getText() != null ? edtNewPassword.getText().toString() : "";
                String confirmPassword = edtConfirmPassword.getText() != null ? edtConfirmPassword.getText().toString() : "";

                mPresenter.resetPassword(newPassword, confirmPassword);
            }
        });
    }

    @Override
    public void launchLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
