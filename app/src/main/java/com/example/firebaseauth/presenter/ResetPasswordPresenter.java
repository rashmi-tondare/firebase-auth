package com.example.firebaseauth.presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.firebaseauth.AppConstants;
import com.example.firebaseauth.BuildConfig;
import com.example.firebaseauth.model.User;
import com.example.firebaseauth.network.ApiClient;
import com.example.firebaseauth.network.ApiInterface;
import com.example.firebaseauth.view.ResetPasswordActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordPresenter {
    private static final String TAG = ResetPasswordPresenter.class.getSimpleName();

    private ResetPasswordActivityView mView;
    private ApiInterface apiInterface;

    public ResetPasswordPresenter(ResetPasswordActivityView mView) {
        this.mView = mView;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void resetPassword(String newPassword, String confirmPassword) {
        if (!validNewPassword(newPassword, confirmPassword)) {
            mView.displayErrorToast("Invalid new password");
            return;
        }

        User user = new User();
        user.setIdToken(mView.getSharedPrefs().getString(AppConstants.USER_ID_TOKEN, ""));
        user.setPassword(newPassword);
        Call<User> resetPasswordCall = apiInterface.resetPassword(BuildConfig.ApiKey, user);
        resetPasswordCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sharedPreferences = mView.getSharedPrefs();
                    sharedPreferences.edit().remove(AppConstants.USER_ID_TOKEN).apply();
                    mView.launchLoginScreen();
                }
                else {
                    Log.d(TAG, "resetPassword:failure" + response.errorBody());
                    mView.displayErrorToast("Password reset unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.w(TAG, "resetPassword:failure", t);
                mView.displayErrorToast("Password reset unsuccessful");
            }
        });
    }

    private boolean validNewPassword(String newPassword, String confirmPassword) {
        return !TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(confirmPassword)
                && newPassword.equals(confirmPassword) && newPassword.length() >= 6;
    }
}
