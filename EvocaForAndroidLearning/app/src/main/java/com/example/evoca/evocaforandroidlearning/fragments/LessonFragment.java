package com.example.evoca.evocaforandroidlearning.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.evoca.evocaforandroidlearning.R;


public class LessonFragment extends Fragment {

    private Button exerciseButton;

    public LessonFragment() {
        // Required empty public constructor
    }


    public static LessonFragment newInstance() {
        LessonFragment fragment = new LessonFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_lesson, container, false);

        exerciseButton = (Button) rootView.findViewById(R.id.btn_exercise);

        exerciseButton.setOnClickListener(new View.OnClickListener() {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.listframe,ExerciseFragment.newInstance()).commit();
            }
        });


        return rootView;
    }

}
