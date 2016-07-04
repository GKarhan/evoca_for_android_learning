package com.example.evoca.evocaforandroidlearning.validation;

import android.view.View;
import android.widget.EditText;

import com.example.evoca.evocaforandroidlearning.activity.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Evoca-PC on 6/28/2016.
 */
public class LoginValidation extends LoginActivity {

 public  static    boolean valis(EditText email){
        String regex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText().toString());
        if (!matcher.matches()) {
            email.setVisibility(View.GONE);
            email.setError("incorrect email");
            email.requestFocus();
            return false;
        }
        return true;
    }
    }

