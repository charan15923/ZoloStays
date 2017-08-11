package com.example.saicharan.zolo.dashboard;

import javax.inject.Inject;

/**
 * Created by NaNi on 08/08/17.
 */

public class DashboardPresenterImpl implements DashboardPresenter,DashboardInteractor.onUpdateListener {
    DashboardView mDashboardView;
    DashboardInteractor mDashboardInteractor;
    @Inject
    public DashboardPresenterImpl(DashboardView mDashboardView,DashboardInteractorImpl dashboardInteractor){
        this.mDashboardView=mDashboardView;
        this.mDashboardInteractor=dashboardInteractor;
    }
    @Override
    public void onSuccess(String msg) {
        if(mDashboardView != null){
            mDashboardView.showSnack(msg);
        }
    }

    @Override
    public void onFailure(String message) {
        if(mDashboardView != null){
            mDashboardView.showSnack(message);
        }
    }

    @Override
    public void onGotData(int id, String phn, String email, String name) {
        mDashboardView.initViews(id,phn,email,name);
    }

    @Override
    public void updateDet(int id, String phone, String email, String name) {
        mDashboardInteractor.update(id,phone,email,name,this);
    }

    @Override
    public void onDestroy() {
        if(mDashboardView != null){
            mDashboardView=null;
        }
    }

    @Override
    public boolean loggedIn() {
        return mDashboardInteractor.logCheck(this);
    }

    @Override
    public void getData() {
        mDashboardInteractor.retrieveData(this);
    }

    @Override
    public void logOut() {
        mDashboardInteractor.logOut(this);
        mDashboardView.navToLogin();
    }

    @Override
    public void setView(DashboardView mDashboard) {
        this.mDashboardView=mDashboard;
    }

    @Override
    public void setModel(DashboardInteractorImpl mDashboardInteractorImpl) {
        mDashboardInteractor=mDashboardInteractorImpl;
    }

}
