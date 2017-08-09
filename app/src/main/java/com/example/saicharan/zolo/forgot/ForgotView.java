package com.example.saicharan.zolo.forgot;

/**
 * Created by NaNi on 07/08/17.
 */

public interface ForgotView {

    void showSnack(String errmsg);
    void navToLogin();
    void sendPasswordMail(String newpass);
}
