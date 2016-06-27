package com.example.evoca.evocaforandroidlearning.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.R;

/**
 * Created by Vacho on 27.06.2016.
 */
public class CustomDialog extends DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext())
                .setTitle("Evoca")
                .setMessage("Duq Grancvel eq!!!!!!!!!")
                .setPositiveButton("OK", okButtonListerner)
                .setNegativeButton("cancel", null)
                .setIcon(R.drawable.thanks)
                .setNeutralButton("close", null);
        setCancelable(false);

        return builder.create();
    }

    private DialogInterface.OnClickListener okButtonListerner = new DialogInterface.OnClickListener() { //te positivw buttoni clicki jamanak incha linelu
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getContext(), "Okkkkkkkkkk", Toast.LENGTH_SHORT).show();
        }
    };
}
