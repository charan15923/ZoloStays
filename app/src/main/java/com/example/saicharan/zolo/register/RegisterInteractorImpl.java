package com.example.saicharan.zolo.register;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.saicharan.zolo.DatabaseHelper;

import javax.inject.Inject;

/**
 * Created by NaNi on 07/08/17.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    private final DatabaseHelper dHelper;

    @Inject
    public RegisterInteractorImpl(DatabaseHelper dHelper){
       this.dHelper=dHelper;

    }
    @Override
    public void register(String phone, String name, String email,String password, onRegisterFinishedListener listener) {
        if (Patterns.PHONE.matcher(phone).matches() && phone.length() == 10 && !dHelper.checkUserPhn(phone)) {
            if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && !dHelper.checkUserEmail(email)) {
                if (name.length()>=3) {
                        if(password.length()>=8){
                            if(dHelper.addUser(phone,email,name,password) != 404)
                                listener.onSuccess();
                            else
                                listener.onFailure("Please try again");
                        }else{
                            listener.onFailure("Password should contain atleast 8 letters");
                        }
                }else{
                    listener.onFailure("Name should contain atleast 3 letters");
                }
            }else{
                if(dHelper.checkUserEmail(email))
                listener.onFailure("Email already registered");
                else
                    listener.onFailure("Invalid email id");
            }
        }else {
            if(dHelper.checkUserPhn(phone))
            listener.onFailure("Phone number already registered");
            else
                listener.onFailure("Invalid phone number");
        }
    }
}


