package com.example.saicharan.zolo.login;

/**
 * Created by NaNi on 06/08/17.
 */

public interface LoginView {
    void showSnack(String errmsg);
    void navToDash();
    void enableButton(Boolean msg);
}
