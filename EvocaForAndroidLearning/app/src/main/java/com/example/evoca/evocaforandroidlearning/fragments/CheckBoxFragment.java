package com.example.evoca.evocaforandroidlearning.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.Model.Exercise;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ChooseActivity;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;
import com.example.evoca.evocaforandroidlearning.util.PrefUtil;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.ArrayList;


public class CheckBoxFragment extends Fragment implements View.OnClickListener {

    private TextView textViewQuestion;
    private CheckBox checkBoxAnswer1;
    private CheckBox checkBoxAnswer2;
    private CheckBox checkBoxAnswer3;
    private CheckBox checkBoxAnswer4;
    private boolean correctAnswer = true;

    private Button buttonCheck;
    private ImageButton buttonAnswer;
    private TextView textViewAnswer;

    private static Exercise exercise;
    private String tar1;
    private String tar2;
    private String tar3;
    private Integer answersCount = 0;

    //private boolean checked;


    public CheckBoxFragment() {
        // Required empty public constructor
    }

    public static CheckBoxFragment newInstance() {
        CheckBoxFragment fragment = new CheckBoxFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_check_box, container, false);
        textViewQuestion = (TextView) rootView.findViewById(R.id.tv_check_box_question);
        checkBoxAnswer1 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer1);
        checkBoxAnswer2 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer2);
        checkBoxAnswer3 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer3);
        checkBoxAnswer4 = (CheckBox) rootView.findViewById(R.id.cb_check_box_answer4);

        buttonAnswer = (ImageButton) rootView.findViewById(R.id.btn_answer);
        textViewAnswer = (TextView) rootView.findViewById(R.id.tv_answer);

        buttonCheck = (Button) rootView.findViewById(R.id.btn_chack);


        textViewQuestion.setText(exercise.getQuestion());
        checkBoxAnswer1.setText(exercise.getAns1());
        checkBoxAnswer2.setText(exercise.getAns2());
        checkBoxAnswer3.setText(exercise.getAns3());
        checkBoxAnswer4.setText(exercise.getAns4());

        tar1  = exercise.getTa1();
        tar2  = exercise.getTa2();
        tar3 = exercise.getTa3();

        if(!tar1.equals("")) answersCount++;
        if(!tar2.equals("")) answersCount++;
        if(!tar3.equals("")) answersCount++;

        System.out.println("----------------------"+tar1);
        System.out.println("----------------------"+tar2);
        System.out.println("----------------------"+tar3);
        System.out.println("----------------------"+answersCount);


       /* checkBoxAnswer1.setOnClickListener(this);
        checkBoxAnswer2.setOnClickListener(this);
        checkBoxAnswer3.setOnClickListener(this);
        checkBoxAnswer4.setOnClickListener(this);
*/

        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (correctAnswer) {
                    Toast.makeText(getContext(), "Հալալ ա Քեզ", Toast.LENGTH_SHORT).show();
                    getNextExercise();
                }
                else {
                    Toast.makeText(getContext(), "Սխալ պատասխան, փորձիր կրկին պատասխանել:", Toast.LENGTH_SHORT).show();
                    LessonFragment lessonFragment = LessonFragment.newInstance(ListActivity.lesson);
                    transaction.replace(R.id.list_frame, lessonFragment).commit();

                }
            }
        });


        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();

                if (correctAnswer){
                    textViewAnswer.setText(getResources().getString(R.string.correct_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.correct);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    System.out.println(correctAnswer);
                } else {
                    textViewAnswer.setText(getResources().getString(R.string.incorrect_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.incorrect);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    //count = 0;
                    checkBoxAnswer1.setChecked(false);
                    checkBoxAnswer2.setChecked(false);
                    checkBoxAnswer3.setChecked(false);
                    checkBoxAnswer4.setChecked(false);
                }

                /*if (correctAnswer && count == 2  ){
                    textViewAnswer.setText(getResources().getString(R.string.correct_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.correct);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    System.out.println(correctAnswer);
                } else if (correctAnswer && count == 3){
                    textViewAnswer.setText(getResources().getString(R.string.correct_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.correct);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    System.out.println(correctAnswer);
                } else {
                    textViewAnswer.setText(getResources().getString(R.string.incorrect_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.incorrect);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    count = 0;
                    checkBoxAnswer1.setChecked(false);
                    checkBoxAnswer2.setChecked(false);
                    checkBoxAnswer3.setChecked(false);
                    checkBoxAnswer4.setChecked(false);
                }*/

            }
        });

        return rootView;
    }


    @Override
    public void onClick(View v) {
       /* checked = ((CheckBox) v).isChecked();
        String tar1  = exercise.getTa1();
        String tar2  = exercise.getTa2();
        String tar3 = exercise.getTa3();
        System.out.println(""+tar1);
        System.out.println(""+tar2);
        System.out.println(""+tar3);
        switch (v.getId()) {
            case R.id.cb_check_box_answer1: {
                if (checked && (checkBoxAnswer1.getText().equals(tar1) || checkBoxAnswer1.getText().equals(tar2) || checkBoxAnswer1.getText().equals(tar3))) {
                    correctAnswer = true;
                    count++;
                }else {
                    correctAnswer = false;
                    count = 0;
                }
                break;
            }
            case R.id.cb_check_box_answer2: {
                if (checked && (checkBoxAnswer2.getText().equals(tar1) || checkBoxAnswer2.getText().equals(tar2)|| checkBoxAnswer2.getText().equals(tar3))) {
                    correctAnswer = true;
                    count++;
                }else {
                    correctAnswer = false;
                    count = 0;
                }
                break;
            }
            case R.id.cb_check_box_answer3: {
                if (checked && (checkBoxAnswer3.getText().equals(tar1) || checkBoxAnswer3.getText().equals(tar2)|| checkBoxAnswer3.getText().equals(tar3))) {
                    correctAnswer = true;
                    count++;
                }else {
                    correctAnswer = false;
                    count = 0;
                }
                break;
            }
            case R.id.cb_check_box_answer4: {
                if (checked && (checkBoxAnswer4.getText().equals(tar1) || checkBoxAnswer4.getText().equals(tar2)|| checkBoxAnswer4.getText().equals(tar3))) {
                    correctAnswer = true;
                    count++;
                }else {
                    correctAnswer = false;
                    count = 0;
                }
                break;
            }

        }*/
    }

    private void checkAnswer() {

        ArrayList<String> userAnswers = new ArrayList<String>();
        int userAnswersCount = 0;
        correctAnswer = true;
        if(checkBoxAnswer1.isChecked()) {
            userAnswers.add(checkBoxAnswer1.getText().toString());
            userAnswersCount++;
        }
        if(checkBoxAnswer2.isChecked()) {
            userAnswers.add(checkBoxAnswer2.getText().toString());
            userAnswersCount++;
        }
        if(checkBoxAnswer3.isChecked()) {
            userAnswers.add(checkBoxAnswer3.getText().toString());
            userAnswersCount++;
        }
        if(checkBoxAnswer4.isChecked()) {
            userAnswers.add(checkBoxAnswer4.getText().toString());
            userAnswersCount++;
        }

        for (String answer : userAnswers) {
            if(!answer.equals(tar1) && !answer.equals(tar2) && !answer.equals(tar3)) {
                correctAnswer = false;
                break;
            }
        }

        if(userAnswersCount != answersCount) {
            correctAnswer = false;
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
            ListActivity.exerciseIndex = 0;
            //transaction.replace(R.id.list_frame, LessonFragment.newInstance(ListActivity.lesson)).commit();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("   ").setMessage("Դուք հաջողությամբ ավարտեցիք ․․․․․․․․․․․․․․")
                    .setIcon(R.drawable.congratulations)
                    .setCancelable(false)
                    .setNegativeButton("Շարունակել", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getContext(), ListActivity.class);
                            startActivity(i);
                            getActivity().finish();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
