package com.example.saicharan.zolo.register;

/**
 * Created by NaNi on 07/08/17.
 */

public interface RegisterPresenter {
    void validateCred(String phone, String email, String name, String password);
    void onDestroy();
    void setView(RegisterView mRegister);
    void setModel(RegisterInteractorImpl mRegisterInteractorImpl);

}
