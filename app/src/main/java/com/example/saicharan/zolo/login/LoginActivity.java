package com.example.saicharan.zolo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.example.saicharan.zolo.R;
import com.example.saicharan.zolo.StateMaintainer;
import com.example.saicharan.zolo.dashboard.DashboardActivity;
import com.example.saicharan.zolo.forgot.ForgotActivity;
import com.example.saicharan.zolo.register.RegisterActivity;

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
    private LoginPresenter mLoginPresenter;
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer( getFragmentManager(), LoginActivity.class.getName());
    String phnData,passData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupMVP();
    }

    private void setupMVP() {
        if (mStateMaintainer.firstTimeIn()) {
            LoginPresenterImpl presenter = new LoginPresenterImpl(this);
            LoginInteractorImpl model = new LoginInteractorImpl(presenter);
            presenter.setModel(model);
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);
            mLoginPresenter = presenter;

        }
        else {
            mLoginPresenter = mStateMaintainer.get(LoginPresenterImpl.class.getName());
            mLoginPresenter.setView(this);
        }
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
       if(msg){
           i++;
           if(i % 2 == 0)
               login.setEnabled(true);
           else
               login.setEnabled(false);
       }else{
           i--;
           if(i % 2 == 0)
               login.setEnabled(true);
           else
               login.setEnabled(false);
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }
}
