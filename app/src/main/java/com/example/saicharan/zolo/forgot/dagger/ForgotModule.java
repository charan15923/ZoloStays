package com.example.saicharan.zolo.forgot.dagger;

import com.example.saicharan.zolo.forgot.ForgotInteractor;
import com.example.saicharan.zolo.forgot.ForgotInteractorImpl;
import com.example.saicharan.zolo.forgot.ForgotPresenter;
import com.example.saicharan.zolo.forgot.ForgotPresenterImpl;
import com.example.saicharan.zolo.forgot.ForgotView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NaNi on 11/08/17.
 */
@Module
public class ForgotModule {
    public final ForgotView forgotView;

    public ForgotModule(ForgotView forgotView){
        this.forgotView=forgotView;
    }

    @Provides
    @ForgotScope
    ForgotView provideForgotView(){
        return this.forgotView;
    }

    @Provides
    @ForgotScope
    ForgotPresenter provideForgotPresenterImpl(ForgotPresenterImpl forgotPresenter){
        return forgotPresenter;
    }

    @Provides
    @ForgotScope
    ForgotInteractor provideForgotInteractor(ForgotInteractorImpl forgotInteractor){
        return forgotInteractor;
    }

}
