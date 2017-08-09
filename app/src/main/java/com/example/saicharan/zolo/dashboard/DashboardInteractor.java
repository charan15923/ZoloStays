package com.example.saicharan.zolo.dashboard;

/**
 * Created by NaNi on 08/08/17.
 */

public interface DashboardInteractor {
    interface onUpdateListener{
        void onSuccess(String message);
        void onFailure(String message);
        void onGotData(int id, String phn, String email, String name);
    }
    void update(int id, String phone, String email, String name, onUpdateListener listener);
    boolean logCheck(onUpdateListener listener);
    void retrieveData(onUpdateListener listener);
    void logOut(onUpdateListener listener);

}
