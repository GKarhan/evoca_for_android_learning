package com.example.evoca.evocaforandroidlearning.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evoca.evocaforandroidlearning.R;


public class EditTextFragment extends Fragment {

    TextView textViewQuestion;
    TextView textViewAnswer;
    TextView textViewEditAnswer;

    public EditTextFragment() {
        // Required empty public constructor
    }


    public static EditTextFragment newInstance() {
        EditTextFragment fragment = new EditTextFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_edit_text, container, false);
        textViewQuestion = (TextView) rootView.findViewById(R.id.tv_edit_question);
        String question = getArguments().getString("question");
        String answer = getArguments().getString("answer1");
        textViewQuestion.setText(question);
        return rootView;
    }


}
