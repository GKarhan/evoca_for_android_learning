package com.example.evoca.evocaforandroidlearning.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.evoca.evocaforandroidlearning.R;

public class ChooseActivity extends AppCompatActivity {

    private Button buttonChooseLessons;
    private Button buttonChooseExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        findViews();
        listener();

    }
    void findViews(){
        buttonChooseLessons = (Button) findViewById(R.id.btn_choose_lessons);
        buttonChooseExercise = (Button) findViewById(R.id.btn_choose_exercise);
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
                Intent intent = new Intent(ChooseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
