package com.example.evoca.evocaforandroidlearning.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
//    EditText etAnswer;
    private Button checkButton;
    private boolean correctAnswer = false;
    private static Exercise exercise;
    private EditText ed;
    private ImageButton buttonAnswer;
    private TextView textViewAnswer;
    private TextView textViewTitle;

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
        ed = (EditText) rootView.findViewById(R.id.et_text);
        checkButton = (Button) rootView.findViewById(R.id.btn_chack);

        buttonAnswer = (ImageButton) rootView.findViewById(R.id.btn_answer);
        textViewAnswer = (TextView) rootView.findViewById(R.id.tv_answer);
        textViewTitle = (TextView) rootView.findViewById(R.id.tv_title);

        final String tar1 = exercise.getTa1();

        textViewQuestion.setText(exercise.getQuestion());
        textViewTitle.setText(ListActivity.lesson.getTitle());

        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (ed.getText().toString().equals(tar1)) {
                    Toast.makeText(getContext(), "Հալալ ա Քեզ", Toast.LENGTH_SHORT).show();
                    getNextExercise();
                } else {
                    Toast.makeText(getContext(), "Սխալ պատասխան, փորձիր կրկին պատասխանել:", Toast.LENGTH_SHORT).show();
                    LessonFragment lessonFragment = LessonFragment.newInstance(ListActivity.lesson);
                    transaction.replace(R.id.list_frame, lessonFragment).commit();

                }
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed.getText().toString().equals(tar1)) {
                    textViewAnswer.setText(getResources().getString(R.string.correct_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.correct);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    ed.setTextColor(Color.parseColor("#367400"));
                    ed.setEnabled(false);
                } else if (ed.getText().toString().isEmpty()){
                    ed.setError("Դաշտը լրացված չէ");
                }else {
                    textViewAnswer.setText(getResources().getString(R.string.incorrect_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.incorrect);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    ed.setTextColor(Color.parseColor("#D80027"));
                    ed.setEnabled(false);
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

            ListActivity.exerciseIndex = 0;

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("   ").setMessage("Դուք հաջողությամբ ավարտեցիք ․․․․․․․․․․․․․․")
                    .setIcon(R.drawable.congratulations)
                    .setCancelable(false)
                    .setNegativeButton("Շարունակել", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LessonFragment lessonFragment = LessonFragment.newInstance(ListActivity.nextLesson);
                            getFragmentManager().beginTransaction().replace(R.id.list_frame, lessonFragment).commit();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
        }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //case R.id.et_edit_answer:
          //  System.out.println("jjj++++++++++++++++++++++++++++");
           // break;
        }

    }
}
