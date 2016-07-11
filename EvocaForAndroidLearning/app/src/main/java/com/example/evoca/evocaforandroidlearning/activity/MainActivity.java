package com.example.evoca.evocaforandroidlearning.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.FacebookSdk;

import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.fragments.ScreenFragment;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

//        ImageView img = (ImageView)findViewById(R.id.animationView);
//        img.setImageResource(R.drawable.logo);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.common_animation);
//        animation.setDuration(7000);
//
//        img.startAnimation(animation);


        getSupportFragmentManager().beginTransaction().replace(R.id.container, ScreenFragment.newInstance()).commit();

        int Delay = 5000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        }, Delay);
    }

}
