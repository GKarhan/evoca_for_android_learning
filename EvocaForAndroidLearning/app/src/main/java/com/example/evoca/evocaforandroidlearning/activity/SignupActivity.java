package com.example.evoca.evocaforandroidlearning.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.evoca.evocaforandroidlearning.Model.Api;
import com.example.evoca.evocaforandroidlearning.Model.ServerResponse;
import com.example.evoca.evocaforandroidlearning.R;

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

                validation();
                emailValidation();

                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint("http://api.evocalab.com/evoca/test")
                        .build();

                Api client = restAdapter.create(Api.class);

                client.postRegistrationData(email, password, firstName, lastName, new Callback<ServerResponse>() {
                    @Override
                    public void success(ServerResponse serverResponse, Response response) {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(SignUpActivity.this, ListActivity.class);
                            startActivity(intent);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }
        });
    }

    void validation() {

        if (editTextFistName.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextFistName.setError("Fill out the required field");
            editTextFistName.requestFocus();

        } else if (editTextLastName.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextLastName.setError("Fill out the required field");
            editTextLastName.requestFocus();

        }else if (editTextSignUpEmail.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpEmail.setError("Fill out the required field");
            editTextSignUpEmail.requestFocus();
        }
        else if (editTextSignUpPass.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpPass.setError("Fill out the required field");
            editTextSignUpPass.requestFocus();
        }
        else if (editTextSignUpConfirmPass.getText().toString().length() == 0) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpConfirmPass.setError("Fill out the required field");
            editTextSignUpConfirmPass.requestFocus();
        }
    }
    void emailValidation() {
        if (!editTextSignUpEmail.getText().toString().contains("@")) {
            progressBar.setVisibility(View.GONE);
            editTextSignUpEmail.setError("incorrect email");
            editTextSignUpEmail.requestFocus();
        }
    }
}
