package com.example.saicharan.zolo.dashboard;

/**
 * Created by NaNi on 08/08/17.
 */

public interface DashboardPresenter {

    void updateDet(int id, String phone, String email, String name);
    void onDestroy();
    boolean loggedIn();
    void getData();
    void logOut();
    void setView(DashboardView mDashboard);
    void setModel(DashboardInteractorImpl mDashboardInteractorImpl);
}
