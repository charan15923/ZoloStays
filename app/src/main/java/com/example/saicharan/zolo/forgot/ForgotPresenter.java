package com.example.saicharan.zolo.forgot;

/**
 * Created by NaNi on 07/08/17.
 */

public interface ForgotPresenter {
    void validateCred(String phone, String newpass);
    void onDestroy();
    void setView(ForgotView mForgot);
    void setModel(ForgotInteractorImpl mForgotInteractorImpl);


}
