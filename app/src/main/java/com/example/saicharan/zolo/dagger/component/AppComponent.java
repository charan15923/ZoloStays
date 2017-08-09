package com.example.saicharan.zolo.dagger.component;

import com.example.saicharan.zolo.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by NaNi on 10/08/17.
 */

@Singleton @Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MyApp myApp);
   // void inject(DashboardInteractorImpl dashboardInteractorImpl);
}
