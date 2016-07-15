package com.example.evoca.evocaforandroidlearning.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evoca.evocaforandroidlearning.R;


public class RadioFragment extends Fragment {
    private MenuItem buttonBack;
    TextView textViewQuestion;
    TextView textViewAnswer1;
    TextView textViewAnswer2;
    TextView textViewAnswer3;
    TextView textViewAnswer4;
    public RadioFragment() {
        // Required empty public constructor
    }

//
    public static RadioFragment newInstance() {
        RadioFragment fragment = new RadioFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_radio, container, false);
        textViewQuestion = (TextView) rootView.findViewById(R.id.tv_radio_question);
        textViewAnswer1 = (TextView) rootView.findViewById(R.id.tv_radio_answer1);
        textViewAnswer2 = (TextView) rootView.findViewById(R.id.tv_radio_answer2);
        textViewAnswer3 = (TextView) rootView.findViewById(R.id.tv_radio_answer3);
        textViewAnswer4 = (TextView) rootView.findViewById(R.id.tv_radio_answer4);

        String question = getArguments().getString("question");
        String answer1 = getArguments().getString("answer1");
        String answer2 = getArguments().getString("answer2");
        String answer3 = getArguments().getString("answer3");
        String answer4 = getArguments().getString("answer4");

        textViewQuestion.setText(question);
        textViewAnswer1.setText(answer1);
        textViewAnswer2.setText(answer2);
        textViewAnswer3.setText(answer3);
        textViewAnswer4.setText(answer4);
        System.out.println("***"+question);
        return rootView;
    }

}
