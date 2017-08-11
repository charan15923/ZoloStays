package com.example.saicharan.zolo.dagger;

import android.app.Application;

import com.example.saicharan.zolo.DatabaseHelper;
import com.example.saicharan.zolo.SessionManagement;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NaNi on 10/08/17.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    SessionManagement providesSession(Application application){
        return new SessionManagement(application);
    }

    @Provides
    @Singleton
    DatabaseHelper provideData(Application application){
        return new DatabaseHelper(application);
    }
}
