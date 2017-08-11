package com.example.saicharan.zolo.dashboard.dagger;

import com.example.saicharan.zolo.dashboard.DashboardInteractor;
import com.example.saicharan.zolo.dashboard.DashboardInteractorImpl;
import com.example.saicharan.zolo.dashboard.DashboardPresenter;
import com.example.saicharan.zolo.dashboard.DashboardPresenterImpl;
import com.example.saicharan.zolo.dashboard.DashboardView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NaNi on 10/08/17.
 */
@Module
public class DashboardActivityModule {
    public final DashboardView dashboardView;
    public DashboardActivityModule(DashboardView dashboardView){
        this.dashboardView=dashboardView;
    }

    @Provides
    @DashboardActivityScope
    DashboardView provideDashboardView(){
        return this.dashboardView;
    }

    @Provides
    @DashboardActivityScope
    DashboardPresenter provideDashboardPresenterImpl(DashboardPresenterImpl dashboardPresenter){
        return dashboardPresenter;
    }

    @Provides
    @DashboardActivityScope
    DashboardInteractor provideDashboardInteractor(DashboardInteractorImpl dashboardInteractor){
        return dashboardInteractor;
    }
}
