package com.example.evoca.evocaforandroidlearning.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.evoca.evocaforandroidlearning.Model.ServerResponse;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.api.ApiManager;
import com.example.evoca.evocaforandroidlearning.dialogs.CustomDialog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit.Callback;
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
    private void findView(){
        signUpButton = (Button) findViewById(R.id.btn_sign_up);
        editTextFistName = (EditText) findViewById(R.id.et_sign_up_first_name);
        editTextLastName = (EditText) findViewById(R.id.et_sign_up_last_name);
        editTextSignUpEmail = (EditText) findViewById(R.id.et_sign_up_email);
        editTextSignUpPass = (EditText) findViewById(R.id.et_sign_up_password);
        editTextSignUpConfirmPass = (EditText) findViewById(R.id.et_sign_up_confirm_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void listener(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                String firstName = editTextFistName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String email = editTextSignUpEmail.getText().toString();
                String password = editTextSignUpPass.getText().toString();

                if (validation() && emailValidation() && confirmValidation()) {

                    ApiManager.getInstance().getClient().postRegistrationData(email, password, firstName, lastName, new Callback<ServerResponse>() {
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
    private boolean validation() {
        if (editTextFistName.getText().toString().length() == 0) {
            setErrorOnField(editTextFistName, R.string.is_empty);
            return false;
        } else if (editTextLastName.getText().toString().length() == 0) {
            setErrorOnField(editTextLastName, R.string.is_empty);
            return false;
        } else if (editTextSignUpEmail.getText().toString().length() == 0) {
            setErrorOnField(editTextSignUpEmail, R.string.is_empty);
            return false;
        } else if (editTextSignUpPass.getText().toString().length() == 0) {
            setErrorOnField(editTextSignUpPass, R.string.is_empty);
            return false;
        } if (editTextSignUpConfirmPass.getText().toString().length() == 0) {
            setErrorOnField(editTextSignUpConfirmPass, R.string.is_empty);
            return false;
        }

        return true;
    }

    private boolean emailValidation() {
        String regex = "^(.+)@(.+)\\.(.+)$";
        String email = editTextSignUpEmail.getText().toString();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            setErrorOnField(editTextSignUpEmail, R.string.incorrect_email);
            return false;
        }
        return true;
    }

    private boolean confirmValidation(){
        String pass = editTextSignUpPass.getText().toString();
        String passConfirm = editTextSignUpConfirmPass.getText().toString();
        if (!pass.equals(passConfirm)){
            setErrorOnField(editTextSignUpConfirmPass, R.string.confiramtion_err);
            return false;
        }
        return true;
    }

    private void setErrorOnField(EditText editText, int errorMessage) {
        progressBar.setVisibility(View.GONE);
        editText.setError(getResources().getString(errorMessage));
        editText.requestFocus();
    }
}
