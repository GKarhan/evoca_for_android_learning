package com.example.evoca.evocaforandroidlearning.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.R;


public class ExerciseFragment extends Fragment {

    public ExerciseFragment() {
        // Required empty public constructor
    }


    public static ExerciseFragment newInstance() {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);

        return rootView;
    }
}
