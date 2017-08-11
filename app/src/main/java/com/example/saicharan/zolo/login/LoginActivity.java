package com.example.saicharan.zolo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.example.saicharan.zolo.MyApp;
import com.example.saicharan.zolo.R;
import com.example.saicharan.zolo.dashboard.DashboardActivity;
import com.example.saicharan.zolo.forgot.ForgotActivity;
import com.example.saicharan.zolo.login.dagger.LoginModule;
import com.example.saicharan.zolo.register.RegisterActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


/**
 * Created by NaNi on 06/08/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.btn_login)Button login;
    int i=0;

    @Inject
    LoginPresenter mLoginPresenter;


    String phnData,passData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        MyApp.get(this)
                .getDataComponent()
                .plus(new LoginModule(this))
                .inject(this);
    }

    @OnClick({R.id.btn_login , R.id.btn_signup, R.id.link_forgot})
    void submitButton(View view) {
        if (view.getId() == R.id.btn_login) {
            mLoginPresenter.validateCred(phnData, passData);
        }
        if(view.getId() == R.id.btn_signup){
            Intent forgot = new Intent(this, RegisterActivity.class);
            startActivity(forgot);
        }
        if(view.getId() == R.id.link_forgot){
            Intent forgot = new Intent(this, ForgotActivity.class);
            startActivity(forgot);
        }
    }

    @OnTextChanged(value = R.id.input_phone,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPhoneInput(Editable editable) {
        phnData=editable.toString();
        mLoginPresenter.validatePass(editable.toString());
    }
    @OnTextChanged(value = R.id.input_password,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordInput(Editable editable) {
        passData=editable.toString();
        mLoginPresenter.validatePhn(editable.toString());
    }
    @Override
    public void showSnack(String errmsg) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar
                .make(parentLayout, errmsg , Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void navToDash() {
        Intent dash = new Intent(this, DashboardActivity.class);
        dash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dash);
        finish();
    }

    @Override
    public void enableButton(Boolean msg) {
     login.setEnabled(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }
}
