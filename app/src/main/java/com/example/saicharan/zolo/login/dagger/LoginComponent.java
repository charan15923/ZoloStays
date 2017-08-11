package com.example.saicharan.zolo.login.dagger;

import com.example.saicharan.zolo.login.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by NaNi on 11/08/17.
 */
@LoginScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
