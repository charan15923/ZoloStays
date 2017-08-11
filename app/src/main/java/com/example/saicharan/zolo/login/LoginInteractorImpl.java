package com.example.saicharan.zolo.login;


import android.util.Log;

import com.example.saicharan.zolo.DatabaseHelper;
import com.example.saicharan.zolo.SessionManagement;

import javax.inject.Inject;

/**
 * Created by NaNi on 06/08/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private final DatabaseHelper dHelper;
    private final SessionManagement sManager;


    @Inject
    public LoginInteractorImpl(DatabaseHelper dHelper,SessionManagement sManager){
        this.dHelper=dHelper;
        this.sManager=sManager;
    }
    @Override
    public void login(String phone, String password, onLoginFinishedListener listener) {
        if(phone.length()==10 && password.length()>=8) {
            if (dHelper.checkUser(phone, password)) {
                int id = dHelper.getColumnid(phone, password);
                if(id!=-1){
                    Log.i("IDD",""+id);
                sManager.createLoginSession(phone, id);
                listener.onSuccess();
                }else
                    listener.onFailure("Something went wrong.Try Again!");

            } else {
                listener.onFailure("Invalid phone number or password");
            }
        }else{
            if(phone.length()<10)
                listener.onFailure("Enter valid phone number");
            if(password.length()<8)
                listener.onFailure("Enter valid password");
        }
    }


}
