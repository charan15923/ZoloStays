package com.example.saicharan.zolo;

import android.app.Application;
import android.content.Context;


import com.example.saicharan.zolo.dagger.AppModule;
import com.example.saicharan.zolo.dagger.DaggerDataComponent;
import com.example.saicharan.zolo.dagger.DataComponent;
import com.example.saicharan.zolo.dagger.DataModule;

import javax.inject.Inject;

/**
 * Created by NaNi on 08/08/17.
 */

public class MyApp extends Application {

    private DataComponent mDataComponent;
    private  static  Application instance;

    public static Context getContext() {
        return instance;
    }

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }


    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;
        mDataComponent= DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule()).build();
    }

    public DataComponent getDataComponent(){
        return mDataComponent;
    }
}
