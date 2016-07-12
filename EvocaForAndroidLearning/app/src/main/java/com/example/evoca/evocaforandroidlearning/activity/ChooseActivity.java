package com.example.evoca.evocaforandroidlearning.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.util.PrefUtil;

public class ChooseActivity extends AppCompatActivity {

    private Button buttonChooseLessons;
    private Button buttonChooseExercise;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        findViews();
        listener();
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!= null &&networkInfo.isConnected()){

        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChooseActivity.this);
            builder.setTitle(getResources().getString(R.string.dialog_connection_title))
                    .setMessage(getResources().getString(R.string.dialog_connection_message))
                    .setIcon(R.drawable.connection_lost)
                    .setCancelable(false)
                    .setNegativeButton("Оk", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
    void findViews(){
        buttonChooseLessons = (Button) findViewById(R.id.btn_choose_lessons);
        buttonChooseExercise = (Button) findViewById(R.id.btn_choose_exercise);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
    void listener(){
        buttonChooseLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        buttonChooseExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PrefUtil.isAuthanticated){
                    Intent intent = new Intent(ChooseActivity.this, ListActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ChooseActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
