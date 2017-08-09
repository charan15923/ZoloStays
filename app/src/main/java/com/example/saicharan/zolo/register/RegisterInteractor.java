package com.example.saicharan.zolo.register;

/**
 * Created by NaNi on 07/08/17.
 */

public interface RegisterInteractor {
    interface onRegisterFinishedListener{
        void onSuccess();
        void onFailure(String message);
    }
    void register(String phone, String name, String email, String password, onRegisterFinishedListener listener);
}
