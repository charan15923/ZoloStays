package com.example.saicharan.zolo.forgot;

/**
 * Created by NaNi on 07/08/17.
 */

public interface ForgotInteractor {
        interface onEmailSentListener{

            void onSuccess(String newpass);
            void onFailure(String message);
        }
    void forgot(String phone, String newpass, onEmailSentListener listener);
}
