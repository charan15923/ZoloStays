package com.example.saicharan.zolo.login;

import android.text.TextUtils;
import android.util.Patterns;

import javax.inject.Inject;

/**
 * Created by NaNi on 06/08/17.
 */

public class LoginPresenterImpl implements LoginPresenter,LoginInteractor.onLoginFinishedListener {
     LoginView mLoginView;
    LoginInteractor mLoginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView mLoginView,LoginInteractorImpl loginInteractor){
        this.mLoginView=mLoginView;
        this.mLoginInteractor=loginInteractor;
    }
    @Override
    public void validateCred(String phone, String password) {
        mLoginInteractor.login(phone,password,this);
    }



    @Override
    public void onDestroy() {
        if(mLoginView != null){
            mLoginView=null;
        }
    }

    @Override
    public void setView(LoginView mLogin) {
        this.mLoginView=mLogin;
    }

    @Override
    public void setModel(LoginInteractorImpl mLoginInteractorImpl) {
            mLoginInteractor=mLoginInteractorImpl;
    }

    @Override
    public void validatePhn(String phnData) {

    }

    @Override
    public void validatePass(String msg) {
        if(msg.length()>=8){
            mLoginView.enableButton(true);
        }
        else{
            mLoginView.enableButton(false);
        }

    }



    @Override
    public void onSuccess() {
        if(mLoginView != null){
            mLoginView.navToDash();
        }
    }

    @Override
    public void onFailure(String message) {
        if(mLoginView != null){
            mLoginView.showSnack(message);
        }
    }
}
