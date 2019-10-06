package com.example.firebaseauth.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.firebaseauth.AppConstants;
import com.example.firebaseauth.BuildConfig;
import com.example.firebaseauth.model.User;
import com.example.firebaseauth.model.UserDetailsResponse;
import com.example.firebaseauth.network.ApiClient;
import com.example.firebaseauth.network.ApiInterface;
import com.example.firebaseauth.view.HomeActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private static final String TAG = HomePresenter.class.getSimpleName();

    private HomeActivityView mView;
    private ApiInterface apiInterface;


    public HomePresenter(HomeActivityView mView) {
        this.mView = mView;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void fetchUser() {
        User user = new User(getIdToken());
        Call<UserDetailsResponse> getProfileCall = apiInterface.getProfile(BuildConfig.ApiKey, user);
        getProfileCall.enqueue(new Callback<UserDetailsResponse>() {
            @Override
            public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {
                if (response.isSuccessful()) {
                    mView.displayEmail(response.body().users[0].email);
                } else {
                    Log.d(TAG, "getProfileCall:Error " + response.errorBody());
                    mView.displayErrorToast("Oops! Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                Log.w(TAG, "getProfileCall:Error", t);
                mView.displayErrorToast("Oops! Something went wrong");
            }
        });
    }

    private String getIdToken() {
        SharedPreferences sharedPrefs = mView.getSharedPrefs();
        return sharedPrefs.getString(AppConstants.USER_ID_TOKEN, "");
    }

    public void signOut() {
        SharedPreferences sharedPreferences = mView.getSharedPrefs();
        sharedPreferences.edit().remove(AppConstants.USER_ID_TOKEN).apply();
        mView.finishActivity();
    }
}
