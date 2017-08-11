package com.example.saicharan.zolo.forgot;

import javax.inject.Inject;

/**
 * Created by NaNi on 07/08/17.
 */

public class ForgotPresenterImpl implements ForgotPresenter,ForgotInteractor.onEmailSentListener {
    ForgotView mForgotView;
    ForgotInteractor mForgotInteractor;

    @Inject
    public ForgotPresenterImpl(ForgotView mForgotView,ForgotInteractorImpl forgotInteractor){
        this.mForgotView=mForgotView;
        this.mForgotInteractor=forgotInteractor;
    }
    @Override
    public void validateCred(String phone,String newpass) {
        mForgotInteractor.forgot(phone,newpass,this);
    }

    @Override
    public void onDestroy() {
        if(mForgotView != null){
            mForgotView=null;
        }
    }

    @Override
    public void setView(ForgotView mForgot) {
        this.mForgotView=mForgot;
    }

    @Override
    public void setModel(ForgotInteractorImpl mForgotInteractorImpl) {
        mForgotInteractor=mForgotInteractorImpl;
    }



    @Override
    public void onSuccess(String newpass) {
        if(mForgotView != null){
            mForgotView.sendPasswordMail(newpass);
            mForgotView.navToLogin();
        }
    }

    @Override
    public void onFailure(String message) {
        if(mForgotView != null){
            mForgotView.showSnack(message);
        }
    }
}
