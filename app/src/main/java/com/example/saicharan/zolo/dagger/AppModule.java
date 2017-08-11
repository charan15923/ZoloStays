package com.example.saicharan.zolo.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NaNi on 10/08/17.
 */
@Module
public class AppModule {
    Application mApplication;
    public AppModule(Application mApplication){
        this.mApplication=mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }
}
