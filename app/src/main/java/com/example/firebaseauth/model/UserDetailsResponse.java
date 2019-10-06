package com.example.firebaseauth.model;

import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse {
    @SerializedName("users")
    public User[] users;

    public UserDetailsResponse(User[] users) {
        this.users = users;
    }
}
