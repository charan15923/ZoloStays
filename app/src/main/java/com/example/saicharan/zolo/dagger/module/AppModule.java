package com.example.saicharan.zolo.dagger.module;

import android.content.Context;

import com.example.saicharan.zolo.DatabaseHelper;
import com.example.saicharan.zolo.MyApp;
import com.example.saicharan.zolo.SessionManagement;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NaNi on 10/08/17.
 */
@Module
public class AppModule {
    private final MyApp myApp;
    public AppModule(MyApp myApp){this.myApp=myApp;}

    @Provides @Singleton
    Context providesApplicationContext(){
        return myApp;
    }
    @Provides @Singleton
    SessionManagement getSession(Context context){
        return  new SessionManagement(myApp);
    }
    @Provides @Singleton
    DatabaseHelper getDhelper(Context context){
        return new DatabaseHelper(myApp);
    }
}
