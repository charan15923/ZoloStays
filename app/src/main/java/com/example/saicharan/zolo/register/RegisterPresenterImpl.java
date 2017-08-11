package com.example.saicharan.zolo.register;

import javax.inject.Inject;

/**
 * Created by NaNi on 07/08/17.
 */

public class RegisterPresenterImpl implements RegisterPresenter,RegisterInteractor.onRegisterFinishedListener {
    RegisterView mRegisterView;
    RegisterInteractor mRegisterInteractor;
    @Inject
    public RegisterPresenterImpl(RegisterView mRegisterView,RegisterInteractorImpl registerInteractor){
        this.mRegisterView=mRegisterView;
        this.mRegisterInteractor=registerInteractor;
    }

    @Override
    public void validateCred(String phone, String email, String name, String password) {
        mRegisterInteractor.register(phone,email,name,password,this);
    }

    @Override
    public void onDestroy() {
        if(mRegisterView != null){
            mRegisterView=null;
        }
    }

    @Override
    public void setView(RegisterView mRegister) {
            this.mRegisterView=mRegister;
    }

    @Override
    public void setModel(RegisterInteractorImpl mRegisterInteractorImpl) {
        mRegisterInteractor=mRegisterInteractorImpl;
    }


    @Override
    public void onSuccess() {
        mRegisterView.navToDash();
    }

    @Override
    public void onFailure(String message) {
        mRegisterView.showSnack(message);
    }
}
