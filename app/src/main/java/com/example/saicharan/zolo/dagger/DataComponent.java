package com.example.saicharan.zolo.dagger;

import android.app.Application;

import com.example.saicharan.zolo.dashboard.dagger.DashboardActivityComponent;
import com.example.saicharan.zolo.dashboard.dagger.DashboardActivityModule;
import com.example.saicharan.zolo.forgot.dagger.ForgotComponent;
import com.example.saicharan.zolo.forgot.dagger.ForgotModule;
import com.example.saicharan.zolo.login.dagger.LoginComponent;
import com.example.saicharan.zolo.login.dagger.LoginModule;
import com.example.saicharan.zolo.register.dagger.RegisterComponent;
import com.example.saicharan.zolo.register.dagger.RegisterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by NaNi on 10/08/17.
 */
@Singleton
@Component(modules = {AppModule.class,DataModule.class})
public interface DataComponent {
        DashboardActivityComponent plus(DashboardActivityModule dashboardActivityModule);
        ForgotComponent plus(ForgotModule forgotModule);
        LoginComponent plus(LoginModule loginModule);
        RegisterComponent plus(RegisterModule registerModule);
        Application application();
}
