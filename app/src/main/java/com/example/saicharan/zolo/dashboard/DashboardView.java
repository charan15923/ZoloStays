package com.example.saicharan.zolo.dashboard;

/**
 * Created by NaNi on 08/08/17.
 */

public interface DashboardView {
    void showSnack(String errmsg);
    void navToLogin();
    void initViews(int id, String phn, String email, String name);
}
