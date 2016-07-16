package com.example.evoca.evocaforandroidlearning.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.Model.Exercise;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;


public class EditTextFragment extends Fragment implements View.OnClickListener {

    TextView textViewQuestion;
    //TextView textViewAnswer;
    //TextView textViewEditAnswer;
    EditText etAnswer;
    private Button checkButton;
    private boolean correctAnswer = false;
    private static Exercise exercise;

    public EditTextFragment() {
        // Required empty public constructor
    }


    public static EditTextFragment newInstance() {
        EditTextFragment fragment = new EditTextFragment();
        //Bundle args = new Bundle();
        exercise = ListActivity.lesson.getExam_questions().get(ListActivity.exerciseIndex);
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
        etAnswer = (EditText) rootView.findViewById(R.id.et_edit_answer);
        checkButton = (Button) rootView.findViewById(R.id.btn_chack);

        /*String question = getArguments().getString("question");
        String answer = getArguments().getString("answer1");*/

        textViewQuestion.setText(exercise.getQuestion());

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correctAnswer) {
                    Toast.makeText(getContext(), "Հալալ ա Քեզ", Toast.LENGTH_SHORT).show();
                    getNextExercise();
                } else {
                    Toast.makeText(getContext(), "Սխալ պատասխան, փորձիր կրկին պատասխանել:", Toast.LENGTH_SHORT).show();
                    getNextExercise();
                }
            }
        });
        return rootView;
    }

    private void getNextExercise(){
        ListActivity.exerciseIndex++;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(ListActivity.exerciseIndex < ListActivity.lesson.getExam_questions().size()) {
            Child lesson = ListActivity.lesson;


            switch (lesson.getExam_questions().get(ListActivity.exerciseIndex).getType()) {
                case "radio": {
                    RadioFragment radioFragment = RadioFragment.newInstance();
                    transaction.replace(R.id.list_frame, radioFragment).commit();
                    break;
                }
                case "checkbox": {
                    CheckBoxFragment checkBoxFragment = CheckBoxFragment.newInstance();
                    transaction.replace(R.id.list_frame,checkBoxFragment).commit();
                    break;
                }
                case "edittext": {
                    EditTextFragment editTextFragment = EditTextFragment.newInstance();
                    transaction.replace(R.id.list_frame,editTextFragment).commit();
                    break;
                }
            }
        } else {
            //((ListActivity)getActivity()).onBackPressed();
            transaction.replace(R.id.list_frame, LessonFragment.newInstance(ListActivity.lesson)).commit();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.et_edit_answer:
            System.out.println("jjj++++++++++++++++++++++++++++");
            break;
        }

    }
}
