package com.sumit.dehaat.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sumit.dehaat.R;
import com.sumit.dehaat.Utils;
import com.sumit.dehaat.model.LoginResponse;
import com.sumit.dehaat.viewmodel.LoginViewModel;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPasword;
    private Button btnLogin;
    private View parent;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        initViews();
        setProperties();
    }

    /**
     * This method find and init the requried views
     */
    public void initViews() {
        etEmail = findViewById(R.id.et_email);
        etPasword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        parent = findViewById(R.id.parent);
    }

    /**
     * this method set properties like click listener and added another properties
     */
    public void setProperties() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_login) {
            login();
        }
    }

    /**
     * this method check is email and password is not empty and valid format
     * @return
     */
    public String checkError() {
        if (etEmail.getText().toString().isEmpty()) {
            return getString(R.string.txt_error_email_empty);
        } else if (etPasword.getText().toString().isEmpty()) {
            return getString(R.string.txt_error_password_empty);
        } else if (!Utils.isEmailValid(etEmail.getText().toString())) {
            return getString(R.string.txt_error_wrong_email);
        }

        return null;
    }

    /**
     * This method check first validation,error and after login flow continue
     */
    public void login() {
        String error = checkError();
        if (error != null) {
            Utils.displayErrorMessage(parent, error);
        } else {

            if (!Utils.isNetworkAvailable(this)) {
                Utils.displayErrorMessage(parent, getString(R.string.txt_no_internet_connection));
                return;
            }


            Utils.showProgressDialog(this);
            loginViewModel.login(etEmail.getText().toString(), etPasword.getText().toString()).observe(this, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {
                    Utils.dismissDialog();
                    if (loginResponse != null) {
                        launchMainActivity(loginResponse.getToken());
                    }

                }
            });
        }

    }

    /**
     * This method launch home activity
     * @param token auth token, we can save it for future calls
     */
    public void launchMainActivity(String token) {
        Utils.updateLoginStatus(this,true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
