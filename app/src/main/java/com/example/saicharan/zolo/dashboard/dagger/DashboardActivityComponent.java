package com.example.saicharan.zolo.dashboard.dagger;

import com.example.saicharan.zolo.dashboard.DashboardActivity;

import dagger.Subcomponent;

/**
 * Created by NaNi on 10/08/17.
 */
@DashboardActivityScope
@Subcomponent(modules = DashboardActivityModule.class)
public interface DashboardActivityComponent {

    void inject(DashboardActivity dashboardActivity);
}
