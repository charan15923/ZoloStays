package com.example.saicharan.zolo.login;

/**
 * Created by NaNi on 06/08/17.
 */

public interface LoginPresenter {
    void validateCred(String phone, String password);
    void onDestroy();
    void setView(LoginView mLogin);
    void setModel(LoginInteractorImpl mLoginInteractorImpl);
    void validatePhn(String msg);
    void validatePass(String msg);
}
