package com.example.saicharan.zolo.forgot;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.saicharan.zolo.DatabaseHelper;
import com.example.saicharan.zolo.MyApp;
import com.example.saicharan.zolo.SessionManagement;

/**
 * Created by NaNi on 07/08/17.
 */

public class ForgotInteractorImpl implements ForgotInteractor {

    private final DatabaseHelper dHelper;
    private final SessionManagement sManager;
    ForgotPresenterImpl mForgotPresenter;

    public ForgotInteractorImpl(ForgotPresenterImpl mLoginPresenter){
        this.mForgotPresenter=mLoginPresenter;
        dHelper = new DatabaseHelper(MyApp.getContext());
        sManager= new SessionManagement(MyApp.getContext());
    }
    @Override
    public void forgot(String phone,String newpass, onEmailSentListener listener) {
        if(!TextUtils.isEmpty(phone) && Patterns.EMAIL_ADDRESS.matcher(phone).matches()) {
            if (dHelper.checkUserEmail(phone)) {
                if (dHelper.updatePass(phone, newpass) == 1) {
                        listener.onSuccess(newpass);
                } else {
                    listener.onFailure("Unable to update password");
                }
            } else {
                    listener.onFailure("User not registered");
            }

        }else{
                listener.onFailure("please enter a valid email id");
        }
    }

}
