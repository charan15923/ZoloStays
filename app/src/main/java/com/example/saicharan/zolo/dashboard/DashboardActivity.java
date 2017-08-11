package com.example.saicharan.zolo.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saicharan.zolo.MyApp;
import com.example.saicharan.zolo.R;
import com.example.saicharan.zolo.dashboard.dagger.DashboardActivityModule;
import com.example.saicharan.zolo.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class DashboardActivity extends Activity implements DashboardView{

    @Inject
    DashboardPresenter mDashboardPresenter;
    int id;
        String email,phn,name;
        String enableEmail,enablePhn,enableName;
    @BindView(R.id.input_email_dash)EditText emailUp;
    @BindView(R.id.input_mobile_dash)EditText phnUp;
    @BindView(R.id.input_name_dash)EditText nameUp;
    @BindView(R.id.btn_update)Button btn_upd;
    @BindView(R.id.welcome)TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        MyApp.get(this)
                .getDataComponent()
                .plus(new DashboardActivityModule(this))
                .inject(this);
        setUp();
    }

    private void setUp() {
        if(mDashboardPresenter.loggedIn())
            mDashboardPresenter.getData();
        else{
            mDashboardPresenter.logOut();
        }
    }
    @OnTextChanged(value = R.id.input_mobile_dash,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPhoneInput(Editable editable) {
        phn=editable.toString();
        enableButton();
    }
    @OnTextChanged(value = R.id.input_email_dash,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterEmailInput(Editable editable) {
        email=editable.toString();
        enableButton();
    }
    @OnTextChanged(value = R.id.input_name_dash,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterNameInput(Editable editable) {
        name=editable.toString();
        enableButton();
    }

    public void enableButton(){
        if(!(email.equals(enableEmail) && name.equals(enableName) && phn.equals(enablePhn))){
            btn_upd.setEnabled(true);
        }

    }
    @OnClick({R.id.btn_update, R.id.link_dash_logout})
    void submitButton(View view) {
        if(view.getId()== R.id.btn_update){
            mDashboardPresenter.updateDet(id,phn,email,name);
        }
        if(view.getId()== R.id.link_dash_logout){
            mDashboardPresenter.logOut();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void showSnack(String errmsg) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar
                .make(parentLayout, errmsg , Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void navToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(i);
    }

    @Override
    public void initViews(int idn,String phone, String emai, String nam) {
        id=idn;
        email=emai;
        phn=phone;
        name=nam;
        tv.setText("Welcome!"+name);
        emailUp.setText(email,TextView.BufferType.EDITABLE);
        nameUp.setText(name,TextView.BufferType.EDITABLE);
        phnUp.setText(phn,TextView.BufferType.EDITABLE);
    }
}
