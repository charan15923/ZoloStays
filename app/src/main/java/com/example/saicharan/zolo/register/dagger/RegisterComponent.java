package com.example.saicharan.zolo.register.dagger;

import com.example.saicharan.zolo.register.RegisterActivity;

import dagger.Subcomponent;

/**
 * Created by NaNi on 11/08/17.
 */
@RegisterScope
@Subcomponent(modules = RegisterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity registerActivity);
}
