package com.example.evoca.evocaforandroidlearning.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.Api;
import com.example.evoca.evocaforandroidlearning.Model.ServerResponse;
import com.example.evoca.evocaforandroidlearning.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity  {


    private Button loginButton;
    private Button signUpButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        listener();
    }

    void findView() {
        loginButton = (Button) findViewById(R.id.btn_login);
        signUpButton = (Button) findViewById(R.id.btn_login_sign_up);
        editTextEmail = (EditText) findViewById(R.id.et_login_email);
        editTextPassword = (EditText) findViewById(R.id.et_login_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    void listener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                  validation();
                  emailValidation();
                if (validation() && emailValidation() ) {

                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setLogLevel(RestAdapter.LogLevel.FULL)
                            .setEndpoint("http://api.evocalab.com/evoca/test")
                            .build();

                    Api client = restAdapter.create(Api.class);

                    client.postLoginData(email, password, new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            progressBar.setVisibility(View.GONE);
                            if (serverResponse.getStatus()) {
                                Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                                startActivity(intent);
                            } else {

                                    editTextEmail.setError(getResources().getString(R.string.incorrect_email));
                                    editTextEmail.requestFocus();
                            }
                        }
                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
    }

 boolean validation() {


            if (editTextPassword.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextPassword.setError(getResources().getString(R.string.password_is_empty));
            editTextPassword.requestFocus();
                return false;
        }
     return true;
    }

    boolean emailValidation() {
        String regex = "^(.+)@(.+)\\.(.+)$";
        String email = editTextEmail.getText().toString();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (editTextEmail.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextEmail.setError(getResources().getString(R.string.is_empty));
            editTextEmail.requestFocus();

        }else
        if (!matcher.matches()) {
            progressBar.setVisibility(View.GONE);
            editTextEmail.setError(getResources().getString(R.string.incorrect_email));
            editTextEmail.requestFocus();
            return false;
        }
        return true;
    }



}
