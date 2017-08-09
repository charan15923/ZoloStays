package com.example.saicharan.zolo.login;

/**
 * Created by NaNi on 06/08/17.
 */

public interface LoginInteractor {
    interface onLoginFinishedListener{

        void onSuccess();
        void onFailure(String message);
    }
    void login(String phone, String password, onLoginFinishedListener listener);
}
