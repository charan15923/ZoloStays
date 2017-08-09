package com.example.saicharan.zolo;

import android.app.Application;
import android.content.Context;

import com.example.saicharan.zolo.dagger.component.AppComponent;
import com.example.saicharan.zolo.dagger.module.AppModule;
import com.example.saicharan.zolo.dashboard.DashboardInteractorImpl;

import javax.inject.Inject;

/**
 * Created by NaNi on 08/08/17.
 */

public class MyApp extends Application {

    protected AppComponent appComponent;
    private static MyApp instance;
    @Inject
    DashboardInteractorImpl dashboardInteractor;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
       // return (MyApp) context.getApplicationContext();
          return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
    public AppComponent getAppComponent(){
        return appComponent;
    }
}
