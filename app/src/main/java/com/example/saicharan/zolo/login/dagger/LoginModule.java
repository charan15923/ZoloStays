package com.example.saicharan.zolo.login.dagger;

import com.example.saicharan.zolo.login.LoginInteractor;
import com.example.saicharan.zolo.login.LoginInteractorImpl;
import com.example.saicharan.zolo.login.LoginPresenter;
import com.example.saicharan.zolo.login.LoginPresenterImpl;
import com.example.saicharan.zolo.login.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NaNi on 11/08/17.
 */
@Module
public class LoginModule {
    public final LoginView loginView;

    public LoginModule(LoginView loginView){
        this.loginView=loginView;
    }

    @Provides
    @LoginScope
    LoginView provideLoginView(){
        return this.loginView;
    }

    @Provides
    @LoginScope
    LoginPresenter provideLoginPresenterImpl(LoginPresenterImpl loginPresenter){
        return loginPresenter;
    }

    @Provides
    @LoginScope
    LoginInteractor provideLoginInteractor(LoginInteractorImpl loginInteractor){
        return loginInteractor;
    }
}
