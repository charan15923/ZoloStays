package com.example.saicharan.zolo;

/**
 * Created by NaNi on 07/08/17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.saicharan.zolo.login.LoginActivity;

public class SessionManagement {

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "ZoloUSER";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_PHN = "phone";
    public static final String KEY_ID="rowid";

    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name,int id){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_PHN, name);
        editor.putInt(KEY_ID,id);
        editor.commit();
    }
    public void update(String phn){
        editor.putString(KEY_PHN,phn).apply();
    }
    public void checkLogin(){
        if(!this.isLoggedIn()){

            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public String[] getUserDetails(){
        String[] data = new String[2];
        data[0] = pref.getString(KEY_PHN, null);
        data[1] = String.valueOf(pref.getInt(KEY_ID,404));
        return data;
    }

    public void logoutUser(){

        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){

        return pref.getBoolean(IS_LOGIN, false);
    }
}