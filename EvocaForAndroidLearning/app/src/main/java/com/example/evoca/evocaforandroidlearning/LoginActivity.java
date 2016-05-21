package com.example.evoca.evocaforandroidlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.btn_login);
        Button signUpButton = (Button) findViewById(R.id.btn_login_sign_up);
        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login: {
                Intent intent = new Intent(LoginActivity.this,ListActivity.class);
                startActivity(intent);
                break;
            } case R.id.btn_login_sign_up: {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
