package com.example.saicharan.zolo.forgot.dagger;

import com.example.saicharan.zolo.forgot.ForgotActivity;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by NaNi on 11/08/17.
 */
@ForgotScope
@Subcomponent(modules = ForgotModule.class)
public interface ForgotComponent {
    void inject(ForgotActivity forgotActivity);
}
