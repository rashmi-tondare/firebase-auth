package com.example.firebaseauth.presenter;

import com.example.firebaseauth.view.HomeActivityView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePresenter {
    private HomeActivityView mView;
    private FirebaseAuth mAuth;

    private FirebaseUser user;

    public HomePresenter(HomeActivityView mView) {
        this.mView = mView;
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void fetchUser() {
        user = mAuth.getCurrentUser();
        if (user != null) {
            mView.displayEmail(user.getEmail());
        }
        else {
            mView.displayErrorToast("Oops! Something went wrong");
        }
    }

    public void signOut() {
        mAuth.signOut();
        mView.finishActivity();
    }
}
