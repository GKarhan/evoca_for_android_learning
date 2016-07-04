package com.example.evoca.evocaforandroidlearning.activity;

/**
 * Created by Evoca-PC on 6/28/2016.
 */
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
import com.example.evoca.evocaforandroidlearning.dialogs.CustomDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpButton;
    private EditText editTextFistName;
    private EditText editTextLastName;
    private EditText editTextSignUpEmail;
    private EditText editTextSignUpPass;
    private EditText editTextSignUpConfirmPass;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findView();
        listener();

    }
    void findView(){
        signUpButton = (Button) findViewById(R.id.btn_sign_up);
        editTextFistName = (EditText) findViewById(R.id.et_sign_up_first_name);
        editTextLastName = (EditText) findViewById(R.id.et_sign_up_last_name);
        editTextSignUpEmail = (EditText) findViewById(R.id.et_sign_up_email);
        editTextSignUpPass = (EditText) findViewById(R.id.et_sign_up_password);
        editTextSignUpConfirmPass = (EditText) findViewById(R.id.et_sign_up_confirm_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    void listener(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                String firstName = editTextFistName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String email = editTextSignUpEmail.getText().toString();
                String password = editTextSignUpPass.getText().toString();
                String confirmPassword = editTextSignUpConfirmPass.getText().toString();

//                validation();
//                emailValidation();

                if (validation() && emailValidation() && confirmValidation()) {
                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setLogLevel(RestAdapter.LogLevel.FULL)
                            .setEndpoint("http://api.evocalab.com/evoca/test")
                            .build();

                    Api client = restAdapter.create(Api.class);

                    client.postRegistrationData(email, password, firstName, lastName, new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            progressBar.setVisibility(View.GONE);
                            if (serverResponse.getStatus()) {
                                CustomDialog custom = new CustomDialog();
                                custom.show(getSupportFragmentManager(), null);
                            } else {
                                editTextSignUpEmail.setError(getResources().getString(R.string.already_exists));
                                editTextSignUpEmail.requestFocus();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            progressBar.setVisibility(View.GONE);

                        }
                    });
                }

            }
        });
    }
    boolean validation() {

        if (editTextFistName.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextFistName.setError(getResources().getString(R.string.is_empty));
            editTextFistName.requestFocus();
            return false;

        } else if (editTextLastName.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextLastName.setError(getResources().getString(R.string.is_empty));
            editTextLastName.requestFocus();
            return false;

        }else if (editTextSignUpEmail.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpEmail.setError(getResources().getString(R.string.is_empty));
            editTextSignUpEmail.requestFocus();
            return false;
        }
        else if (editTextSignUpPass.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpPass.setError(getResources().getString(R.string.is_empty));
            editTextSignUpPass.requestFocus();
            return false;
        }
        else if (editTextSignUpConfirmPass.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpConfirmPass.setError(getResources().getString(R.string.is_empty));
            editTextSignUpConfirmPass.requestFocus();
            return false;
        }
//        else if()   {
//            progressBar.setVisibility(View.GONE);
//            editTextSignUpConfirmPass.setError("confirmation failed");
//            editTextSignUpConfirmPass.requestFocus();
//            return false;
//        }
        return true;
    }

    boolean emailValidation() {
        String regex = "^(.+)@(.+)\\.(.+)$";
        String email = editTextSignUpEmail.getText().toString();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpEmail.setError(getResources().getString(R.string.incorrect_email));
            editTextSignUpEmail.requestFocus();
            return false;
        }
        return true;
    }

    boolean confirmValidation(){
        String pass = editTextSignUpPass.getText().toString();
        String passConfirm = editTextSignUpConfirmPass.getText().toString();
        if (!pass.equals(passConfirm)){
            progressBar.setVisibility(View.GONE);
            editTextSignUpConfirmPass.setError(getResources().getString(R.string.confiramtion_err));
            editTextSignUpConfirmPass.requestFocus();
            return false;
        }
        return true;
    }



}
