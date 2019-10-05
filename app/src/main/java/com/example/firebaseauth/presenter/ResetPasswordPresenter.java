package com.example.firebaseauth.presenter;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.firebaseauth.view.ResetPasswordActivityView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordPresenter {
    private static final String TAG = ResetPasswordPresenter.class.getSimpleName();

    private ResetPasswordActivityView mView;
    private FirebaseAuth mAuth;

    public ResetPasswordPresenter(ResetPasswordActivityView mView) {
        this.mView = mView;
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void resetPassword(String newPassword, String confirmPassword) {
        if (!validNewPassword(newPassword, confirmPassword)) {
            mView.displayErrorToast("Invalid new password");
            return;
        }

        mAuth.getCurrentUser().updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mAuth.signOut();
                    mView.launchLoginScreen();
                }
                else {
                    Log.w(TAG, "resetPassword:failure", task.getException());
                    mView.displayErrorToast("Password reset unsuccessful");
                }
            }
        });
    }

    private boolean validNewPassword(String newPassword, String confirmPassword) {
        return !TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(confirmPassword)
                && newPassword.equals(confirmPassword) && newPassword.length() >= 6;
    }
}
