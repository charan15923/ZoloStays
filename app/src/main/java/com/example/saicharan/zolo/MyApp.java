package com.example.saicharan.zolo;

import android.app.Application;
import android.content.Context;

/**
 * Created by NaNi on 08/08/17.
 */

public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
