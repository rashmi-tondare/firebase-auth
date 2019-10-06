package com.example.firebaseauth.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("returnSecureToken")
    public boolean returnSecureToken;

    @SerializedName("idToken")
    public String idToken;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.returnSecureToken = true;
    }

    public User(String idToken) {
        this.idToken = idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isReturnSecureToken() {
        return returnSecureToken;
    }

    public void setReturnSecureToken(boolean returnSecureToken) {
        this.returnSecureToken = returnSecureToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
