package com.example.evoca.evocaforandroidlearning.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;
import com.example.evoca.evocaforandroidlearning.activity.LoginActivity;
import com.example.evoca.evocaforandroidlearning.fragments.LessonFragment;

/**
 * Created by Vacho on 21.07.2016.
 */
public class Custom extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.queen);
        mp.start();
        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext());
        builder.setTitle("   ").setMessage("Դուք հաջողությամբ ավարտեցիք ․․․․․․․․․․․․․․")
                .setIcon(R.drawable.congratulations)
                .setCancelable(false)
                .setNegativeButton("Շարունակել", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mp.pause();
                        LessonFragment lessonFragment = LessonFragment.newInstance(ListActivity.nextLesson);
                        getFragmentManager().beginTransaction().replace(R.id.list_frame, lessonFragment).commit();
                    }
                });

        return builder.create();
    }

}
