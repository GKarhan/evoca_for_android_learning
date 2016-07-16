package com.example.evoca.evocaforandroidlearning.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.Model.Exercise;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;


public class CheckBoxFragment extends Fragment implements View.OnClickListener {

    private TextView textViewQuestion;
    private CheckBox checkBoxAnswer1;
    private CheckBox checkBoxAnswer2;
    private CheckBox checkBoxAnswer3;
    private CheckBox checkBoxAnswer4;
    private Button buttonCheck;
    private static Exercise exercise;



    public CheckBoxFragment() {
        // Required empty public constructor
    }


    public static CheckBoxFragment newInstance() {
        CheckBoxFragment fragment = new CheckBoxFragment();
        Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_check_box, container, false);
        textViewQuestion = (TextView) rootView.findViewById(R.id.tv_check_box_question);
        checkBoxAnswer1 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer1);
        checkBoxAnswer2 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer2);
        checkBoxAnswer3 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer3);
        checkBoxAnswer4 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer4);
        buttonCheck = (Button) rootView.findViewById(R.id.btn_chack);

        /*String question = getArguments().getString("question");
        final String answer1 = getArguments().getString("answer1");
        String answer2 = getArguments().getString("answer2");
        String answer3 = getArguments().getString("answer3");
        String answer4 = getArguments().getString("answer4");

        textViewQuestion.setText(question);
        checkBoxAnswer1.setText(answer1);
        checkBoxAnswer2.setText(answer2);
        checkBoxAnswer3.setText(answer3);
        checkBoxAnswer4.setText(answer4);*/

        textViewQuestion.setText(exercise.getQuestion());
        checkBoxAnswer1.setText(exercise.getAns1());
        checkBoxAnswer2.setText(exercise.getAns2());
        checkBoxAnswer3.setText(exercise.getAns3());
        checkBoxAnswer4.setText(exercise.getAns4());

        checkBoxAnswer1.setOnClickListener(this);
        checkBoxAnswer2.setOnClickListener(this);
        checkBoxAnswer3.setOnClickListener(this);
        checkBoxAnswer4.setOnClickListener(this);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getNextExercise();
            }
        });
        return rootView;
    }


    @Override
    public void onClick(View v) {
        boolean checked = ((CheckBox) v).isChecked();

        /*String tar1  = getArguments().getString("tar1");
        String tar2  = getArguments().getString("tar2");*/

        String tar1  = exercise.getTa1();
        String tar2  = exercise.getTa2();

        System.out.println(""+tar1);
        System.out.println(""+tar2);
        switch (v.getId()) {

            case R.id.cb_check_box_answer1: {
                if (checked && (checkBoxAnswer1.getText().equals(tar1) || checkBoxAnswer1.getText().equals(tar2))) {

                }
                break;
            }
            case R.id.cb_check_box_answer2: {
                if (checked && (checkBoxAnswer2.getText().equals(tar1) || checkBoxAnswer2.getText().equals(tar2))) {
                    Toast.makeText(getContext(), "" +checkBoxAnswer1.getText(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.cb_check_box_answer3: {
                if (checked && (checkBoxAnswer3.getText().equals(tar1) || checkBoxAnswer3.getText().equals(tar2))) {
                    Toast.makeText(getContext(), "" +checkBoxAnswer1.getText(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.cb_check_box_answer4: {
                if (checked && (checkBoxAnswer4.getText().equals(tar1) || checkBoxAnswer4.getText().equals(tar2))) {
                    Toast.makeText(getContext(), "" +checkBoxAnswer4.getText(), Toast.LENGTH_SHORT).show();
                }
                break;
            }

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
            //getFragmentManager().popBackStack();
            transaction.replace(R.id.list_frame, LessonFragment.newInstance(ListActivity.lesson)).commit();
        }

    }
}
