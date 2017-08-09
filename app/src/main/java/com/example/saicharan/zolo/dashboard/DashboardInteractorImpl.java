package com.example.saicharan.zolo.dashboard;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.example.saicharan.zolo.DatabaseHelper;
import com.example.saicharan.zolo.SessionManagement;

/**
 * Created by NaNi on 08/08/17.
 */

public class DashboardInteractorImpl implements DashboardInteractor {

    private final DatabaseHelper dHelper;
    private final SessionManagement sManager;
    DashboardPresenterImpl mDashboardPresenter;

    public DashboardInteractorImpl(DashboardPresenterImpl mDashboardPresenter){
        this.mDashboardPresenter=mDashboardPresenter;
        dHelper = new DatabaseHelper(MyApp.getContext());
        sManager= new SessionManagement(MyApp.getContext());
    }
    @Override
    public void update(int id, String phone, String email, String name, onUpdateListener listener) {
        if (Patterns.PHONE.matcher(phone).matches() && phone.length() == 10 && dHelper.checkDashPhone(phone,id)) {
            if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && dHelper.checkDashEmail(email,id)) {
                if (name.length() >= 3) {
                            listener.onFailure(""+id+phone);
                            if(dHelper.updateUser(id,phone,email,name)){
                                sManager.update(phone);
                                listener.onGotData(id,phone,email,name);
                                listener.onFailure("SUCCESS");
                            }
                            else{
                                listener.onFailure("Failed,Try again");
                            }
                } else {
                        listener.onFailure("Name should be more than 3 letters");
                }
            }else{
                if(!dHelper.checkDashEmail(email,id))
                    listener.onFailure("Email already registered");
                else
                    listener.onFailure("Invalid email id");
            }
        }else{
            if(!dHelper.checkDashPhone(phone,id))
                listener.onFailure("Phone number already registered");
            else
                listener.onFailure("Invalid phone number");
        }
    }

    @Override
    public boolean logCheck(onUpdateListener listener) {
       return sManager.isLoggedIn();
    }

    @Override
    public void retrieveData(onUpdateListener listener) {
        int id;
        String phn;
       String[] get= sManager.getUserDetails();
        phn=get[0];
        id=Integer.parseInt(get[1]);
        Log.i("IDDBA",""+id);
        String[] getDB = dHelper.getValues(phn,id);
        listener.onGotData(id,phn,getDB[1],getDB[0]);
    }

    @Override
    public void logOut(onUpdateListener listener) {
        sManager.logoutUser();
    }
}
