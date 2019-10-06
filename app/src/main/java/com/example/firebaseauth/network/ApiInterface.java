package com.example.firebaseauth.network;

import com.example.firebaseauth.model.User;
import com.example.firebaseauth.model.UserDetailsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/v1/accounts:signUp?")
    Call<User> signUp(@Query("key") String apiKey, @Body User user);

    @POST("/v1/accounts:signInWithPassword?")
    Call<User> signIn(@Query("key") String apiKey, @Body User user);

    @POST("/v1/accounts:lookup?")
    Call<UserDetailsResponse> getProfile(@Query("key") String apiKey, @Body User idToken);

    @POST("/v1/accounts:update?")
    Call<User> resetPassword(@Query("key") String apiKey, @Body User user);
}
