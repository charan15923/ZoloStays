package com.example.saicharan.zolo.forgot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.saicharan.zolo.GMailSender;
import com.example.saicharan.zolo.MyApp;
import com.example.saicharan.zolo.R;
import com.example.saicharan.zolo.StateMaintainer;
import com.example.saicharan.zolo.forgot.dagger.ForgotModule;
import com.example.saicharan.zolo.login.LoginActivity;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by NaNi on 07/08/17.
 */

public class ForgotActivity extends AppCompatActivity implements ForgotView {
    String email;
    String newpass;
    @Inject
    ForgotPresenter mForgotPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        MyApp.get(this)
                .getDataComponent()
                .plus(new ForgotModule(this))
                .inject(this);

    }




    @OnClick({R.id.link_login_forgot, R.id.btn_forgot})
    void submitButton(View view) {
        if (view.getId() == R.id.btn_forgot) {
            String uuid = UUID.randomUUID().toString();
            uuid.replaceAll("-","");
            newpass=uuid.substring(0,9);
            mForgotPresenter.validateCred(email,newpass);
        }
        if(view.getId()== R.id.link_login_forgot){
            Intent forgot = new Intent(this, LoginActivity.class);
            startActivity(forgot);
        }
    }

    @OnTextChanged(value = R.id.input_email_forgot,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        void afterEmailInput(Editable editable) {
        email=editable.toString();

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
        Intent dash = new Intent(this, LoginActivity.class);
        dash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dash);
    }

    @Override
    public void sendPasswordMail(String newpass) {
        final String newPassword= newpass;
        new Thread(new Runnable() {

            public void run() {
                try {
                    GMailSender sender = new GMailSender("saicharan15923@gmail.com", "saipass145");
                    sender.sendMail("[ZOLO]NEW PASSWORD",
                            newPassword,
                            "charan15923@gmail.com",
                            email);
                    runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Toast.makeText(getApplicationContext(), "Password sent to your mail", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                    runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Toast.makeText(getApplicationContext(), "Something went wrong.Try again!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mForgotPresenter.onDestroy();
    }
}
