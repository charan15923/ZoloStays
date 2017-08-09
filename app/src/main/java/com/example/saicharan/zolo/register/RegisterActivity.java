package com.example.saicharan.zolo.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.example.saicharan.zolo.R;
import com.example.saicharan.zolo.StateMaintainer;
import com.example.saicharan.zolo.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by NaNi on 07/08/17.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView{
    String email,phone,pass,name;
    private RegisterPresenter mRegisterPresenter;
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer( getFragmentManager(), RegisterActivity.class.getName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setupMVP();
    }

    private void setupMVP() {
        if (mStateMaintainer.firstTimeIn()) {
            RegisterPresenterImpl presenter = new RegisterPresenterImpl(this);
            RegisterInteractorImpl model = new RegisterInteractorImpl(presenter);
            presenter.setModel(model);
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);
            mRegisterPresenter = presenter;

        }
        else {
            mRegisterPresenter = mStateMaintainer.get(RegisterPresenterImpl.class.getName());
            mRegisterPresenter.setView(this);
        }
    }

    @OnClick(R.id.btn_register)
    void submitButton(View view) {
        if (view.getId() == R.id.btn_register) {
            Log.i("KK",phone+name+email+pass);
            mRegisterPresenter.validateCred(phone,name,email,pass);
        }
    }

    @OnTextChanged(value = R.id.input_email_reg,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterEmailInput(Editable editable) {
        email=editable.toString();

    }
    @OnTextChanged(value = R.id.input_password_reg,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPassInput(Editable editable) {
        pass=editable.toString();

    }
    @OnTextChanged(value = R.id.input_name_reg,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterNameInput(Editable editable) {
        name=editable.toString();

    }
    @OnTextChanged(value = R.id.input_mobile_reg,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPhnInput(Editable editable) {
        phone = editable.toString();
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
        Intent dash = new Intent(this, LoginActivity.class);
        dash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dash);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegisterPresenter.onDestroy();
    }
}
