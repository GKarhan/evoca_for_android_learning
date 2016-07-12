package com.example.evoca.evocaforandroidlearning.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.util.PrefUtil;


public class LessonFragment extends Fragment {

    private Button exerciseButton;
    TextView textViewText;
    TextView textViewTitle;
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

        exerciseButton = (Button) rootView.findViewById(R.id.btn_chack);
        textViewText = (TextView) rootView.findViewById(R.id.aaaaa);
        textViewTitle = (TextView) rootView.findViewById(R.id.tv_lesson);
        String getArgumentTitle = getArguments().getString("title");
        textViewTitle.setTypeface(null, Typeface.BOLD_ITALIC);
        textViewTitle.setText(getArgumentTitle);

        String getArgument = getArguments().getString("data");
        textViewText.setText(getArgument);

        if(!PrefUtil.isAuthanticated) {
            exerciseButton.setVisibility(View.GONE);
        }
        
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            @Override
            public void onClick(View v) {
                transaction.replace(R.id.list_frame, ExerciseFragment.newInstance()).commit();


            }
        });


        return rootView;
    }

}
