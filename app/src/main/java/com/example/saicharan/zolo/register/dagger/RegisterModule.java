package com.example.saicharan.zolo.register.dagger;

import com.example.saicharan.zolo.register.RegisterInteractor;
import com.example.saicharan.zolo.register.RegisterInteractorImpl;
import com.example.saicharan.zolo.register.RegisterPresenter;
import com.example.saicharan.zolo.register.RegisterPresenterImpl;
import com.example.saicharan.zolo.register.RegisterView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NaNi on 11/08/17.
 */
@Module
public class RegisterModule {

    public final RegisterView registerView;

    public RegisterModule(RegisterView registerView){
        this.registerView=registerView;
    }

    @Provides
    @RegisterScope
    RegisterView provideRegisterView(){
        return this.registerView;
    }

    @Provides
    @RegisterScope
    RegisterPresenter provideForgotPresenterImpl(RegisterPresenterImpl registerPresenter){
        return registerPresenter;
    }

    @Provides
    @RegisterScope
    RegisterInteractor provideRegisterInteractor(RegisterInteractorImpl registerInteractor){
        return registerInteractor;
    }
}
