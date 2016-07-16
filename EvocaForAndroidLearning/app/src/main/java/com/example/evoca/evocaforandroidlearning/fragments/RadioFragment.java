package com.example.evoca.evocaforandroidlearning.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.Model.Exercise;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;

import java.util.ArrayList;


public class RadioFragment extends Fragment implements View.OnClickListener {
    private MenuItem buttonBack;
    private TextView textViewQuestion;
    private RadioButton textViewRadioAnswer1;
    private RadioButton textViewRadioAnswer2;
    private RadioButton textViewRadioAnswer3;
    private RadioButton textViewRadioAnswer4;
    private String tarberak1;
    private boolean correctAnswer = false;
    private Button checkButton;
    private static Exercise exercise;
    //ArrayList<Exercise> list = new ArrayList<>();


    public RadioFragment() {
        // Required empty public constructor
    }

//
    public static RadioFragment newInstance() {
        RadioFragment fragment = new RadioFragment();
        Bundle args = new Bundle();
        System.out.println(ListActivity.exerciseIndex);
        System.out.println(ListActivity.lesson.getExam_questions());
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
        View rootView = inflater.inflate(R.layout.fragment_radio, container, false);
        textViewQuestion = (TextView) rootView.findViewById(R.id.tv_radio_question);
        textViewRadioAnswer1 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer1);
        textViewRadioAnswer2 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer2);
        textViewRadioAnswer3 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer3);
        textViewRadioAnswer4 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer4);
        checkButton = (Button) rootView.findViewById(R.id.btn_radio_check);

        /*String question = getArguments().getString("question");
        String answer1 = getArguments().getString("answer1");
        String answer2 = getArguments().getString("answer2");
        String answer3 = getArguments().getString("answer3");
        String answer4 = getArguments().getString("answer4");

        tarberak1 = getArguments().getString("tar1");

        textViewQuestion.setText(question);
        textViewRadioAnswer1.setText(answer1);
        textViewRadioAnswer2.setText(answer2);
        textViewRadioAnswer3.setText(answer3);
        textViewRadioAnswer4.setText(answer4);
        System.out.println("***" + question);*/

        textViewQuestion.setText(exercise.getQuestion());
        textViewRadioAnswer1.setText(exercise.getAns1());
        textViewRadioAnswer2.setText(exercise.getAns2());
        textViewRadioAnswer3.setText(exercise.getAns3());
        textViewRadioAnswer4.setText(exercise.getAns4());
        tarberak1 = exercise.getTa1();

        textViewRadioAnswer1.setOnClickListener(this);
        textViewRadioAnswer2.setOnClickListener(this);
        textViewRadioAnswer3.setOnClickListener(this);
        textViewRadioAnswer4.setOnClickListener(this);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correctAnswer) {
                    Toast.makeText(getContext(), "Հալալ ա Քեզ", Toast.LENGTH_SHORT).show();
                    getNextExercise();
                }
                else {
                    Toast.makeText(getContext(), "Սխալ պատասխան, փորձիր կրկին պատասխանել:", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.tv_radio_answer1:
                //System.out.println(list.size());
                if (checked && textViewRadioAnswer1.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
            case R.id.tv_radio_answer2:
                if (checked && textViewRadioAnswer2.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
            case R.id.tv_radio_answer3:
                if (checked && textViewRadioAnswer3.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
            case R.id.tv_radio_answer4:
                if (checked && textViewRadioAnswer4.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
        }
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

}
